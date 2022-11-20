package com.pfmanager.core.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfmanager.core.entity.transaction.TransactionMapping;
import com.pfmanager.core.entity.TransactionCategory;
import com.pfmanager.core.entity.User;
import com.pfmanager.core.entity.transaction.Transaction;
import com.pfmanager.core.repository.transaction.TransactionCategoryRepository;
import com.pfmanager.core.repository.transaction.TransactionMappingRepository;
import com.pfmanager.core.repository.transaction.TransactionRepository;

@Service
public class TransactionService {
    private @Autowired TransactionRepository transactionRepository;
    private @Autowired TransactionMappingRepository transactionMappingRepository;
    private @Autowired TransactionCategoryRepository transactionCategoryRepository;

    public Transaction createTransaction(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }

    public Iterable<Transaction> findAllTransactions() {
        return this.transactionRepository.findAll();
    }

    private BigDecimal getTotalAmount(List<Transaction> transactions) {
        return transactions.stream().map(t -> t.getAmount()).reduce(
            BigDecimal.ZERO,
            (a, b) -> a.add(b).setScale(2, RoundingMode.HALF_EVEN)
        ).setScale(2, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getTotalSourceAmount(List<TransactionMapping> transactionMappings) {
        return getTotalAmount(transactionMappings.stream().map(tm -> tm.getSourceTransaction()).toList());
    }
    private BigDecimal getTotalTargetAmount(List<TransactionMapping> transactionMappings) {
        return getTotalAmount(transactionMappings.stream().map(tm -> tm.getTargetTransaction()).toList());
    }

    private Iterable<TransactionMapping> createTransactionMappings(List<TransactionMapping> transactionMappings) {
        if(transactionMappings.stream().filter(t -> ! t.getSourceTransaction().getActive()).count() > 0) {
            throw new Error("All source transactions must be active");
        }
        BigDecimal sourceTotalAmount = this.getTotalSourceAmount(transactionMappings);
        BigDecimal targetTotalAmount = this.getTotalTargetAmount(transactionMappings);
        if( ! sourceTotalAmount.equals(targetTotalAmount) ) {
            throw new Error("The sum of the source transaction amount is not equal to the sum of targets");
        }
        transactionMappings.forEach(tm -> {
            tm.getSourceTransaction().setActive(false);
            this.transactionRepository.save(tm.getSourceTransaction());
            tm.getTargetTransaction().setActive(true);
            this.transactionRepository.save(tm.getTargetTransaction());
        });
        return this.transactionMappingRepository.saveAll(transactionMappings);
    }

    private void deleteTransactionMapping(Transaction sourceTransaction, Transaction targetTransaction) {
        List<TransactionMapping> transactionMappings = new ArrayList<>();
        if(sourceTransaction == null) {
            if (targetTransaction == null) {
                throw new Error("One of sourceTransaction/targetTransaction must be provided, none provided");
            }
            else {
                transactionMappings = targetTransaction.getTargetMappings();
            }
        }
        else {
            if(targetTransaction == null) {
                transactionMappings = sourceTransaction.getSourceMappings();
            }
            else {
                throw new Error("One of sourceTransaction/targetTransaction must be provided, two provided");
            }
        }
        if(transactionMappings.stream().filter(tm -> tm.getSourceTransaction().getActive()).count() > 0) {
            throw new Error("All source transactions must be inactive");
        }
        if(transactionMappings.stream().filter(tm -> ! tm.getTargetTransaction().getActive()).count() > 0) {
            throw new Error("All target transactions must be active");
        }
        transactionMappings.forEach(tm -> {
            this.transactionRepository.delete(tm.getTargetTransaction());
            tm.getSourceTransaction().setActive(true);
            this.transactionRepository.save(tm.getSourceTransaction());
        });
        this.transactionMappingRepository.deleteAll(transactionMappings);
    }

    public Iterable<TransactionMapping> groupTransactions(List<Transaction> sourceTransactions, Transaction targetTransaction) {
        return this.createTransactionMappings(sourceTransactions, Arrays.asList(targetTransaction));
    }

    public void ungroupTransaction(Transaction targetTransaction) {
        this.deleteTransactionMapping(null, targetTransaction);
    }

    public Iterable<TransactionMapping> splitTransaction(Transaction sourceTransaction, List<Transaction> targetTransactions) {
        return this.createTransactionMappings(Arrays.asList(sourceTransaction), targetTransactions);
    }

    public void unsplitTransaction(Transaction sourceTransaction) {
        this.deleteTransactionMapping(sourceTransaction, null);
    }

    public TransactionCategory save(TransactionCategory transactionCategory) {
        return this.transactionCategoryRepository.save(transactionCategory);
    }

    public Iterable<TransactionMapping> shareTransaction(Transaction sourceTransaction, HashMap<User, BigDecimal> splitMapping, User roundingAdjustUser) {
        HashMap<User, Transaction> targetTransactions = new HashMap<>();
        BigDecimal partSum = splitMapping.entrySet().stream().map(k -> k.getValue()).reduce(BigDecimal.ZERO, BigDecimal::add);
        splitMapping.forEach((user, part) -> {
            Transaction transaction = new Transaction();
            transaction.setCategory(sourceTransaction.getCategory());
            transaction.setDescription(sourceTransaction.getDescription());
            transaction.setLabels(sourceTransaction.getLabels());
            transaction.setMemo(sourceTransaction.getMemo());
            transaction.setPostingDate(sourceTransaction.getPostingDate());
            transaction.setTransactionDate(sourceTransaction.getTransactionDate());

            transaction.setAccount(null);
            transaction.setAmount(roundCent(sourceTransaction.getAmount().multiply(part).divide(partSum).setScale(2, RoundingMode.HALF_EVEN)));
            targetTransactions.put(user, transaction);
        });
        BigDecimal totalRoundedAmounts = targetTransactions.entrySet().stream().map(
            t -> t.getValue().getAmount()
        ).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_EVEN);
        targetTransactions.get(roundingAdjustUser).setAmount(
            sourceTransaction.getAmount().subtract(
                totalRoundedAmounts.subtract(
                    targetTransactions.get(roundingAdjustUser).getAmount()
                )
            ).setScale(2, RoundingMode.HALF_EVEN)
        );
        return this.splitTransaction(sourceTransaction, new ArrayList<Transaction>(targetTransactions.values()));
    }
    private static BigDecimal roundCent(BigDecimal value) {
        return value.round(new MathContext(2, RoundingMode.HALF_EVEN));
    }
}

package com.pfmanager.core.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfmanager.core.entity.TransactionCategory;
import com.pfmanager.core.entity.User;
import com.pfmanager.core.entity.transaction.PartialTransaction;
import com.pfmanager.core.entity.transaction.Transaction;
import com.pfmanager.core.entity.transaction.TransactionGroup;
import com.pfmanager.core.repository.transaction.PartialTransactionRepository;
import com.pfmanager.core.repository.transaction.TransactionCategoryRepository;
import com.pfmanager.core.repository.transaction.TransactionGroupRepository;
import com.pfmanager.core.repository.transaction.TransactionRepository;

@Service
public class TransactionService {
    private @Autowired TransactionRepository transactionRepository;
    private @Autowired TransactionCategoryRepository transactionCategoryRepository;
    private @Autowired TransactionGroupRepository transactionGroupRepository;
    private @Autowired PartialTransactionRepository partialTransactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }

    public Iterable<Transaction> findAllTransactions() {
        return this.transactionRepository.findAll();
    }

    private BigDecimal getTotalAmount(List<? extends Transaction> targetTransactions) {
        return targetTransactions.stream().map(t -> t.getAmount()).reduce(
            BigDecimal.ZERO,
            (a, b) -> a.add(b).setScale(2, RoundingMode.HALF_EVEN)
        ).setScale(2, RoundingMode.HALF_EVEN);
    }

    private void duplicateTransaction(Transaction sourceTransaction, Transaction targetTransaction) {
        targetTransaction.setCategory(sourceTransaction.getCategory());
        targetTransaction.setDescription(sourceTransaction.getDescription());
        targetTransaction.setMemo(sourceTransaction.getMemo());
        targetTransaction.setPostingDate(sourceTransaction.getPostingDate());
        targetTransaction.setTransactionDate(sourceTransaction.getTransactionDate());
    }

    public TransactionGroup groupTransactions(List<Transaction> sourceTransactions, Transaction transactionModel) {
        if(sourceTransactions.stream().filter(t -> ! t.getActive()).count() > 0) {
            throw new Error("All source transactions must be active");
        }
        TransactionGroup transactionGroup = new TransactionGroup();
        duplicateTransaction(transactionModel, transactionGroup);

        transactionGroup.setAmount(getTotalAmount(sourceTransactions));
        transactionGroup.setChildTransactions(sourceTransactions);
        transactionGroup.setActive(true);
        sourceTransactions.forEach(t -> {
            t.setActive(false);
            this.transactionRepository.save(t);
        });
        return this.transactionGroupRepository.save(transactionGroup);
    }

    public TransactionGroup groupTransactions(List<Transaction> sourceTransactions) {
        return groupTransactions(sourceTransactions, sourceTransactions.get(0));
    }

    public void undivideTransaction(TransactionGroup transactionGroup) {
        transactionGroup.getChildTransactions().forEach(t -> {
            t.setActive(true);
            this.transactionRepository.save(t);
        });
        this.transactionGroupRepository.delete(transactionGroup);
    }

    private Iterable<PartialTransaction> divideTransaction(Transaction sourceTransaction, List<PartialTransaction> targetTransactions) {
        if(! sourceTransaction.getActive()) {
            throw new Error("Source transaction must be active");
        }
        if(! getTotalAmount(targetTransactions).equals(sourceTransaction.getAmount())) {
            throw new Error("Target transactions amount sum not equal to source transaction amount");
        }
        targetTransactions.forEach(t -> {
            t.setActive(true);
            t.setParentTransaction(sourceTransaction);
            this.partialTransactionRepository.save(t);
        });
        sourceTransaction.setActive(false);
        return this.partialTransactionRepository.saveAll(targetTransactions);
    }

    public Iterable<PartialTransaction> splitTransaction(Transaction sourceTransaction, List<BigDecimal> amounts) {
        List<PartialTransaction> partialTransactions = new ArrayList<>();
        amounts.forEach(amount -> {
            PartialTransaction pt = new PartialTransaction();
            duplicateTransaction(sourceTransaction, pt);
            pt.setAmount(amount);
            partialTransactions.add(pt);
        });
        return divideTransaction(sourceTransaction, partialTransactions);
    }

    public Iterable<PartialTransaction> shareTransaction(Transaction sourceTransaction, HashMap<User, BigDecimal> splitMapping, User roundingAdjustUser) {
        HashMap<User, PartialTransaction> targetTransactions = new HashMap<>();
        BigDecimal partsSum = splitMapping.entrySet().stream().map(k -> k.getValue()).reduce(BigDecimal.ZERO, BigDecimal::add);
        splitMapping.forEach((user, part) -> {
            PartialTransaction transaction = new PartialTransaction();
            duplicateTransaction(sourceTransaction, transaction);

            transaction.setAmount(sourceTransaction.getAmount().multiply(part).divide(partsSum).setScale(2, RoundingMode.HALF_EVEN));
            transaction.setOwner(user);
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
        return this.divideTransaction(sourceTransaction, new ArrayList<PartialTransaction>(targetTransactions.values()));
    }

    public TransactionCategory save(TransactionCategory transactionCategory) {
        return this.transactionCategoryRepository.save(transactionCategory);
    }

}

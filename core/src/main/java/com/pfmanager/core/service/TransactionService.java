package com.pfmanager.core.service;

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

    private Double getTotalAmount(List<Transaction> transactions) {
        return transactions.stream().map(t -> t.getAmount()).reduce(0.0, (a, b) -> a + b);
    }

    private Iterable<TransactionMapping> createTransactionMappings(List<Transaction> sourceTransactions, List<Transaction> targetTransactions) {
        if(sourceTransactions.stream().filter(t -> ! t.getActive()).count() > 0) {
            throw new Error("All source transactions must be active");
        }
        if( this.getTotalAmount(sourceTransactions) != this.getTotalAmount(targetTransactions)) {
            throw new Error("The sum of the source transaction amount is not equal to the sum of targets");
        }
        List<TransactionMapping> transactionMappings = new ArrayList<>();
        sourceTransactions.forEach(sourceTransaction -> {
            targetTransactions.forEach(targetTransaction -> {
                TransactionMapping transactionMapping = new TransactionMapping();
                transactionMapping.setSourceTransaction(sourceTransaction);
                transactionMapping.setTargetTransaction(targetTransaction);
                targetTransaction.setActive(true);
                this.transactionRepository.save(targetTransaction);
            });
            sourceTransaction.setActive(false);
            this.transactionRepository.save(sourceTransaction);
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

    public Iterable<TransactionMapping> shareTransaction(Transaction sourceTransaction, HashMap<User, Double> splitMapping, User roundingAdjustUser) {
        List<Transaction> targetTransactions = new ArrayList<>();
        Double partSum = splitMapping.entrySet().stream().map(k -> k.getValue()).reduce(0.0, (a, b) -> a + b);
        splitMapping.forEach((user, part) -> {
            Transaction transaction = new Transaction();
            transaction.setCategory(sourceTransaction.getCategory());
            transaction.setDescription(sourceTransaction.getDescription());
            transaction.setLabels(sourceTransaction.getLabels());
            transaction.setMemo(sourceTransaction.getMemo());
            transaction.setPostingDate(sourceTransaction.getPostingDate());
            transaction.setTransactionDate(sourceTransaction.getTransactionDate());

            transaction.setAccount(null);
            transaction.setAmount(roundCent(sourceTransaction.getAmount() * part / partSum));
            targetTransactions.add(transaction);
        });
        Double totalAmount = targetTransactions.stream().map(t -> t.getAmount()).reduce(0.0, (a, b) -> a + b);
        // @TODO : to be continued
        return this.splitTransaction(sourceTransaction, targetTransactions);
    }
    private static Double roundCent(Double value) {
        Double output = value * 100;
        output = (double) Math.round(value);
        return output / 100;
    }
}

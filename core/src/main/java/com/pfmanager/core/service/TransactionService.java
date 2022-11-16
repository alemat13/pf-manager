package com.pfmanager.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pfmanager.core.entity.transaction.TransactionMapping;
import com.pfmanager.core.entity.transaction.Transaction;
import com.pfmanager.core.repository.transaction.TransactionMappingRepository;
import com.pfmanager.core.repository.transaction.TransactionRepository;

public class TransactionService {
    private @Autowired TransactionRepository transactionRepository;
    private @Autowired TransactionMappingRepository transactionMappingRepository;
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
    public void unsplitTransaction(Transaction sourceTransaction) {
        List<TransactionMapping> transactionMappings = sourceTransaction.getSourceMappings();
        if(sourceTransaction.getActive()) {
            throw new Error("The source transaction must be inactive");
        }
        if(transactionMappings.stream().filter(tm -> ! tm.getTargetTransaction().getActive()).count() > 0) {
            throw new Error("All target transactions must be active");
        }
        transactionMappings.forEach(tm -> {
            this.transactionRepository.delete(tm.getTargetTransaction());
        });
        this.transactionMappingRepository.deleteAll(transactionMappings);
        sourceTransaction.setActive(true);
        this.transactionRepository.save(sourceTransaction);
    }
    public void ungroupTransaction(Transaction targetTransaction) {
        List<TransactionMapping> transactionMappings = targetTransaction.getTargetMappings();
        if (transactionMappings.stream().filter(tm -> tm.getSourceTransaction().getActive()).count() > 0) {
            throw new Error("All source transactions must be inactive");
        }
        if(!targetTransaction.getActive()) {
            throw new Error("The target transaction must be active");
        }
        this.transactionRepository.delete(targetTransaction);
        this.transactionMappingRepository.deleteAll(transactionMappings);
        transactionMappings.forEach(tm -> {
            tm.getSourceTransaction().setActive(true);
            this.transactionRepository.save(tm.getSourceTransaction());
        });
    }
    public Iterable<TransactionMapping> groupTransactions(List<Transaction> sourceTransactions, Transaction targetTransaction) {
        return this.createTransactionMappings(sourceTransactions, Arrays.asList(targetTransaction));
    }
    public Iterable<TransactionMapping> splitTransaction(Transaction sourceTransaction, List<Transaction> targetTransactions) {
        return this.createTransactionMappings(Arrays.asList(sourceTransaction), targetTransactions);
    }
}

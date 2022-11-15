package com.pfmanager.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pfmanager.core.entity.transaction.SplittedTransaction;
import com.pfmanager.core.entity.transaction.Transaction;
import com.pfmanager.core.repository.transaction.SplittedTransactionRepository;
import com.pfmanager.core.repository.transaction.TransactionRepository;

public class TransactionService {
    private @Autowired TransactionRepository transactionRepository;
    private @Autowired SplittedTransactionRepository splittedTransactionRepository;
    public Transaction createTransaction(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }
    public Iterable<Transaction> findAllTransactions() {
        return this.transactionRepository.findAll();
    }
    public SplittedTransaction splitTransaction(Transaction original, List<Transaction> targets) {
        if(!original.getActive()) {
            throw new Error("The original transaction must be active");
        }

        Double targetsSum = targets.stream().map(t -> t.getAmount()).reduce(0.0, (a, b) -> a + b);
        if(original.getAmount() != targetsSum) {
            throw new Error("The sum of the target transactions is not equal to the original amount");
        }

        SplittedTransaction splittedTransaction = new SplittedTransaction();

        splittedTransaction.setOriginalTransaction(original);
        splittedTransaction.setTargetTransactions(targets);

        original.setActive(false);
        targets.forEach(t -> t.setActive(true));

        this.transactionRepository.saveAll(targets);
        this.transactionRepository.save(original);

        return this.splittedTransactionRepository.save(splittedTransaction);
    }
}

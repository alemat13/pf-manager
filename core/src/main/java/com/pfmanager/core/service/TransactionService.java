package com.pfmanager.core.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pfmanager.core.entity.transaction.SubTransaction;
import com.pfmanager.core.entity.transaction.ProxyTransaction;
import com.pfmanager.core.entity.transaction.Transaction;
import com.pfmanager.core.entity.transaction.TransactionInterface;
import com.pfmanager.core.repository.transaction.ProxyTransactionRepository;
import com.pfmanager.core.repository.transaction.SubTransactionRepository;
import com.pfmanager.core.repository.transaction.TransactionRepository;

public class TransactionService {
    private @Autowired TransactionRepository transactionRepository;
    private @Autowired ProxyTransactionRepository proxyTransactionRepository;
    private @Autowired SubTransactionRepository subTransactionRepository;
    public Transaction createTransaction(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }
    public Iterable<Transaction> findAllTransactions() {
        return this.transactionRepository.findAll();
    }
    private Double getTotalAmount(List<? extends TransactionInterface> transactions) {
        return transactions.stream().map(t -> t.getAmount()).reduce(0.0, (a, b) -> a + b);
    }
    public ProxyTransaction createProxyTransaction(List<Transaction> sources, List<SubTransaction> targets) {
        sources.forEach(t -> {
            if(!t.getActive()) {
                throw new Error("The original transactions must be active");
            }
        });

        if(this.getTotalAmount(sources) != this.getTotalAmount(targets)) {
            throw new Error("The sum of the target transactions is not equal to the original amount");
        }

        ProxyTransaction splittedTransaction = new ProxyTransaction();

        splittedTransaction.getSourceTransactions().addAll(sources);
        splittedTransaction.setTargetTransactions(targets);

        sources.forEach(t -> t.setActive(false));
        targets.forEach(t -> t.setActive(true));

        this.transactionRepository.saveAll(targets);
        this.transactionRepository.saveAll(sources);

        return this.proxyTransactionRepository.save(splittedTransaction);
    }
    public void deleteProxyTransaction(ProxyTransaction proxyTransaction) {
        proxyTransaction.getSourceTransactions().forEach(t -> t.setActive(true));
        this.transactionRepository.deleteAll(proxyTransaction.getTargetTransactions());
        this.proxyTransactionRepository.delete(proxyTransaction);
    }
    public ProxyTransaction splitTransaction(Transaction source, List<SubTransaction> targets) {
        List<Transaction> sources = new ArrayList<>();
        return this.createProxyTransaction(sources, targets);
    }
    public void unsplitTransaction(Transaction source) {
        
    }
    public ProxyTransaction groupTransactions(List<Transaction> sources, SubTransaction target) {
        List<SubTransaction> targets = new ArrayList<>();
        return this.createProxyTransaction(sources, targets);
    }
}

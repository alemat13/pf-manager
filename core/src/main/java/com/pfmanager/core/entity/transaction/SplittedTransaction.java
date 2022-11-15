package com.pfmanager.core.entity.transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class SplittedTransaction extends Transaction {
    private Transaction originalTransaction;
    private List<Transaction> targetTransactions = new ArrayList<>();
    public List<Transaction> getTargetTransactions() {
        return targetTransactions;
    }
    public void setTargetTransactions(List<Transaction> targetTransactions) {
        this.targetTransactions = targetTransactions;
    }
    public Transaction getOriginalTransaction() {
        return originalTransaction;
    }
    public void setOriginalTransaction(Transaction originalTransaction) {
        this.originalTransaction = originalTransaction;
    }
    public SplittedTransaction(Date postingDate, Double amount, String description) {
        super(postingDate, amount, description);
    }
    
}

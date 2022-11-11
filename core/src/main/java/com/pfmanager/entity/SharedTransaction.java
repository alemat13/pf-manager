package com.pfmanager.entity;

import java.util.ArrayList;
import java.util.List;

public class SharedTransaction {
    private List<Transaction> originalTransactions = new ArrayList<>();
    private List<SharedTransactionDivision> division;
    public List<Transaction> getOriginalTransactions() {
        return originalTransactions;
    }
    public void setOriginalTransactions(List<Transaction> originalTransactions) {
        this.originalTransactions = originalTransactions;
    }
    public List<SharedTransactionDivision> getDivision() {
        return division;
    }
    public boolean addTransaction(Transaction transaction) {
        return this.originalTransactions.add(transaction);
    }
    public void setDivision(List<SharedTransactionDivision> division) {
        this.division = division;
    }
}

package com.pfmanager.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class SharedTransaction {
    private Long id;
    private List<Transaction> originalTransactions = new ArrayList<>();
    private List<SharedTransactionDivision> division;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public List<Transaction> getOriginalTransactions() {
        return originalTransactions;
    }
    public void setOriginalTransactions(List<Transaction> originalTransactions) {
        this.originalTransactions = originalTransactions;
    }
    public List<SharedTransactionDivision> getDivision() {
        return division;
    }
    public void setDivision(List<SharedTransactionDivision> division) {
        this.division = division;
    }
}

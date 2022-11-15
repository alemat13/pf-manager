package com.pfmanager.core.entity.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity
public class SharedTransaction extends Transaction {
    private @OneToMany (
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    ) List<Transaction> originalTransactions = new ArrayList<>();
    private @OneToMany (
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    ) List<SharedTransactionDivision> division;

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

package com.pfmanager.core.entity.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.pfmanager.core.entity.SharedTransactionDivision;

@Entity
public class SharedTransaction implements Transaction {
    private @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY
    ) Long  id;
    private @OneToMany (
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    ) List<RealTransaction> originalTransactions = new ArrayList<>();
    private @OneToMany (
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    ) List<SharedTransactionDivision> division;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RealTransaction> getOriginalTransactions() {
        return originalTransactions;
    }

    public void setOriginalTransactions(List<RealTransaction> originalTransactions) {
        this.originalTransactions = originalTransactions;
    }

    public List<SharedTransactionDivision> getDivision() {
        return division;
    }

    public void setDivision(List<SharedTransactionDivision> division) {
        this.division = division;
    }
}

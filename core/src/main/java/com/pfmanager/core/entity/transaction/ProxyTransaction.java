package com.pfmanager.core.entity.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class ProxyTransaction {
    private @OneToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    ) List<Transaction> sourceTransactions = new ArrayList<>();
    private @OneToMany (
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    ) List<SubTransaction> targetTransactions = new ArrayList<>();
    public List<SubTransaction> getTargetTransactions() {
        return targetTransactions;
    }
    public void setTargetTransactions(List<SubTransaction> targetTransactions) {
        this.targetTransactions = targetTransactions;
    }
    public List<Transaction> getSourceTransactions() {
        return sourceTransactions;
    }
    public void setSourceTransactions(List<Transaction> originalTransactions) {
        this.sourceTransactions = originalTransactions;
    }
    private RoundSharingMethod roundSharingMethod;

    public RoundSharingMethod getRoundSharingMethod() {
        return roundSharingMethod;
    }

    public void setRoundSharingMethod(RoundSharingMethod roundSharingMethod) {
        this.roundSharingMethod = roundSharingMethod;
    }
}

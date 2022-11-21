package com.pfmanager.core.entity.transaction;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class TransactionGroup extends Transaction {
    private @OneToMany(fetch=FetchType.LAZY, mappedBy = "parentTransaction") List<Transaction> childTransactions;

    public TransactionGroup() {
    }

    public List<Transaction> getChildTransactions() {
        return childTransactions;
    }

    public void setChildTransactions(List<Transaction> childTransactions) {
        this.childTransactions = childTransactions;
    }
}

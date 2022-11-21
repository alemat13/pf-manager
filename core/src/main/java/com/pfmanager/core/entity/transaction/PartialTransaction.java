package com.pfmanager.core.entity.transaction;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.pfmanager.core.entity.User;

@Entity
public class PartialTransaction extends Transaction {
    private BigDecimal part;
    private @ManyToOne(fetch=FetchType.LAZY) User owner;
    private @ManyToOne Transaction parentTransaction;
    
    public PartialTransaction() {
    }

    public BigDecimal getPart() {
        return part;
    }

    public void setPart(BigDecimal part) {
        this.part = part;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Transaction getParentTransaction() {
        return parentTransaction;
    }

    public void setParentTransaction(Transaction parentTransaction) {
        this.parentTransaction = parentTransaction;
    }

}

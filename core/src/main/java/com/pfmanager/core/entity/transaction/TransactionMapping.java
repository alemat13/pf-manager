package com.pfmanager.core.entity.transaction;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.pfmanager.core.entity.User;

@Entity
public class TransactionMapping {
    private @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY
    ) Long id;
    private @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    ) Transaction sourceTransaction;
    private @ManyToOne (
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    ) Transaction targetTransaction;
    private @ManyToOne (
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL
    ) User user;
    private BigDecimal part;

    public Transaction getTargetTransaction() {
        return targetTransaction;
    }

    public void setTargetTransaction(Transaction targetTransaction) {
        this.targetTransaction = targetTransaction;
    }

    public Transaction getSourceTransaction() {
        return sourceTransaction;
    }

    public void setSourceTransaction(Transaction originalTransaction) {
        this.sourceTransaction = originalTransaction;
    }

    private RoundSharingMethod roundSharingMethod;

    public RoundSharingMethod getRoundSharingMethod() {
        return roundSharingMethod;
    }

    public void setRoundSharingMethod(RoundSharingMethod roundSharingMethod) {
        this.roundSharingMethod = roundSharingMethod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getPart() {
        return part;
    }
    
    public void setPart(BigDecimal part) {
        this.part = part;
    }
}

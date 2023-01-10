package com.pfmanager.core.entity.transaction;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.pfmanager.core.entity.Account;

@Entity
public class AccountTransaction extends Transaction {
    private @ManyToOne(
        fetch = FetchType.LAZY
    ) Account account;

    public AccountTransaction() { }
    
    public AccountTransaction(Date postingDate, BigDecimal amount, String description, Account account) {
        super(postingDate, amount, description);
        this.account = account;
    }

    public AccountTransaction(Date postingDate, double amount, String description,  Account account) {
        super(postingDate, amount, description);
        this.account = account;

    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

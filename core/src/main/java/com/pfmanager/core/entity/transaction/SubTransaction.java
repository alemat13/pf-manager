package com.pfmanager.core.entity.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.pfmanager.core.entity.User;

@Entity
public class SubTransaction extends Transaction {
    private @ManyToOne(fetch = FetchType.LAZY) User user;
    private Double part;
    private @ManyToMany(
        mappedBy = "targetTransactions"
    ) List<ProxyTransaction> proxyTransactions = new ArrayList<>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getPart() {
        return part;
    }

    public void setPart(Double part) {
        this.part = part;
    }
}

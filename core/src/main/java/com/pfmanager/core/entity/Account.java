package com.pfmanager.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Account {
    private @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY
    ) Long id;
    private String number;
    private String name;
    private @OneToMany (
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    ) List<User> owners = new ArrayList<>();
    private @ManyToOne(fetch = FetchType.LAZY) Currency currency;
    private @ManyToOne(
        fetch = FetchType.LAZY
    ) Bank bank;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account(String name, Currency currency, Bank bank) {
        this.name = name;
        this.currency = currency;
        this.bank = bank;
    }

    public Account() {
    }

    public List<User> getOwners() {
        return owners;
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

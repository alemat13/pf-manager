package com.pfmanager.entity;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String number;
    private String name;
    private List<User> owners = new ArrayList<>();
    private Currency currency;
    private Bank bank;

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

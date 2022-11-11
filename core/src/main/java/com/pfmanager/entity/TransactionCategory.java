package com.pfmanager.entity;

public class TransactionCategory {
    private String name;
    private TransactionCategory parent;
    public TransactionCategory(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public TransactionCategory getParent() {
        return parent;
    }
    public void setParent(TransactionCategory parent) {
        this.parent = parent;
    }
}

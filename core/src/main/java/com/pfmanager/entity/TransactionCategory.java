package com.pfmanager.entity;

import javax.persistence.Entity;

@Entity
public class TransactionCategory {
    private Long id;
    private String name;
    private TransactionCategory parent;
    public TransactionCategory(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

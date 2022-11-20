package com.pfmanager.core.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TransactionCategory {
    private @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY
    ) Long id;
    private String name;
    private @ManyToOne (
        fetch = FetchType.LAZY,
        cascade = CascadeType.PERSIST
    ) TransactionCategory parent;

    public TransactionCategory() {}
    
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

package com.pfmanager.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransactionLabel {
    private @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY
    ) Long id;
    private String name;
    private String description;
    
    public TransactionLabel() { }

    public TransactionLabel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TransactionLabel(String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.pfmanager.entity;

import javax.persistence.Entity;

@Entity
public class Bank {
    private Long id;
    private String name;
    private String description;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Bank(String name, String description) {
        this.name = name;
        this.description = description;
    }
    public Bank(String name) {
        this.name = name;
    }
    public Bank() {
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

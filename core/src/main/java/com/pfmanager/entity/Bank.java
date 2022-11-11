package com.pfmanager.entity;

public class Bank {
    private String name;
    private String description;
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

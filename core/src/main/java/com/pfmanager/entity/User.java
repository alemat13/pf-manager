package com.pfmanager.entity;

public class User {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    private String emailAddress;
    public User() {
    }
    public User(String name) {
        this.name = name;
    }
    public User(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }
}

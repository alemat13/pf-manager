package com.pfmanager.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User {
    private @Column String name;
    private @Column String emailAddress;
    
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

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }
}

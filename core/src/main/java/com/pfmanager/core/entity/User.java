package com.pfmanager.core.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    private @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY
    ) Long id;
    private String name;
    private String emailAddress;
    
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

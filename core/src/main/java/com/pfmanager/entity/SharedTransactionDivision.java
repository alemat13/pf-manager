package com.pfmanager.entity;

import javax.persistence.Entity;

@Entity
public class SharedTransactionDivision {
    private Long id;
    private User user;
    private Double part;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public SharedTransactionDivision(User user, Double part) {
        this.user = user;
        this.part = part;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Double getPart() {
        return part;
    }
    public void setPart(Double part) {
        this.part = part;
    }
}

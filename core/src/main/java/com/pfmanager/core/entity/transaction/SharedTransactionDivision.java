package com.pfmanager.core.entity.transaction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.pfmanager.core.entity.User;

@Entity
public class SharedTransactionDivision {
    private @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY
    ) Long  id;
    private @ManyToOne(fetch = FetchType.LAZY) User user;
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

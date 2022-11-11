package com.pfmanager.entity;

public class SharedTransactionDivision {
    private User user;
    private Double part;
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

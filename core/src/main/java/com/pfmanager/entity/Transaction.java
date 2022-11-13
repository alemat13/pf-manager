package com.pfmanager.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.pfmanager.entity.enums.TransactionType;

@Entity
public class Transaction {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Long id;
    private Date transactionDate;
    private Date postingDate;
    private Double amount;
    private String description;
    private String memo;
    private Account sourceAccount;
    private Account targetAccount;
    private TransactionType type;
    private TransactionCategory category;
    private List<TransactionLabel> labels;
    public Transaction(Date postingDate, Double amount, String description) {
        this.postingDate = postingDate;
        this.amount = amount;
        this.description = description;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Date getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    public Date getPostingDate() {
        return postingDate;
    }
    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public Account getSourceAccount() {
        return sourceAccount;
    }
    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
    public Account getTargetAccount() {
        return targetAccount;
    }
    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }
    public TransactionType getType() {
        return type;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public TransactionCategory getCategory() {
        return category;
    }
    public void setCategory(TransactionCategory category) {
        this.category = category;
    }
    public List<TransactionLabel> getLabels() {
        return labels;
    }
    public void setLabels(List<TransactionLabel> labels) {
        this.labels = labels;
    }
}

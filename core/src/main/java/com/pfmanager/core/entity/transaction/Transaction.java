package com.pfmanager.core.entity.transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.pfmanager.core.entity.Account;
import com.pfmanager.core.entity.TransactionCategory;
import com.pfmanager.core.entity.TransactionLabel;

@Entity
public class Transaction {
    private @Id @GeneratedValue(
        strategy = GenerationType.IDENTITY
    ) Long id;
    private Date transactionDate;
    private Date postingDate;
    private BigDecimal amount;
    private String description;
    private String memo;
    private Boolean active = true;
    private @ManyToOne(
        fetch = FetchType.LAZY
    ) Account account;
    private @ManyToOne(
        fetch = FetchType.LAZY
    ) TransactionCategory category;
    private @OneToMany (
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    ) List<TransactionLabel> labels;
    private @OneToMany(mappedBy = "sourceTransaction") List<TransactionMapping> sourceMappings;
    private @OneToMany(mappedBy = "targetTransaction") List<TransactionMapping> targetMappings;

    public Transaction(Date postingDate, BigDecimal amount, String description) {
        this.postingDate = postingDate;
        this.amount = amount;
        this.description = description;
    }

    public Transaction() { }
    
    public Transaction(Date postingDate, double amount, String description) {
        this.postingDate = postingDate;
        this.amount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_EVEN);
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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
    
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<TransactionMapping> getSourceMappings() {
        return sourceMappings;
    }

    public void setSourceMappings(List<TransactionMapping> sourceMapping) {
        this.sourceMappings = sourceMapping;
    }

    public List<TransactionMapping> getTargetMappings() {
        return targetMappings;
    }

    public void setTargetMappings(List<TransactionMapping> targetMapping) {
        this.targetMappings = targetMapping;
    }
}

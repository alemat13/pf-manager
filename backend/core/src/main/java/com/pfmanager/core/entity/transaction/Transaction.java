package com.pfmanager.core.entity.transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.pfmanager.core.entity.TransactionCategory;
import com.pfmanager.core.entity.TransactionLabel;

@Entity
public abstract class Transaction {
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
    ) TransactionCategory category;
    private @ManyToMany (
        fetch = FetchType.LAZY
    ) List<TransactionLabel> labels;
    private @OneToMany(fetch=FetchType.LAZY, mappedBy =  "parentTransaction") List<PartialTransaction> childTransactions;
    private @ManyToOne(fetch = FetchType.LAZY) TransactionGroup parentTransaction;

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

}

package com.pfmanager.core.entity.transaction;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class OriginalTransaction extends Transaction {

    public OriginalTransaction(Date postingDate, Double amount, String description) {
        super(postingDate, amount, description);
    }
    
}

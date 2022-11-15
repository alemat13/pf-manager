package com.pfmanager.core.entity.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity
public class SharedTransaction extends SplittedTransaction {
    private @OneToMany (
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        orphanRemoval = true
    ) List<SharedTransactionDivision> targets = new ArrayList<SharedTransactionDivision>();
    private RoundSharingMethod roundSharingMethod;

    public RoundSharingMethod getRoundSharingMethod() {
        return roundSharingMethod;
    }

    public void setRoundSharingMethod(RoundSharingMethod roundSharingMethod) {
        this.roundSharingMethod = roundSharingMethod;
    }
}

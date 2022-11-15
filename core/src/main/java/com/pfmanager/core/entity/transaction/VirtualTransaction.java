package com.pfmanager.core.entity.transaction;

import java.util.ArrayList;
import java.util.List;

public class VirtualTransaction implements Transaction {
    private List<RealTransaction> originalTransactions = new ArrayList<>();

    public List<RealTransaction> getOriginalTransactions() {
        return originalTransactions;
    }

    public void setOriginalTransactions(List<RealTransaction> originalTransactions) {
        this.originalTransactions = originalTransactions;
    }
}

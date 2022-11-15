package com.pfmanager.core.repository.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfmanager.core.entity.transaction.SplittedTransaction;

@Repository
public interface SplittedTransactionRepository extends CrudRepository<SplittedTransaction, Long> {
    
}

package com.pfmanager.core.repository.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfmanager.core.entity.transaction.PartialTransaction;

@Repository
public interface PartialTransactionRepository extends CrudRepository<PartialTransaction, Long> {
    
}

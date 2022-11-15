package com.pfmanager.core.repository.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfmanager.core.entity.transaction.SharedTransaction;

@Repository
public interface SharedTransactionRepository extends CrudRepository<SharedTransaction, Long> {
    
}

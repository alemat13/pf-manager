package com.pfmanager.core.repository.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfmanager.core.entity.transaction.SubTransaction;

@Repository
public interface SubTransactionRepository extends CrudRepository<SubTransaction, Long> {
    
}

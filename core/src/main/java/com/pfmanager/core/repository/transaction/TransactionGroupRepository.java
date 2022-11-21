package com.pfmanager.core.repository.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfmanager.core.entity.transaction.TransactionGroup;

@Repository
public interface TransactionGroupRepository extends CrudRepository<TransactionGroup, Long> {
    
}

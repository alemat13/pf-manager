package com.pfmanager.core.repository.transaction;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfmanager.core.entity.transaction.ProxyTransaction;

@Repository
public interface ProxyTransactionRepository extends CrudRepository<ProxyTransaction, Long> {
    
}

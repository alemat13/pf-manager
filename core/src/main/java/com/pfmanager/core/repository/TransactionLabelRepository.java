package com.pfmanager.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfmanager.core.entity.TransactionLabel;

@Repository
public interface TransactionLabelRepository extends CrudRepository<TransactionLabel, Long> {

}

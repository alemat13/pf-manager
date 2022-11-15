package com.pfmanager.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfmanager.core.entity.TransactionCategory;

@Repository
public interface TransactionCategoryRepository extends CrudRepository<TransactionCategory, Long> {

}

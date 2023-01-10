package com.pfmanager.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfmanager.core.entity.Bank;

@Repository
public interface BankRepository extends CrudRepository<Bank, Long> {

}

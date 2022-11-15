package com.pfmanager.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pfmanager.core.entity.Currency;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

}

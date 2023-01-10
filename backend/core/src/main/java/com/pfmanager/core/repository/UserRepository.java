package com.pfmanager.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.pfmanager.core.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
}

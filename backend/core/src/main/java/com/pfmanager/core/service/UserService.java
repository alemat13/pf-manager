package com.pfmanager.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfmanager.core.entity.User;
import com.pfmanager.core.repository.UserRepository;

@Service
public class UserService {
    private @Autowired UserRepository userRepository;
    public Iterable<User> findAllUsers() {
        return this.userRepository.findAll();
    }
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }
}

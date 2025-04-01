package com.spring.boot.micro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.boot.micro.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    User findByAccountId(String accountId);
}
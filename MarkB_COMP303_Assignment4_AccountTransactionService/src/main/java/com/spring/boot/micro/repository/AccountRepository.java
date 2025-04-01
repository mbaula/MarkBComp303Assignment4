package com.spring.boot.micro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.boot.micro.model.Account;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByAccountId(String accountId);
}
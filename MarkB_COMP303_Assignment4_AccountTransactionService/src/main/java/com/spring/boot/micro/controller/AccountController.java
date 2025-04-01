package com.spring.boot.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.micro.model.Account;
import com.spring.boot.micro.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/reserve")
    public ResponseEntity<String> reserve(@RequestBody String orderJson) {
        return accountService.reserveFunds(orderJson);
    }

    @PostMapping("/account/create")
    public ResponseEntity<String> create(@RequestBody Account account) {
        return accountService.createAccount(account);
    }
}

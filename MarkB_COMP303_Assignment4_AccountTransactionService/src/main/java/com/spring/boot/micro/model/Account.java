package com.spring.boot.micro.model;

import org.springframework.data.annotation.Id;

public class Account {

    @Id
    private String id;
    private String accountId;
    private double balance;

    public Account() {}

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getAccountId() { return accountId; }

    public void setAccountId(String accountId) { this.accountId = accountId; }

    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }
}


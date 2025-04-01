package com.spring.boot.micro.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.micro.model.Account;
import com.spring.boot.micro.repository.AccountRepository;

@Service
public class AccountService {

	private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<String> reserveFunds(String orderJson) {
        try {
        	log.info("[Account] Reserve request received");

            JsonNode orderNode = objectMapper.readTree(orderJson);
            String accountId = orderNode.get("accountId").asText();
            int quantity = orderNode.get("quantity").asInt();
            double totalCost = quantity * 100;
            log.info("[Account] accountId={}, quantity={}, totalCost={}", accountId, quantity, totalCost);

            Account account = accountRepository.findByAccountId(accountId);
            if (account == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");

            log.info("[Account] Current balance: {}", account.getBalance());

            if (account.getBalance() >= totalCost) {
                account.setBalance(account.getBalance() - totalCost);
                accountRepository.save(account);
                log.info("[Account] Funds reserved. New balance: {}", account.getBalance());

                return ResponseEntity.ok("Funds reserved. Remaining balance: $" + account.getBalance());
            } else {
                log.warn("[Account] Insufficient funds for account: {}", accountId);

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient funds");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    public ResponseEntity<String> createAccount(Account account) {
        if (accountRepository.findByAccountId(account.getAccountId()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Account already exists");
        }
        accountRepository.save(account);
        return ResponseEntity.ok("Account created");
    }
    
    
}
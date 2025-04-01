package com.spring.boot.micro.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MarketService {
	private static final Logger log = LoggerFactory.getLogger(MarketService.class);

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<String> processOrder(String orderJson) {
        try {
            JsonNode orderNode = objectMapper.readTree(orderJson);
            String accountId = orderNode.get("accountId").asText();
            String stockSymbol = orderNode.get("stockSymbol").asText();
            int quantity = orderNode.get("quantity").asInt();
            
            log.info("[Market] Received order: {} x {} from {}", quantity, stockSymbol, accountId);
            log.info("[Market] Order placed successfully in simulated market.");

            System.out.println("Order placed → " + quantity + " units of " + stockSymbol + " from account " + accountId);

            return ResponseEntity.ok("Order placed successfully for " + stockSymbol);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to process order: " + e.getMessage());
        }
    }
}
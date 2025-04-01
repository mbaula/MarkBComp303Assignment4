package com.spring.boot.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.micro.service.MarketService;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Autowired
    private MarketService marketService;

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody String orderJson) {
        return marketService.processOrder(orderJson);
    }

    @GetMapping("/")
    public String healthCheck() {
        return "Market Service is running";
    }
}
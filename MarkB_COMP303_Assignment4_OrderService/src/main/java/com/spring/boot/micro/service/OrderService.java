package com.spring.boot.micro.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.micro.model.Order;
import com.spring.boot.micro.repository.OrderRepository;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseEntity<Order> placeOrder(String accountId, String stockSymbol, int quantity) {
        try {
            log.info("[OrderService] Request to place order: {} x {} from account {}", quantity, stockSymbol, accountId);

            Order order = new Order(accountId, stockSymbol, quantity, "pending");
            orderRepository.save(order);
            log.info("[OrderService] Order saved with status: pending");

            String jsonOrder = objectMapper.writeValueAsString(order);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(jsonOrder, headers);

            log.info("[OrderService] Sending to AccountTransactionService...");
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> accountResponse = restTemplate.postForEntity(
                    "http://localhost:8082/account/reserve", request, String.class);

            log.info("[OrderService] Account service responded: {}", accountResponse.getBody());

            if (accountResponse.getStatusCode().is2xxSuccessful()) {
                log.info("[OrderService] Sending to MarketService...");
                ResponseEntity<String> marketResponse = restTemplate.postForEntity(
                        "http://localhost:8083/market/placeOrder", request, String.class);

                log.info("[OrderService] Market service responded: {}", marketResponse.getBody());

                if (marketResponse.getStatusCode().is2xxSuccessful()) {
                    order.setStatus("placed");
                    orderRepository.save(order);
                    log.info("[OrderService] Final order status set to: placed");
                    return ResponseEntity.ok(order);
                }
            }

            log.warn("[OrderService] Order could not be completed. Returning as pending.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(order);

        } catch (Exception e) {
            log.error("[OrderService] Unexpected error while placing order", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

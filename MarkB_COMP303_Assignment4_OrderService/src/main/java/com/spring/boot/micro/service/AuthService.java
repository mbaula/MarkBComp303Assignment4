package com.spring.boot.micro.service;

import com.spring.boot.micro.model.User;
import com.spring.boot.micro.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final RestTemplate restTemplate = new RestTemplate();

    public boolean userExists(String username, String accountId) {
        boolean exists = userRepository.findByUsername(username) != null ||
                         userRepository.findByAccountId(accountId) != null;
        log.info("[AuthService] Checking existence for username='{}' / accountId='{}' -> {}", username, accountId, exists);
        return exists;
    }

    public String registerUser(User user) {
        try {
            log.info("[AuthService] Registering new user: {}", user.getUsername());

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            log.info("[AuthService] Saved user in DB");

            String accountJson = "{ \"accountId\": \"" + user.getAccountId() + "\", \"balance\": 10000 }";
            log.info("[AuthService] Sending account creation request: {}", accountJson);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(accountJson, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(
                    "http://localhost:8082/account/create", request, String.class);

            log.info("[AuthService] Account Service responded: {}", response.getBody());
            return "" + response.getBody();

        } catch (Exception e) {
            log.error("[AuthService] Failed to create account for {}: {}", user.getUsername(), e.getMessage(), e);
            return "Failed to create account: " + e.getMessage();
        }
    }

    public User authenticate(String username, String rawPassword) {
        log.info("[AuthService] Authenticating user: {}", username);
        User found = userRepository.findByUsername(username);
        if (found != null && passwordEncoder.matches(rawPassword, found.getPassword())) {
            log.info("[AuthService] Login success for user: {}", username);
            return found;
        } else {
            log.warn("[AuthService] Login failed for user: {}", username);
            return null;
        }
    }
}

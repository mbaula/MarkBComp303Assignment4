package com.spring.boot.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MarkBComp303Assignment4AccountTransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkBComp303Assignment4AccountTransactionServiceApplication.class, args);
		System.out.println("Account Transaction Service is running!");
	}

}

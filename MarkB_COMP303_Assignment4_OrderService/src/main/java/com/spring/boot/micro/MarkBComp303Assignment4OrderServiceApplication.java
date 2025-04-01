package com.spring.boot.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MarkBComp303Assignment4OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkBComp303Assignment4OrderServiceApplication.class, args);
		System.out.println("Order Service is running!");
	}

}

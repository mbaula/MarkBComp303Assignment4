package com.spring.boot.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MarkBComp303Assignment4MarketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkBComp303Assignment4MarketServiceApplication.class, args);
		System.out.println("Market Service is running!");
	}

}

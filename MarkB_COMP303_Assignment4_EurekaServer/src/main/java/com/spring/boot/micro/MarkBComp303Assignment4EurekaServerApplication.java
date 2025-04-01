package com.spring.boot.micro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MarkBComp303Assignment4EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkBComp303Assignment4EurekaServerApplication.class, args);
		System.out.println("Eureka Server started and ready to register microservices...");
	}

}

package com.spring.boot.micro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.boot.micro.model.Order;

public interface OrderRepository extends MongoRepository<Order, String> {

}

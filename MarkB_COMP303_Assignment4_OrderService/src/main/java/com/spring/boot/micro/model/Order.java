package com.spring.boot.micro.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("orders")
public class Order {
	@Id
    private String id;

    private String accountId;
    private String stockSymbol;
    private int quantity;
    private String status; // "pending", "reserved", "placed"

    public Order() {}

    public Order(String accountId, String stockSymbol, int quantity, String status) {
        this.accountId = accountId;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.status = status;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

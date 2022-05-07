package com.example.gccoffeeserver.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID orderId;
    private final Email email; // email로 주문받기
    private String address;
    private String postcode;
    private final List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(UUID orderId, Email email, String address, String postcode, List<OrderItem> orderItems, OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderItems = orderItems;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Order(Email email, String address, String postcode, List<OrderItem> orderItems) {
        this.orderId = UUID.randomUUID();
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderItems = orderItems;
        this.orderStatus = OrderStatus.ACCEPTED;
        this.createdAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
        this.updatedAt = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
    }

    public UUID getOrderId() {
        return orderId;
    }

    public Email getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

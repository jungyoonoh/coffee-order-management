package com.example.gccoffeeserver.controller.views;

import com.example.gccoffeeserver.model.Order;
import com.example.gccoffeeserver.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrderDto {
    private final UUID orderId;
    private final String email; // email로 주문받기
    private final String address;
    private final String postcode;
    private final OrderStatus orderStatus;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private OrderDto(UUID orderId, String email, String address, String postcode, OrderStatus orderStatus, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderStatus = orderStatus;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static OrderDto from(Order order) {
        return new OrderDto(order.getOrderId(),
                order.getEmail().getAddress(),
                order.getAddress(),
                order.getPostcode(),
                order.getOrderStatus(),
                order.getCreatedAt(),
                order.getUpdatedAt());
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
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

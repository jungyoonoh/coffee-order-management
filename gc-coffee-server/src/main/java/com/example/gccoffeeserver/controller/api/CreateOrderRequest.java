package com.example.gccoffeeserver.controller.api;

import com.example.gccoffeeserver.model.OrderItem;

import java.util.List;

public record CreateOrderRequest(String email, String address, String postcode,
                                 List<OrderItem> orderItems) {

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}

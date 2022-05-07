package com.example.gccoffeeserver.controller.api;

import com.example.gccoffeeserver.model.OrderItem;

import java.util.List;

public record CreateOrderRequest(String email, String address, String postcode,
                                 List<OrderItem> orderItem) {
}

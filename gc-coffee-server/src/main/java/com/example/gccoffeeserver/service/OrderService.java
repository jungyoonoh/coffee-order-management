package com.example.gccoffeeserver.service;

import com.example.gccoffeeserver.model.Email;
import com.example.gccoffeeserver.model.Order;
import com.example.gccoffeeserver.model.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order createOrder(Email email, String address, String postcode, List<OrderItem> orderItems);

    List<Order> getAllOrders();

    List<OrderItem> getOrderItemsByOrderId(UUID orderId);

    void removeAllOrder();
}

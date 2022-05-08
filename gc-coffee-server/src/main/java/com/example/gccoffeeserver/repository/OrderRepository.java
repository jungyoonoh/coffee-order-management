package com.example.gccoffeeserver.repository;

import com.example.gccoffeeserver.model.Order;
import com.example.gccoffeeserver.model.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {

    Order insert(Order order);

    List<Order> findAllOrders();

    List<OrderItem> findOrderItemsByOrderId(UUID orderId);

    Long getSumOfSales();

    void deleteAllOrder();

    void deleteOrderById(UUID orderId);
}
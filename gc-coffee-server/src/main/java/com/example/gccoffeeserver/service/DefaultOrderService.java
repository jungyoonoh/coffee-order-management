package com.example.gccoffeeserver.service;

import com.example.gccoffeeserver.model.Email;
import com.example.gccoffeeserver.model.Order;
import com.example.gccoffeeserver.model.OrderItem;
import com.example.gccoffeeserver.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;

    public DefaultOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Email email, String address, String postcode, List<OrderItem> orderItems) {
        return orderRepository.insert(new Order(email, address, postcode, orderItems));
    }

    @Override
    @Transactional
    public List<Order> getAllOrders() {
        return orderRepository.findAllOrders();
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(UUID orderId) {
        return orderRepository.findOrderItemsByOrderId(orderId);
    }

    @Override
    public void removeAllOrder() {
        orderRepository.deleteAllOrder();
    }
}

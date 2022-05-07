package com.example.gccoffeeserver.controller.api;

import com.example.gccoffeeserver.model.Email;
import com.example.gccoffeeserver.model.Order;
import com.example.gccoffeeserver.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/v1/orders")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        System.out.println("createOrderRequest = " + createOrderRequest.toString());
        var order = orderService.createOrder(new Email(createOrderRequest.email()),
                createOrderRequest.address(),
                createOrderRequest.postcode(),
                createOrderRequest.orderItems());

        return ResponseEntity.ok(order);
    }
}

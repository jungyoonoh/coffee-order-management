package com.example.gccoffeeserver.controller.views;

import com.example.gccoffeeserver.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderViewController {
    private final OrderService orderService;

    public OrderViewController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String ordersPage(Model model) {
        var orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @DeleteMapping("/orders")
    public String removeAllOrders() {
        orderService.removeAllOrder();
        return "redirect:/orders";
    }
}

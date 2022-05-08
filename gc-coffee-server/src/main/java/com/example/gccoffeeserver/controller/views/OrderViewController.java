package com.example.gccoffeeserver.controller.views;

import com.example.gccoffeeserver.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Controller
public class OrderViewController {
    private final OrderService orderService;

    public OrderViewController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String ordersPage(Model model) {
        var orderDtoList =  orderService.getAllOrders()
                .stream()
                .map(OrderDto::from)
                .toList();
        model.addAttribute("orders", orderDtoList);

        var totalIncome = orderService.getTotalIncome();
        model.addAttribute("totalIncome", totalIncome);
        return "order-list";
    }

    @GetMapping("/orders/{orderId}")
    public String orderDetailPage(@PathVariable UUID orderId, Model model) {
        var orderItems = orderService.getOrderItemsByOrderId(orderId);
        model.addAttribute("orderItems", orderItems);
        return "order-detail";
    }

    @DeleteMapping("/orders")
    public String removeAllOrders() {
        orderService.removeAllOrder();
        return "redirect:/orders";
    }

    @DeleteMapping("/orders/{id}")
    public String deleteOrder(@PathVariable UUID id) {
        orderService.removeOrderById(id);
        return "redirect:/orders";
    }
}

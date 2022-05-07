package com.example.gccoffeeserver.model;

import java.util.UUID;

public record OrderItem(UUID productId, String productName, Category category, long price,
                        int quantity) {

    @Override
    public String toString() {
        return "OrderItem{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

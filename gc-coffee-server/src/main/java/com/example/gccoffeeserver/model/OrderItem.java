package com.example.gccoffeeserver.model;

import java.util.UUID;

public record OrderItem(UUID productId, Category category, long price,
                        int quantity) {
    @Override
    public String toString() {
        return "OrderItem{" +
                "productId=" + productId +
                ", category=" + category +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

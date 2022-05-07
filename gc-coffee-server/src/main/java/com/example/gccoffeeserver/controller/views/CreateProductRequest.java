package com.example.gccoffeeserver.controller.views;

import com.example.gccoffeeserver.model.Category;

public record CreateProductRequest(String name, Category category,
                                   long price, String description) {
}

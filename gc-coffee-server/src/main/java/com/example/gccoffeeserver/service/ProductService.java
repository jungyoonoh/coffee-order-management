package com.example.gccoffeeserver.service;

import com.example.gccoffeeserver.model.Category;
import com.example.gccoffeeserver.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    List<Product> getProductsByCategory(Category category);

    Optional<Product> getProductByName(String name);

    Optional<Product> getProductById(UUID id);

    List<Product> getAllProducts();

    Product createProduct(String name, Category category, long price, String description);

    Optional<Product> updateProductProperties(UUID id, String name, Category category, long price, String description);

    void removeProductById(UUID id);

    void removeAllProduct();
}

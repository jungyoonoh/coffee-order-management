package com.example.gccoffeeserver.service;

import com.example.gccoffeeserver.model.Category;
import com.example.gccoffeeserver.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getProductsByCategory(Category category);

    Optional<Product> getProductByName(String name);

    List<Product> getAllProducts();

    Product createProduct(String name, Category category, Long price, String description);

    Optional<Product> updateProductProperties(String name, Category category, Long price, String description);

}

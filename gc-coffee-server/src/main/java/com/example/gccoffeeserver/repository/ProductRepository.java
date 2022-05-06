package com.example.gccoffeeserver.repository;

import com.example.gccoffeeserver.model.Category;
import com.example.gccoffeeserver.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    Optional<Product> findById(UUID id);

    Optional<Product> findByName(String name);

    List<Product> findByCategory(Category category);

    List<Product> findAll();

    Product insert(Product product);

    Product update(Product product);

    void deleteAll();

    void deleteById(UUID id);
}

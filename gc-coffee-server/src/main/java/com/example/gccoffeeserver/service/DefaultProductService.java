package com.example.gccoffeeserver.service;

import com.example.gccoffeeserver.model.Category;
import com.example.gccoffeeserver.model.Product;
import com.example.gccoffeeserver.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultProductService implements ProductService {

    private final ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String name, Category category, Long price, String description) {
        return productRepository.insert(new Product(name, category, price, description));
    }

    @Override
    @Transactional
    public Optional<Product> updateProductProperties(String name, Category category, Long price, String description) {
        var product = productRepository.findByName(name);
        if (product.isPresent()) {
            product.get().updateProductProperties(name, category, price, description);
            productRepository.update(product.get());
            return product;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> updateProductProperties(UUID id, String name, Category category, Long price, String description) {
        var product = productRepository.findById(id);
        if (product.isPresent()) {
            product.get().updateProductProperties(name, category, price, description);
            productRepository.update(product.get());
            return product;
        }
        return Optional.empty();
    }

    @Override
    public void removeProductById(UUID id) {
        productRepository.deleteById(id);
    }
}

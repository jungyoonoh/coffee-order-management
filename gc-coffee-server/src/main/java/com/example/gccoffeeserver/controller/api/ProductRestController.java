package com.example.gccoffeeserver.controller.api;

import com.example.gccoffeeserver.model.Product;
import com.example.gccoffeeserver.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/v1/products")
    public List<Product> productList() {
        return productService.getAllProducts();
    }
}

package com.example.gccoffeeserver.controller.views;

import com.example.gccoffeeserver.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class ProductViewController {

    private final ProductService productService;

    public ProductViewController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String productsPage(Model model) {
        var products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/products/{id}")
    public String detailPage(@PathVariable UUID id, Model model) {
        var product = productService.getProductById(id);
        model.addAttribute("product", product.get());
        return "product-detail";
    }

    @GetMapping("/new-product")
    public String newProductPage() {
        return "new-product";
    }

    @PostMapping("/products")
    public String createProduct(CreateProductRequest createProductRequest) {
        productService.createProduct(createProductRequest.name(),
                createProductRequest.category(),
                createProductRequest.price(),
                createProductRequest.description());
        return "redirect:/products";
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable UUID id) {
        productService.removeProductById(id);
        return "redirect:/products";
    }

    @PutMapping("/products/{id}")
    public String updateProduct(@PathVariable UUID id, CreateProductRequest createProductRequest){
        productService.updateProductProperties(
                id,
                createProductRequest.name(),
                createProductRequest.category(),
                createProductRequest.price(),
                createProductRequest.description());
        return "redirect:/products";
    }
}

package com.example.gccoffeeserver.service;

import com.example.gccoffeeserver.MySqlContainerInitializer;
import com.example.gccoffeeserver.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ProductServiceTest extends MySqlContainerInitializer {

    @Autowired
    ProductService productService;

    @AfterEach
    public void clear(){
        productService.removeAllProduct();
    }

    @Test
    @DisplayName("@Transactional Test : ID로 상품을 찾은 후 상품의 속성을 변경할 수 있다.")
    public void testUpdateProductById() {
        var product = productService.createProduct("coffee-bean", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        var updated = productService.updateProductProperties(product.getProductId(), "java-bean", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        assertThat(updated.get().getName(), is("java-bean"));
    }
}

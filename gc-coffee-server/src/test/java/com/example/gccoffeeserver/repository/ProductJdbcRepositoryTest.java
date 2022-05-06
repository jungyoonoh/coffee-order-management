package com.example.gccoffeeserver.repository;

import com.example.gccoffeeserver.MySqlContainerInitializer;
import com.example.gccoffeeserver.model.Category;
import com.example.gccoffeeserver.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.testcontainers.shaded.org.hamcrest.beans.SamePropertyValuesAs;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

class ProductJdbcRepositoryTest extends MySqlContainerInitializer {

    @Autowired
    ProductRepository productRepository;

    @AfterEach
    void clear(){
        productRepository.deleteAll();
    }

    @Test
    @DisplayName("ID로 상품을 찾을 수 있다.")
    void testFindById() {
        Product product = new Product("coffee-bean", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        productRepository.insert(product);
        Product findObject = productRepository.findById(product.getId()).get();
        assertThat(product, samePropertyValuesAs(findObject));
    }

    @Test
    @DisplayName("이름으로 상품을 찾을 수 있다.")
    void testFindByName() {
        Product product = new Product("coffee-bean", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        productRepository.insert(product);
        Product findObject = productRepository.findByName("coffee-bean").get();
        assertThat(product, samePropertyValuesAs(findObject));
    }

    @Test
    @DisplayName("같은 카테고리의 상품을 찾을 수 있다.")
    void testFindByCategory() {
        Product product = new Product("coffee-bean", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        Product product2 = new Product("coffee-bean2", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        productRepository.insert(product);
        productRepository.insert(product2);

        List<Product> coffeeBeanProducts = productRepository.findByCategory(Category.COFFEE_BEAN);
        assertThat(coffeeBeanProducts, everyItem(hasProperty("category", is(Category.COFFEE_BEAN))));
    }

    @Test
    @DisplayName("저장된 모든 상품을 반환한다.")
    void testFindAll() {
        Product product = new Product("coffee-bean", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        Product product2 = new Product("coffee-bean2", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        productRepository.insert(product);
        productRepository.insert(product2);

        List<Product> allProducts = productRepository.findAll();
        assertThat(allProducts, containsInAnyOrder(
                samePropertyValuesAs(product),
                samePropertyValuesAs(product2)
        ));
    }

    @Test
    @DisplayName("상품을 생성할 수 있다.")
    void testCreate() {
        Product product = new Product("coffee-bean", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        var find = productRepository.insert(product);

        assertThat(product, samePropertyValuesAs(find));
    }

    @Test
    @DisplayName("상품 정보를 업데이트 할 수 있다.")
    void testUpdate() {
        Product product = new Product("coffee-bean", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        productRepository.insert(product);

        product.updateProductProperties("java-bean", product.getCategory(), product.getPrice(), product.getDescription());
        productRepository.update(product);
        var update = productRepository.findById(product.getId()).get();
        assertThat(update.getName(), is("java-bean"));
    }

    @Test
    @DisplayName("저장된 모든 상품을 지울 수 있다.")
    void testDeleteAll() {
        Product product = new Product("coffee-bean", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        Product product2 = new Product("coffee-bean2", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        productRepository.insert(product);
        productRepository.insert(product2);
        productRepository.deleteAll();
        var allProducts = productRepository.findAll();
        assertThat(allProducts.isEmpty(), is(true));
    }

    @Test
    @DisplayName("특정 ID를 가지는 상품을 제거한다.")
    void testDeleteById() {
        Product product = new Product("coffee-bean", Category.COFFEE_BEAN, 5000, "산미 가득한 원두");
        productRepository.insert(product);
        productRepository.deleteById(product.getId());
        var find = productRepository.findById(product.getId());
        assertThat(find.isEmpty(), is(true));
    }
}
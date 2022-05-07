package com.example.gccoffeeserver.repository;

import com.example.gccoffeeserver.MySqlContainerInitializer;
import com.example.gccoffeeserver.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.samePropertyValuesAs;

public class OrderJdbcRepositoryTest extends MySqlContainerInitializer {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @AfterEach
    public void clear(){
        orderRepository.deleteAllOrder();
    }

    @Test
    @DisplayName("주문을 생성할 수 있다.")
    public void testInsertOrder(){

        var coffeeBean = new Product("JAVA_BEAN_COFFEE", Category.COFFEE_BEAN, 10000);
        productRepository.insert(coffeeBean);

        var order = new Order(new Email("xkakak142@naver.com"),
                "서울시 광진구 능동로 120",
                "05029",
                List.of(new OrderItem(coffeeBean.getProductId(), coffeeBean.getCategory(), coffeeBean.getPrice(), 3)));

        Order insert = orderRepository.insert(order);
        assertThat(order, samePropertyValuesAs(insert));
    }

    @Test
    @DisplayName("생성된 모든 주문을 반환한다.")
    public void testFindAllOrder(){
        var order = new Order(new Email("xkakak142@naver.com"),
                "서울시 광진구 능동로 120",
                "05029",
                List.of());

        var order2 = new Order(new Email("yoonoh123@naver.com"),
                "서울시 광진구 광나루로 536",
                "05032",
                List.of());

        orderRepository.insert(order);
        orderRepository.insert(order2);

        List<Order> allOrders = orderRepository.findAllOrders();
        assertThat(allOrders, containsInAnyOrder(
                samePropertyValuesAs(order),
                samePropertyValuesAs(order2)
        ));
    }

    @Test
    @DisplayName("특정 주문의 품목 리스트를 반환한다.")
    public void testFindOrderItemsByOrder(){
        var coffeeBean = new Product("JAVA_BEAN_COFFEE", Category.COFFEE_BEAN, 10000);
        var iceAmericano = new Product("아이스 아메리카노", Category.DRINK, 3000);

        productRepository.insert(coffeeBean);
        productRepository.insert(iceAmericano);

        List<OrderItem> items = new ArrayList<>();
        var orderItem1 = new OrderItem(coffeeBean.getProductId(), coffeeBean.getCategory(), coffeeBean.getPrice(), 3);
        var orderItem2 = new OrderItem(iceAmericano.getProductId(), iceAmericano.getCategory(), iceAmericano.getPrice(), 1);
        items.add(orderItem1);
        items.add(orderItem2);

        var order = new Order(new Email("xkakak142@naver.com"),
                "서울시 광진구 능동로 120",
                "05029",
                items);

        orderRepository.insert(order);

        var allItemsByOrder = orderRepository.findOrderItemsByOrderId(order.getOrderId());
        assertThat(allItemsByOrder, containsInAnyOrder(
                samePropertyValuesAs(orderItem1),
                samePropertyValuesAs(orderItem2)
        ));
    }
}

package com.market.order;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {

    List<Order> getOrders();

    void saveOrders(Order order);

    void init();
}



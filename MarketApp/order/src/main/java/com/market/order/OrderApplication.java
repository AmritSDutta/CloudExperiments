package com.market.order;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class OrderApplication {
    private static final Log LOGGER = LogFactory.getLog(OrderApplication.class);

    @Autowired
    private OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @GetMapping("/")
    public ResponseEntity<String> getStarted() {
        return new ResponseEntity("Order Service Up.", HttpStatus.OK);
    }

    @GetMapping("/init")
    public ResponseEntity<String> initialized() {
        orderRepository.init();
        return new ResponseEntity("Initialized Successfully.", HttpStatus.OK);
    }

    @GetMapping("/getOrders")
    public ResponseEntity<List<Order>> getOrderReq() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setOrderId("101");
        order.setItem("Red Shirt");
        order.setDescription("Puma red shirt");
        order.setComments("Soft cotton shirt");
        order.setQuantity(2l);
        orders.add(order);
        LOGGER.debug(orders);
        List<Order> otherOrders = orderRepository.getOrders();
        orders.addAll(otherOrders);

        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }

    @PostMapping("/saveOrder")
    public void saveOrder(@RequestBody Order order) {
        LOGGER.debug("hi : " + order);
        orderRepository.saveOrders(order);

    }

}

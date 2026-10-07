package com.example.kafkademo.controller;

import com.example.kafkademo.model.OrderEvent;
import com.example.kafkademo.producer.OrderProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderEvent orderEvent) {

        orderProducer.sendOrder(orderEvent);

        // Interview point: API user orderId bhejta hai; producer orderId ko Kafka key banata hai.
        return "Order event sent to Kafka with key/orderId: " + orderEvent.getOrderId();
    }
}

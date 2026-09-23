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

        return "Order event sent to Kafka";
    }
}
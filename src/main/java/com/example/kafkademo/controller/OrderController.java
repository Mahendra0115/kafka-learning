package com.example.kafkademo.controller;

import com.example.kafkademo.model.OrderEvent;
import com.example.kafkademo.producer.OrderProducer;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    // @PostMapping
    // public String createOrder(@RequestBody OrderEvent orderEvent) {

    //     orderProducer.sendOrder(orderEvent);

    //     return "Order event sent to Kafka";
    // }



    @PostMapping
    public CompletableFuture<ResponseEntity<String>> createOrder(
            @RequestBody OrderEvent orderEvent) {

        return orderProducer.sendOrder(orderEvent)
                .thenApply(result -> {

                    SendResult<String, OrderEvent> sendResult = result;

                    int partition = sendResult
                            .getRecordMetadata()
                            .partition();

                    long offset = sendResult
                            .getRecordMetadata()
                            .offset();

                    System.out.println(
                            "Order sent successfully. Order ID: "
                                    + orderEvent.getOrderId()
                    );

                    System.out.println("Partition: " + partition);
                    System.out.println("Offset: " + offset);

                    return ResponseEntity.ok(
                            "Order successfully acknowledged by Kafka. "
                                    + "Order ID: "
                                    + orderEvent.getOrderId()
                    );
                })
                .exceptionally(exception -> {

                    System.err.println(
                            "Failed to send order: "
                                    + exception.getMessage()
                    );

                    return ResponseEntity
                            .status(HttpStatus.SERVICE_UNAVAILABLE)
                            .body("Failed to publish order to Kafka");
                });
    }
}

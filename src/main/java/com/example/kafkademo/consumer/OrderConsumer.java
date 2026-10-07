package com.example.kafkademo.consumer;

import com.example.kafkademo.model.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    // Listen for incoming messages on the order-created topic
    @KafkaListener(
            topics = "order-created",
            groupId = "order-consumer-group"
    )
    public void consumeOrder(
            OrderEvent orderEvent,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {

        // Interview point: Kafka ordering topic level par nahi, partition level par guarantee karta hai.
        // Same key same partition me jayegi, isliye same orderId ke events ordered consume honge.
        System.out.println(
                "Kafka metadata -> key: " + key
                        + ", partition: " + partition
                        + ", offset: " + offset
        );

        // Print the order ID when the message is received
        System.out.println(
                "Order received: " + orderEvent.getOrderId()
        );

        // Print customer information from the message
        System.out.println(
                "Customer: " + orderEvent.getCustomerName()
        );

        // Print the purchased product name
        System.out.println(
                "Product: " + orderEvent.getProduct()
        );

        // Print the total order amount
        System.out.println(
                "Amount: " + orderEvent.getAmount()
        );
    }
}

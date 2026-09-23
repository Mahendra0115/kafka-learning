package com.example.kafkademo.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.kafkademo.model.OrderEvent;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(OrderEvent orderEvent) {

        // Send the order event to Kafka topic
        kafkaTemplate.send("order-created", orderEvent);

        // Log confirmation after sending the message
        System.out.println("Message sent: " + orderEvent);
    }
}
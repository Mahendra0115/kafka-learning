package com.example.kafkademo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(
            topics = "demo-topic",
            groupId = "kafka-demo-group"
    )
    public void consumeMessage(String message) {

        System.out.println("Message received: " + message);
    }
}
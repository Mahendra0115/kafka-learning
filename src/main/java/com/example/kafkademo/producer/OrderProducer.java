package com.example.kafkademo.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.kafkademo.model.OrderEvent;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

   public CompletableFuture<SendResult<String, OrderEvent>>
            sendOrder(OrderEvent orderEvent) {

        return kafkaTemplate.send("order-created", orderEvent);
    }


}

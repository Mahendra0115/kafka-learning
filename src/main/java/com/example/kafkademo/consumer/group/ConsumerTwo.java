package com.example.kafkademo.consumer.group;

import com.example.kafkademo.model.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerTwo {

    @KafkaListener(
            topics = "order-created",
            groupId = "order-processing-group"
    )
    public void consume(OrderEvent orderEvent) {

        System.out.println(
                "CONSUMER-2 received Order: "
                        + orderEvent.getOrderId()
        );
    }
}
package com.example.kafkademo.consumer.group;

import com.example.kafkademo.model.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerOne {

    @KafkaListener(
            topics = "order-created",
            groupId = "order-processing-group"
    )
    public void consume(OrderEvent orderEvent) {

        System.out.println(
                "CONSUMER-1 received Order: "
                        + orderEvent.getOrderId()
        );
    }
}
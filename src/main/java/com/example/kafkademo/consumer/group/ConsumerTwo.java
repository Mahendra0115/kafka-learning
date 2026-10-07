package com.example.kafkademo.consumer.group;

import com.example.kafkademo.model.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class ConsumerTwo {

    @KafkaListener(
            topics = "order-created",
            groupId = "order-processing-group"
    )
    public void consume(
            OrderEvent orderEvent,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition
    ) {

        // Interview point: Ek partition ek consumer group me ek time par sirf ek consumer ko assign hota hai.
        System.out.println(
                "CONSUMER-2 received Order: "
                        + orderEvent.getOrderId()
                        + ", key: " + key
                        + ", partition: " + partition
        );
    }
}

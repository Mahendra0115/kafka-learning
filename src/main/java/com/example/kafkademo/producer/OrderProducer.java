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

        // Interview point: Kafka key decide karta hai message kis partition me jayega.
        // Same key hamesha same topic ke same partition me jati hai, isliye ordering safe rehti hai.
        String kafkaKey = String.valueOf(orderEvent.getOrderId());

        // orderId ko key banane se same order ke related events same partition me rahenge.
        kafkaTemplate.send("order-created", kafkaKey, orderEvent)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        System.out.println("Message send failed for key: " + kafkaKey);
                        ex.printStackTrace();
                        return;
                    }

                    // Interview point: Producer metadata se partition/offset verify kar sakte hain.
                    System.out.println(
                            "Message sent with key: " + kafkaKey
                                    + ", partition: " + result.getRecordMetadata().partition()
                                    + ", offset: " + result.getRecordMetadata().offset()
                                    + ", order: " + orderEvent
                    );
                });
    }
}

package com.example.kafkademo.consumer.multigroup;

import com.example.kafkademo.model.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(
            topics = "order-created",
            groupId = "notification-group"
    )
    public void sendNotification(OrderEvent orderEvent) {

        System.out.println("===== NOTIFICATION SERVICE =====");
        System.out.println("Order ID: " + orderEvent.getOrderId());
        System.out.println("Customer: " + orderEvent.getCustomerName());
        System.out.println(
                "Notification: Your order for "
                        + orderEvent.getProduct()
                        + " has been received."
        );
    }
}

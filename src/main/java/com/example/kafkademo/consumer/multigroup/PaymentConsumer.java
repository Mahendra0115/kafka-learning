package com.example.kafkademo.consumer.multigroup;

import com.example.kafkademo.model.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @KafkaListener(
            topics = "order-created",
            groupId = "payment-group"
    )
    public void processPayment(OrderEvent orderEvent) {

        System.out.println("===== PAYMENT SERVICE =====");
        System.out.println("Order ID: " + orderEvent.getOrderId());
        System.out.println("Customer: " + orderEvent.getCustomerName());
        System.out.println("Product: " + orderEvent.getProduct());
        System.out.println("Amount: " + orderEvent.getAmount());

        System.out.println("Payment processing started.");
    }
}

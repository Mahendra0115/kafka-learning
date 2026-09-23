package com.example.kafkademo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic messageTopic() {
        // Create the Kafka topic used to publish order events
        // Topic name: order-created
        // 3 partitions allow messages to be spread across multiple partitions
        // 1 replica means one copy of the data is kept for redundancy
        return new NewTopic("order-created", 3, (short) 1);
    }
}
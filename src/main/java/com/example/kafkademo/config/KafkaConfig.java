package com.example.kafkademo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic messageTopic() {
        return new NewTopic("demo-topic", 1, (short) 1);
    
//        (short) 1      -----> Kafka me data duplicate copies rakhne ke liye use hota hai. Ex ->  1 ka matlab ek replica hi hai
//         "demo-topic"   ----> Yahi topic par messages send/consume hote hain. Example: orders, payments, user-signup, email-events
//             1          ----> Ek topic ke andar multiple partitions ho sakte hain. 

    }
}
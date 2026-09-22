package com.example.kafkademo.controller;

import com.example.kafkademo.producer.KafkaProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MessageController {

    private final KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/message")
    public String sendMessage(@RequestParam String message) {

        kafkaProducer.sendMessage(message);

        return "Message sent successfully";
    }
}
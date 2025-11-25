package com.rmq.example.publisher.controller;

import com.rmq.example.publisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class Publisher {

    @Value("${rabbitmq.queuename}")
    private String queueName;

    @Autowired
    PublisherService publisherService;

    @PostMapping("/publish/text")
    public void publishText(@RequestBody String text){
        System.out.println("Enviando mensagem: " + text);
        publisherService.publishTextMessage(text, queueName);
    }

    @PostMapping("/publish/json")
    public void publishJson(@RequestBody String text){
        System.out.println("Enviando mensagem: " + text);
        publisherService.publishJsonMessage(text, queueName);
    }
}
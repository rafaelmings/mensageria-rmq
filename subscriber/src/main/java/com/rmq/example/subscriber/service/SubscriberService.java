package com.rmq.example.subscriber.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rmq.example.subscriber.model.QueueMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.Message;

import java.nio.charset.StandardCharsets;

@Service
public class SubscriberService {

    private ObjectMapper objectMapper;

    @RabbitListener(containerFactory = "listenerContainerFactory", queues = "${rabbitmq.queuename}")
    public void receiveMessage(Message message) {
        String rawMessage = rmqMessageToString(message);

        if (isJson(rawMessage)) {
            try {
                QueueMessage msgObject = objectMapper.readValue(rawMessage, QueueMessage.class);
                System.out.println("JSON recebido: " + msgObject);
            } catch (Exception e) {
                System.out.println("Erro ao converter JSON: " + e.getMessage());
            }
        } else {
            System.out.println("Texto recebido: " + rawMessage);
        }
    }

    private String rmqMessageToString(Message message) {
        return new String(message.getBody(), StandardCharsets.UTF_8);
    }

    // Detecta JSON de forma forte
    private boolean isJson(String str) {
        if (str == null) return false;

        str = str.trim();

        // Só tenta parse se começar como JSON
        if (!(str.startsWith("{") || str.startsWith("["))) {
            return false;
        }

        try {
            objectMapper.readTree(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @PostConstruct
    public void init() {
        objectMapper = new ObjectMapper();
    }
}

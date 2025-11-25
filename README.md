# Projeto de Mensageria com Spring Boot e RabbitMQ 🚀

Este projeto demonstra um MVP funcional de mensageria utilizando
RabbitMQ como message broker e duas aplicações Spring Boot: Publisher e
Subscriber.

## Integrantes 👤

Luis Gustavo Rodrigues Ribeiro
Rafael Domingues de Oliveira

## Arquitetura 🏛️

    [Publisher API] ---> [RabbitMQ Queue] ---> [Subscriber Listener]

## Stack Tecnológico 🔧

-   Java 21
-   Spring Boot 3+
-   RabbitMQ
-   Docker
-   Spring AMQP
-   Maven

## Como Rodar ⚙️

### Subir RabbitMQ

    docker run -d --hostname rmq --name rabbitmq -p 8080:5672 -p 8090:15672 rabbitmq:3-management

### Rodar Publisher

    mvn spring-boot:run

### Rodar Subscriber

    mvn spring-boot:run

## Testar via PowerShell 📨

### Enviar texto

    Invoke-WebRequest -Uri "http://localhost:8081/publish/text" -Method POST -Body "Olá RabbitMQ!" -Headers @{ "Content-Type" = "text/plain" }

### Enviar JSON

    Invoke-WebRequest -Uri "http://localhost:8081/publish/json" -Method POST -Body '{"key1":"value1","key2":"value2"}' -Headers @{ "Content-Type" = "application/json" }

## Funcionalidades Entregues 📦

-   Envio e recebimento de texto
-   Envio e recebimento de JSON
-   Subscriber identifica o tipo da mensagem
-   Conversão JSON → Objeto
-   Fila criada automaticamente
-   MVP funcional

## Aprendizados ⚡

-   Comunicação assíncrona
-   Integração com RabbitMQ
-   Boas práticas de mensageria

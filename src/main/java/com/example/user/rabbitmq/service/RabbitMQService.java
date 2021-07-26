package com.example.user.rabbitmq.service;

public interface RabbitMQService {

    void sendEmail(String message) throws Exception;
}

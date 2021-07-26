package com.example.user.rabbitmq.service.impl;

import com.example.user.UserApplication;
import com.example.user.rabbitmq.service.RabbitMQService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

import static com.example.user.util.Constants.queueName;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    private static Logger logger = LoggerFactory.getLogger(RabbitMQServiceImpl.class);

    @Override
    public void sendEmail(String message){
        ConnectionFactory connectionFactory = new ConnectionFactory();

        try(Connection connection = connectionFactory.newConnection()) {

            Channel channel = connection.createChannel();
            channel.queueDeclare("email_queue", false, false, false, null);
            channel.basicPublish("", "email_queue", false, null, message.getBytes(StandardCharsets.UTF_8));

        }catch (Exception e){
            logger.error("RabbitMQServiceImpl.sendEmail", ExceptionUtils.getMessage(e));
        }
    }
}

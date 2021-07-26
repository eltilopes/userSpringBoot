package com.example.user.rabbitmq;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import com.example.user.UserApplication;
import com.example.user.entity.Email;
import com.example.user.rabbitmq.service.EmailService;
import com.example.user.util.ObjectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static com.example.user.util.Constants.queueName;

@Component
public class Runner implements CommandLineRunner {

    private final Receiver receiver;

    public Runner(Receiver receiver) {
        this.receiver = receiver;
    }
    @Resource
    private EmailService emailService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Test sending message RabbitMq ...");
        System.out.println("Sending email...");
        //emailService.sendEmail(new Email("elilopesgmail.com","elilopesgmail.com","Test sending message RabbitMq ..."));
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //String message = ObjectUtil.getJsonFromObject(new Email("elilopesgmail.com","elilopesgmail.com","Test sending message RabbitMq ..."));
        String message = "Mensagem de Teste";

        try(Connection connection = connectionFactory.newConnection()) {

            Channel channel = connection.createChannel();
            channel.queueDeclare("home", false, false, false, null);
            channel.basicPublish("", "home", false, null, message.getBytes(StandardCharsets.UTF_8));

        }catch (Exception e){
            System.out.println(ExceptionUtils.getMessage(e));
        }

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);

    }

}
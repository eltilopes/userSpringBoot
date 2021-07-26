package com.example.user.rabbitmq.service.impl;

import com.example.user.UserApplication;
import com.example.user.entity.Email;
import com.example.user.entity.User;
import com.example.user.rabbitmq.service.EmailService;
import com.example.user.rabbitmq.service.RabbitMQService;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;
import com.example.user.util.ObjectUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class EmailServiceImpl implements EmailService {

    private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private RabbitMQService rabbitMQService;

    @Autowired
    private UserService userService;

    @Override
    public void sendEmail(Email email) throws Exception {
        logger.info("EmailServiceImpl.senEmail : " + ObjectUtil.getJsonFromObject(email));
        if(ObjectUtil.isNullOrEmty(email.getSendTo())){
            List<User> users = userService.findAllAdmin(Boolean.TRUE);
            for(User user : users){
                email.setSendTo(user.getEmail());
                rabbitMQService.sendEmail(ObjectUtil.getJsonFromObject(email));
            }
        }
        rabbitMQService.sendEmail(ObjectUtil.getJsonFromObject(email));
    }
}
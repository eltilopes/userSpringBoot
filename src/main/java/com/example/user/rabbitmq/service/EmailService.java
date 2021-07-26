package com.example.user.rabbitmq.service;


import com.example.user.entity.Email;

public interface EmailService {
    /**
     * Send mail task to message queue
     * @param email
     * @throws Exception
     */
    void sendEmail(Email email) throws Exception;
}
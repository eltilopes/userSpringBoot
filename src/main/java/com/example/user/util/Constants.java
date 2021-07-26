package com.example.user.util;

public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "devglan123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";


    public static final String topicExchangeName = "user-exchange";
    public static final String routeKey = "email_routekey";
    public static final String queueName = "email_queue";

    public static final String receiveMessage = "receiveMessage";


}

package com.example.user.entity;

import java.io.Serializable;

public class Email implements Serializable {
    private String message;
    private String sendTo;
    private String fromTo;

    public Email( String sendTo, String fromTo,String message) {
        this.fromTo = fromTo;
        this.message = message;
        this.sendTo = sendTo;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getFromTo() {
        return fromTo;
    }

    public void setFromTo(String fromTo) {
        this.fromTo = fromTo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

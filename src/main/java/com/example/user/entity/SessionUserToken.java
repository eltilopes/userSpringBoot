package com.example.user.entity;


import java.io.Serializable;

public class SessionUserToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String token;

    public SessionUserToken(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

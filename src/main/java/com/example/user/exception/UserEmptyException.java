package com.example.user.exception;

public class UserEmptyException extends RuntimeException {

    public UserEmptyException() {
        super("User empty!");
    }
}
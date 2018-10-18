package com.codecool.spring.exception;

public class AirplaneModelNotFoundException extends RuntimeException {

    public AirplaneModelNotFoundException(String message) {
        super(message);
    }
}

package com.codecool.spring.exception;

public class AirplaneModelWrongDataException extends RuntimeException {

    public AirplaneModelWrongDataException(String message) {
        super(message);
    }
}

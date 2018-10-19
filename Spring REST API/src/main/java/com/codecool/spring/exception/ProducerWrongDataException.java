package com.codecool.spring.exception;

public class ProducerWrongDataException extends RuntimeException {
    public ProducerWrongDataException() {
        super("Wrong data.");
    }
}

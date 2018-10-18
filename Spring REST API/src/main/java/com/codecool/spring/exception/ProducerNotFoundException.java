package com.codecool.spring.exception;

public class ProducerNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProducerNotFoundException(long id) {
        super("No producer for id: " + id);
    }
}

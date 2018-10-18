package com.codecool.spring.controller;

import com.codecool.spring.exception.ProducerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProducerExceptionController {
    @ExceptionHandler(value= ProducerNotFoundException.class)
    public ResponseEntity<Object> exception(ProducerNotFoundException exception) {
        return new ResponseEntity<>("Producer not found", HttpStatus.NOT_FOUND);
    }
}

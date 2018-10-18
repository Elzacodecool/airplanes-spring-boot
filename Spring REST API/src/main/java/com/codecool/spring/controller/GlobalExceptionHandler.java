package com.codecool.spring.controller;

import com.codecool.spring.exception.AirplaneModelNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AirplaneModelNotFoundException.class})
    public final ResponseEntity<ErrorMessage> handleExceptions(Exception ex, HttpServletRequest request) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorMessage errorMessageResponse = new ErrorMessage(new Timestamp(System.currentTimeMillis()), status.toString(),
                ex.getMessage(), request.getRequestURL().toString());
        return new ResponseEntity<>(errorMessageResponse, headers, status);
    }
}
}

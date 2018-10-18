package com.codecool.spring.controller;

import com.codecool.spring.exception.AirplaneModelNotFoundException;
import com.codecool.spring.exception.AirplaneModelWrongDataException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AirplaneModelNotFoundException.class, AirplaneModelWrongDataException.class})
    public final ResponseEntity<ErrorMessage> handleExceptions(Exception ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof AirplaneModelNotFoundException) status = HttpStatus.NOT_FOUND;
        if (ex instanceof AirplaneModelWrongDataException) status = HttpStatus.BAD_REQUEST;

        HttpHeaders headers = new HttpHeaders();
        ErrorMessage errorMessageResponse = new ErrorMessage(new Timestamp(System.currentTimeMillis()), status.toString(),
                ex.getMessage(), request.getRequestURL().toString());
        return new ResponseEntity<>(errorMessageResponse, headers, status);
    }
}

class ErrorMessage {

    private String message;
    private String path;
    private Timestamp timestamp;
    private String status;

    public ErrorMessage(Timestamp timestamp, String status, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String details) {
        this.path = path;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

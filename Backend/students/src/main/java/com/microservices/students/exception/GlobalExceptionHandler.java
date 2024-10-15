package com.microservices.students.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<Object> handleEmailAlreadyRegistered(EmailAlreadyRegisteredException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new EmailAlreadyRegisteredResponse("ERROR", ex.getMessage(), ex.getStudent())
        );
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Object> handleGeneralException(GeneralException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ErrorResponse("ERROR", ex.getMessage())
        );
    }

    // You can add more exception handlers for different exceptions as needed
}

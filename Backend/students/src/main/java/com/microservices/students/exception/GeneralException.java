package com.microservices.students.exception;

public class GeneralException extends RuntimeException {
    public GeneralException(String message) {
        super(message);
    }
}
package com.microservices.teacher.exception;

public class GeneralException extends RuntimeException {
    public GeneralException(String message) {
        super(message);
    }
}
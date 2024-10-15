package com.microservices.students.exception;

import com.microservices.students.domain.Student;

public class EmailAlreadyRegisteredException extends RuntimeException {
    private Student student;
    public EmailAlreadyRegisteredException(String message, Student student) {
        super(message);
        this.student = student;
    }

    public Student getStudent() {
        return student; 
    }
}

package com.microservices.teacher.exception;

import com.microservices.teacher.model.Teacher;

public class EmailAlreadyRegisteredException extends RuntimeException {
    private Teacher teacher;
    public EmailAlreadyRegisteredException(String message, Teacher teacher) {
        super(message);
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher; 
    }
}

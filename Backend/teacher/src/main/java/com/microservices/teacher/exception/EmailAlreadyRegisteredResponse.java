package com.microservices.teacher.exception;

import com.microservices.teacher.model.Teacher;

public class EmailAlreadyRegisteredResponse extends ErrorResponse {
    private Teacher teacher;
    public EmailAlreadyRegisteredResponse(String status, String message, Teacher teacher) {
        super(status, message);
        this.teacher = teacher;
    }
    

    public Teacher getStudent() {
        return teacher;
    }

    public void setStudent(Teacher teacher) {
        this.teacher = teacher;
    }
}

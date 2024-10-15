package com.microservices.students.exception;

import com.microservices.students.domain.Student;

public class EmailAlreadyRegisteredResponse extends ErrorResponse {
    private Student student;
    public EmailAlreadyRegisteredResponse(String status, String message, Student student) {
        super(status, message);
        this.student = student;
    }
    

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

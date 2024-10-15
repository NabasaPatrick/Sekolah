package com.microservices.course_enrollment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String phoneNumber;
    private String qualifications;
    private TeacherRole role; // Enum for teacher roles (e.g., HEADMASTER, ADMIN, TEACHER)
}

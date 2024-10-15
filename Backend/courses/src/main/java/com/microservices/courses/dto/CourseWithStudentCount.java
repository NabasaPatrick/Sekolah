package com.microservices.courses.dto;

import lombok.Data;

@Data
public class CourseWithStudentCount {
    private Long id; // Changed from Integer
    private String name;
    private String description;
    private String grade;
    private int studentCount;

    // ... (constructors and getters/setters)
}
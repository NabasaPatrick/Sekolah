package com.microservices.courses.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.courses.model.StudentCourse;
import com.microservices.courses.service.EnrolledCourseService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/enroll")
@AllArgsConstructor
public class EnrolledCourseContoller {
    
    private final EnrolledCourseService ecs;

    @GetMapping("/student/{courseId}")
    public ResponseEntity<List<StudentCourse>> getCourseById(@PathVariable Long courseId) {
        List<StudentCourse> studentCourses = ecs.getStudentEnrolledByCourse(courseId);
        return studentCourses != null ? ResponseEntity.ok(studentCourses) : ResponseEntity.notFound().build();
    }
}

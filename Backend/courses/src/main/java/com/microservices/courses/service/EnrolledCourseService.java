package com.microservices.courses.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.courses.model.StudentCourse;
import com.microservices.courses.repository.StudentCourseRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnrolledCourseService {
    private final StudentCourseRepository scr;

     public List<StudentCourse> getStudentEnrolledByCourse(Long courseId) {
        return scr.findAllByCourseId(courseId);
    }

}

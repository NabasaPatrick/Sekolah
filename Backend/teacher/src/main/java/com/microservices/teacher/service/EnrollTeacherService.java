package com.microservices.teacher.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservices.teacher.model.TeacherCourse;
import com.microservices.teacher.repository.TeacherCourseRepostiry;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EnrollTeacherService {
    private final TeacherCourseRepostiry tcr;

     public List<TeacherCourse> getStudentEnrolledByCourse(Long courseId) {
        return tcr.findAllByCourseId(courseId);
    }

}
package com.microservices.teacher.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.teacher.model.TeacherCourse;

@Repository
public interface TeacherCourseRepostiry  extends JpaRepository<TeacherCourse, Long> {
    List<TeacherCourse> findAllByCourseId(Long courseId);
} 

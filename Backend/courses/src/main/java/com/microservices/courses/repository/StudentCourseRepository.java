package com.microservices.courses.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.courses.model.StudentCourse;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    List<StudentCourse> findAllByCourseId(Long courseId);
}


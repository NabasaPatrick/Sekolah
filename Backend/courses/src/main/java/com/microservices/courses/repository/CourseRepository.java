package com.microservices.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.courses.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}

package com.microservices.students.infrastucture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.students.domain.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}

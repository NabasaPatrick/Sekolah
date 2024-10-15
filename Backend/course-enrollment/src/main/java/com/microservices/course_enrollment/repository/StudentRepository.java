package com.microservices.course_enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.course_enrollment.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}

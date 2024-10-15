package com.microservices.course_enrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.course_enrollment.model.Teacher;
import com.microservices.course_enrollment.model.TeacherRole;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findByRole(TeacherRole role);
}

package com.microservices.teacher.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.teacher.model.Teacher;
import com.microservices.teacher.model.TeacherRole;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByRole(TeacherRole role);
    Optional<Teacher> findByEmail(String email);
}

package com.microservices.students.infrastucture.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.students.domain.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByName(String name);
    Optional<Student> findByEmail(String email);
}

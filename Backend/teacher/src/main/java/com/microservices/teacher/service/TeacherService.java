package com.microservices.teacher.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservices.teacher.exception.EmailAlreadyRegisteredException;
import com.microservices.teacher.model.Teacher;
import com.microservices.teacher.model.TeacherRole;
import com.microservices.teacher.repository.TeacherRepository;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public Teacher createTeacher(Teacher teacher) {
        Optional<Teacher> existingTeacher = teacherRepository.findByEmail(teacher.getEmail());
        System.out.println(existingTeacher.isPresent());
        if (existingTeacher.isPresent()) {
            Teacher st = existingTeacher.get();
            throw new EmailAlreadyRegisteredException("Email already registered", st);
        }
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        Teacher existingTeacher = teacherRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Teacher Not Found"));
        existingTeacher.setName(updatedTeacher.getName());
        existingTeacher.setEmail(updatedTeacher.getEmail());
        existingTeacher.setPhoneNumber(updatedTeacher.getPhoneNumber());
        existingTeacher.setQualifications(updatedTeacher.getQualifications());
        existingTeacher.setRole(updatedTeacher.getRole());
        existingTeacher.setCourses(updatedTeacher.getCourses());
        return teacherRepository.save(existingTeacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public List<Teacher> getTeachersByRole(TeacherRole role) {
        return teacherRepository.findByRole(role);
    }
}

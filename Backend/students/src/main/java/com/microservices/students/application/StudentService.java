package com.microservices.students.application;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservices.students.domain.Student;
import com.microservices.students.exception.EmailAlreadyRegisteredException;
import com.microservices.students.infrastucture.repository.CourseRepository;
import com.microservices.students.infrastucture.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {
        Optional<Student> existingStudent = studentRepository.findByEmail(student.getEmail());
        System.out.println(existingStudent.isPresent());
        if (existingStudent.isPresent()) {
            Student st = existingStudent.get();
            throw new EmailAlreadyRegisteredException("Email already registered", st);
        }
    
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Student Not Found"));
    
        existingStudent.setName(updatedStudent.getName());
        existingStudent.setAge(updatedStudent.getAge());
        existingStudent.setCourses(updatedStudent.getCourses());
        existingStudent.setEmail(updatedStudent.getEmail());
        
    
        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
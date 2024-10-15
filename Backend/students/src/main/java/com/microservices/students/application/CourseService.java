package com.microservices.students.application;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.microservices.students.domain.Course;
import com.microservices.students.infrastucture.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Implement methods for CRUD operations, such as:
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course updatedCourse) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Course Not Found"));
    
        existingCourse.setName(updatedCourse.getName());
        existingCourse.setDescription(updatedCourse.getDescription());
        
    
        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        // ...
    }
}

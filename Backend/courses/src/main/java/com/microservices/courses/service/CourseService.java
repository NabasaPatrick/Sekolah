package com.microservices.courses.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.microservices.courses.model.Course;
import com.microservices.courses.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;


    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getAllCoursesWithCount() {
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
        // Add other fields as needed
        return courseRepository.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
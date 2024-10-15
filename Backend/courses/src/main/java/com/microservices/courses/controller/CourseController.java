package com.microservices.courses.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.courses.dto.CourseWithStudentCount;
import com.microservices.courses.model.Course;
import com.microservices.courses.service.CourseService;
import com.microservices.courses.service.CourseWithCountService;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private final CourseService courseService;
    private final CourseWithCountService cwcs;

    public CourseController(CourseService courseService, CourseWithCountService cwcs) {
        this.courseService = courseService;
        this.cwcs = cwcs;
    }

    @GetMapping
    public ResponseEntity<List<CourseWithStudentCount>> getAllCourses() {
        System.out.println("tessstt");
        List<CourseWithStudentCount> a = cwcs.findCoursesWithStudentCount();
        return ResponseEntity.ok(a);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return course != null ? ResponseEntity.ok(course) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.created(URI.create("/courses/" + createdCourse.getId())).body(createdCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        Course update = courseService.updateCourse(id, updatedCourse);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}

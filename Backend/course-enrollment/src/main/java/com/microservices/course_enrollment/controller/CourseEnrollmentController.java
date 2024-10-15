package com.microservices.course_enrollment.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.course_enrollment.model.Course;
import com.microservices.course_enrollment.model.CourseEnrollment;
import com.microservices.course_enrollment.model.Student;
import com.microservices.course_enrollment.model.Teacher;
import com.microservices.course_enrollment.service.CourseEnrollmentService;

@RestController
@RequestMapping("/course-enrollments")
public class CourseEnrollmentController {

    private final CourseEnrollmentService courseEnrollmentService;

    public CourseEnrollmentController(CourseEnrollmentService courseEnrollmentService) {
        this.courseEnrollmentService = courseEnrollmentService;
    }

    @PostMapping
    public ResponseEntity<CourseEnrollment> enrollStudentInCourse(@PathVariable Long studentId, @PathVariable Long courseId, @RequestBody Teacher teacher) {
        CourseEnrollment courseEnrollment = courseEnrollmentService.enrollStudentInCourse(studentId, courseId, teacher.getId());
        return ResponseEntity.created(URI.create("/course-enrollments/" + courseEnrollment.getId())).body(courseEnrollment);
    }

    @DeleteMapping("/{studentId}/{courseId}")
    public ResponseEntity<Void> unenrollStudentFromCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        courseEnrollmentService.unenrollStudentFromCourse(studentId, courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<List<Course>> getCoursesEnrolledByStudent(@PathVariable Long studentId) {
        List<Course> courses = courseEnrollmentService.getCoursesEnrolledByStudent(studentId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/by-course/{courseId}")
    public ResponseEntity<List<Student>> getStudentsEnrolledInCourse(@PathVariable Long courseId) {
        List<Student> students = courseEnrollmentService.getStudentsEnrolledInCourse(courseId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/pending-applications/{courseId}")
    public ResponseEntity<List<Teacher>> getPendingTeacherApplications(@PathVariable Long courseId) {
        List<Teacher> pendingTeachers = courseEnrollmentService.getPendingTeacherApplicationsForCourse(courseId);
        return ResponseEntity.ok(pendingTeachers);
    }

    @PutMapping("/approve-teacher/{courseId}/{teacherId}")
    public ResponseEntity<Void> approveTeacherApplication(@PathVariable Long courseId, @PathVariable Long teacherId) {
        courseEnrollmentService.approveTeacherApplication(courseId, teacherId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/reject-teacher/{courseId}/{teacherId}")
    public ResponseEntity<Void> rejectTeacherApplication(@PathVariable Long courseId, @PathVariable Long teacherId) {
        courseEnrollmentService.rejectTeacherApplication(courseId, teacherId);
        return ResponseEntity.noContent().build();
    }
}

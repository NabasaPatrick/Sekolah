package com.microservices.teacher.controller;

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

import com.microservices.teacher.model.Teacher;
import com.microservices.teacher.model.TeacherCourse;
import com.microservices.teacher.model.TeacherRole;
import com.microservices.teacher.service.EnrollTeacherService;
import com.microservices.teacher.service.TeacherService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private EnrollTeacherService ets;


    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Teacher teacher = teacherService.getTeacherById(id);
        return teacher != null ? ResponseEntity.ok(teacher) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.createTeacher(teacher);
        return ResponseEntity.created(URI.create("/teachers/" + createdTeacher.getId())).body(createdTeacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher updatedTeacher) {
        Teacher update = teacherService.updateTeacher(id, updatedTeacher);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-role/{role}")
    public ResponseEntity<List<Teacher>> getTeachersByRole(@PathVariable TeacherRole role) {
        List<Teacher> teachers = teacherService.getTeachersByRole(role);
        return ResponseEntity.ok(teachers);
    }


    @GetMapping("/enroll/{courseId}")
    public ResponseEntity<List<TeacherCourse>> getTeachersEnrollByCourse(@PathVariable Long courseId) {
        List<TeacherCourse> teachers = ets.getStudentEnrolledByCourse(courseId);
        return ResponseEntity.ok(teachers);
    }
}
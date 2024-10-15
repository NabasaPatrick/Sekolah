package com.microservices.course_enrollment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.course_enrollment.model.CourseEnrollment;
import com.microservices.course_enrollment.model.TeacherRole;

@Repository
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {

    List<CourseEnrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);

    List<CourseEnrollment> findByCourseId(Long courseId);

    List<CourseEnrollment> findByStudentId(Long studentId);

    List<CourseEnrollment> findByCourseIdAndTeacherRole(Long courseId, TeacherRole role);

    List<CourseEnrollment> findByCourseIdAndTeacherId(Long courseId, Long teacherId);
}

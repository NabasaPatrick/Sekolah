package com.microservices.course_enrollment.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.microservices.course_enrollment.model.Course;
import com.microservices.course_enrollment.model.CourseEnrollment;
import com.microservices.course_enrollment.model.Student;
import com.microservices.course_enrollment.model.Teacher;
import com.microservices.course_enrollment.model.TeacherRole;
import com.microservices.course_enrollment.model.TeacherStatus;
import com.microservices.course_enrollment.repository.CourseEnrollmentRepository;
import com.microservices.course_enrollment.repository.CourseRepository;
import com.microservices.course_enrollment.repository.StudentRepository;
import com.microservices.course_enrollment.repository.TeacherRepository;

@Service
public class CourseEnrollmentService {

    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public CourseEnrollmentService(CourseEnrollmentRepository courseEnrollmentRepository,
                                   StudentRepository studentRepository,
                                   CourseRepository courseRepository,
                                   TeacherRepository teacherRepository) {
        this.courseEnrollmentRepository = courseEnrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    public CourseEnrollment enrollStudentInCourse(Long studentId, Long courseId, Long teacherId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("student Not Found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("course Not Found"));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new NoSuchElementException("Teacher Not Found"));
        CourseEnrollment courseEnrollment = new CourseEnrollment();
        courseEnrollment.setStudent(student);
        courseEnrollment.setCourse(course);
        courseEnrollment.setTeacher(teacher);

        return courseEnrollmentRepository.save(courseEnrollment);
    }

    public void unenrollStudentFromCourse(Long studentId, Long courseId) {
        CourseEnrollment courseEnrollment = courseEnrollmentRepository.findByStudentIdAndCourseId(studentId, courseId).getFirst();

        courseEnrollmentRepository.delete(courseEnrollment);
    }

    public List<Course> getCoursesEnrolledByStudent(Long studentId) {
        return courseEnrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(CourseEnrollment::getCourse)
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsEnrolledInCourse(Long courseId) {
        return courseEnrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(CourseEnrollment::getStudent)
                .collect(Collectors.toList());
    }

    public List<Teacher> getPendingTeacherApplicationsForCourse(Long courseId) {
        return courseEnrollmentRepository.findByCourseIdAndTeacherRole(courseId, TeacherRole.TEACHER)
                .stream()
                .map(CourseEnrollment::getTeacher)
                .collect(Collectors.toList());
    }

    public void approveTeacherApplication(Long courseId, Long teacherId) {
        CourseEnrollment courseEnrollment = courseEnrollmentRepository.findByCourseIdAndTeacherId(courseId, teacherId).getFirst();

        courseEnrollment.setTeacherStatus(TeacherStatus.APPROVED);
        courseEnrollmentRepository.save(courseEnrollment);
    }

    public void rejectTeacherApplication(Long courseId, Long teacherId) {
        CourseEnrollment courseEnrollment = courseEnrollmentRepository.findByCourseIdAndTeacherId(courseId, teacherId).getFirst();

        courseEnrollment.setTeacherStatus(TeacherStatus.REJECTED);
        courseEnrollmentRepository.save(courseEnrollment);
    }
}
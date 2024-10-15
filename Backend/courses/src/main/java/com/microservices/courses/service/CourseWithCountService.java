package com.microservices.courses.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.courses.dto.CourseWithStudentCount;
import com.microservices.courses.model.Course;
import com.microservices.courses.model.StudentCourse;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

@Service
public class CourseWithCountService {

    @Autowired
    private EntityManager entityManager;

    public List<CourseWithStudentCount> findCoursesWithStudentCount() {
        String sql = "SELECT c.id as id, c.name as name, c.description as description, c.grade as gradde, COUNT(sc.course_id) AS studentCount FROM course c JOIN student_course sc ON c.id = sc.course_id GROUP BY c.id;";
        Query query = entityManager.createNativeQuery(sql);

        List<Object[]> results = query.getResultList();

        List<CourseWithStudentCount> courseWithStudentCounts = new ArrayList<>();

        // Iterate through the results and create CourseWithStudentCount objects
        for (Object[] row : results) {
            CourseWithStudentCount course = new CourseWithStudentCount();
            course.setId(((Number) row[0]).longValue()); // Cast and set id
            course.setName((String) row[1]);           // Set name
            course.setDescription((String) row[2]);    // Set description
            course.setGrade((String) row[3]);           // Set grade
            course.setStudentCount(((Number) row[4]).intValue()); // Cast and set studentCount

            courseWithStudentCounts.add(course);
        }
        return courseWithStudentCounts;
    }
}
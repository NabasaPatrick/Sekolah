<template>
  <div class="detail-course">
    <h2>Course Details</h2>
    <div v-if="course" class="course-info">
      <p>Course Name: {{ course.name }}</p>
      <p>Grade: {{ course.grade }}</p>
      <p>Description: {{ course.description }}</p>
    </div>
    <div v-else>
      <p>Loading course details...</p>
    </div>
    <div class="student-list">
      <h3>Enrolled Teacher</h3>
      <ul v-if="teacherCourse">
        <li v-for="teacher in teacherCourse" :key="teacher.teacher.id">
          {{ teacher.teacher.name }}
        </li>
      </ul>
      <ul v-else>
        <li>
          <p>Loading teacher list...</p>
        </li>
      </ul>
    </div>
    <div class="student-list">
      <h3>Enrolled Students</h3>
      <ul v-if="studentCourse">
        <li v-for="student in studentCourse" :key="student.student.id">
          {{ student.student.name }}
        </li>
      </ul>
      <ul v-else>
        <li>
          <p>Loading student list...</p>
        </li>
      </ul>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import axios from 'axios'
import { useRoute } from 'vue-router'

export default defineComponent({
  setup() {
    const course = ref(null)
    const studentCourse = ref(null)
    const teacherCourse = ref(null)
    const isLoading = ref(true)
    const route = useRoute() // Use Vue Router's useRoute hook to access route params

    const fetchCourseDetails = () => {
      const courseId = route.params.courseId
      axios
        .get(`http://localhost:8081/courses/${courseId}`)
        .then(response => {
          course.value = response.data
          isLoading.value = false
        })
        .catch(error => {
          console.error(error)
        })
    }

    const fetchStudentCourseDetails = () => {
      const courseId = route.params.courseId
      axios
        .get(`http://localhost:8081/enroll/student/${courseId}`)
        .then(response => {
          studentCourse.value = response.data
        })
        .catch(error => {
          console.error(error)
        })
    }

    const fetchTeacherCourseDetails = () => {
      const courseId = route.params.courseId
      axios
        .get(`http://localhost:8082/teachers/enroll/${courseId}`)
        .then(response => {
          teacherCourse.value = response.data
        })
        .catch(error => {
          console.error(error)
        })
    }

    // Fetch course details on component mount
    fetchCourseDetails()
    fetchStudentCourseDetails()
    fetchTeacherCourseDetails()

    return {
      course,
      isLoading,
      studentCourse,
      teacherCourse,
    }
  },
})
</script>

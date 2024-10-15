<template>
  <div class="student-portal">
    <h2>Teacher Portal</h2>
    <div class="register-button">
      <button @click="navigateToStudentRegister">Register to School</button>
    </div>
    <div class="course-list">
      <h3>List of Available Courses in Our School</h3>
      <table class="course-table">
        <thead>
          <tr>
            <th>Course Name</th>
            <th>Grade</th>
            <th>Total Students</th>
            <th>Details</th>
            <th>Enroll</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="course in availableCourses" :key="course.id">
            <td>{{ course.name }}</td>
            <td>{{ course.grade }}</td>
            <td>{{ course.studentCount }}</td>
            <td>
              <button @click="navigateToDetailsCourses(course.id)">
                Details
              </button>
            </td>
            <td>
              <button @click="navigateToStudentEnroll(course.id)">
                Enroll
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<style scoped>
.course-table {
  border-collapse: collapse;
  width: 100%;
}

.course-table th,
.course-table td {
  border: 1px solid #000;
  padding: 8px;
  text-align: left;
}

.course-table th {
  background-color: #f2f2f2;
}

.course-table tr:nth-child(even) {
  background-color: #f9f9f9;
}
</style>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import axios from 'axios'
import { useRouter } from 'vue-router'
import DetailCourse from '../components/DetailCourse.vue'
import EnrollmentForm from '../components/EnrollmentForm.vue'

export default defineComponent({
  setup() {
    const router = useRouter()

    return {
      showCourseDetailsModal: ref(false),
      selectedCourse: ref(null),
      handleCloseDetailsModal() {
        this.showCourseDetailsModal = false
        this.selectedCourse = null // Clear selected course
      },
      handleShowDetailsModal(course) {
        this.showCourseDetailsModal = true
        this.selectedCourse = course
      },
      navigateToDetailsCourses(courseId) {
        router.push(`/detail-course/${courseId}`)
      },
      navigateToStudentEnroll(courseId) {
        router.push(`/enrollment-course/teacher/${courseId}`)
      },
      navigateToStudentRegister() {
        router.push('/register-teacher')
      },
    }
  },
  data() {
    return {
      availableCourses: [],
    }
  },
  mounted() {
    this.fetchCourses()
  },
  methods: {
    fetchCourses() {
      axios
        .get('http://localhost:8081/courses')
        .then(response => {
          this.availableCourses = response.data
        })
        .catch(error => {
          console.error(error)
        })
    },
  },
  components: {
    DetailCourse,
    EnrollmentForm,
  },
})
</script>

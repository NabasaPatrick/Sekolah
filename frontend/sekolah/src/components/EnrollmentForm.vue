<template>
  <div>
    <h2>Register {{ type }}</h2>

    <!-- Show form if registration is not yet successful -->
    <form
      @submit.prevent="submitForm"
      v-if="!registrationSuccess && !registrationFail && type == 'student'"
    >
      <div>
        <label for="name">Name:</label>
        <input type="text" v-model="student.name" id="name" required />
      </div>

      <div>
        <label for="email">Email:</label>
        <input type="email" v-model="student.email" id="email" required />
      </div>

      <div>
        <label for="id">StudentId:</label>
        <input type="number" v-model="student.id" id="id" required />
      </div>

      <button type="submit">Enroll</button>
    </form>

    <form
      @submit.prevent="submitForm"
      v-if="!registrationSuccess && !registrationFail && type == 'teacher'"
    >
      <div>
        <label for="name">Name:</label>
        <input type="text" v-model="teacher.name" id="name" required />
      </div>

      <div>
        <label for="email">Email:</label>
        <input type="email" v-model="teacher.email" id="email" required />
      </div>

      <div>
        <label for="id">phoneNumber:</label>
        <input
          type="number"
          v-model="teacher.phoneNumber"
          id="phoneNumber"
          required
        />
      </div>

      <div>
        <label for="id">TeacherId:</label>
        <input type="number" v-model="teacher.id" id="id" required />
      </div>

      <button type="submit">Enroll</button>
    </form>

    <div v-if="registrationSuccess">
      <h3>Registration Successful!</h3>
      <p>
        Your Student ID is: <strong>{{ registeredStudentId }}</strong>
      </p>
      <button @click="proceedToHome">Proceed to Home</button>
    </div>

    <div v-if="registrationFail">
      <h3>Registration Failed!</h3>
      <p>your email already register</p>
      <p>studentid: {{ registeredStudentId }}</p>
      <p>studentEmail: {{ registeredStudentEmail }}</p>
      <p>studentName: {{ registeredStudentName }}</p>
      <button @click="proceedToHome">Proceed to Home</button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import { Student } from '../interface/Student'
import { Teacher } from '../interface/Teacher'
import axios from 'axios'
import { useRouter, useRoute } from 'vue-router'

export default defineComponent({
  name: 'RegisterStudent',
  setup() {
    const route = useRoute()
    const student = ref<Student>({
      name: '',
      age: 0,
      grade: 0,
      email: '',
      course: [],
      id: 0,
    })

    const teacher = ref<Teacher>({
      name: '',
      email: '',
      phonenumber: 0,
      qualification: '',
      role: 'TEACHER',
      id: 0,
      course: [],
    })

    const registrationSuccess = ref(false) // To track if registration was successful
    const registrationFail = ref(false)
    const registeredStudentId = ref<string | null>(null) // To store the student ID
    const router = useRouter() // Access to router for navigation
    const registeredStudentEmail = ref<string | null>(null)
    const registeredStudentName = ref<string | null>(null)
    const courseId = route.params.courseId
    const type = route.params.type
    const submitForm = async () => {
      const studentData = {
        ...student.value,
        grade: student.value.grade.toString(),
      }
      const teacherData = {
        ...teacher.value,
        grade: teacher.value.phonenumber.toString(),
      }
      try {
        // Make a POST request to the backend to register the student
        if (type == 'student') {
          const response = await axios.get(
            `http://localhost:8080/students/${studentData.id}`,
          )
          const data = response.data
          console.log('Student data retrieve successfully', data)
          const courseExists = data.courses.some(value => value.id == courseId)
          if (!courseExists) {
            data.courses.push({ id: Number(courseId) })
            console.log('Student data update successfully', data)
            const responses = await axios.put(
              `http://localhost:8080/students/${studentData.id}`,
              data,
            )
            console.log('Student data update successfully', responses)
          }

          registeredStudentId.value = response.data.id
          console.log(studentData)
          registrationSuccess.value = true
        } else {
          const response = await axios.get(
            `http://localhost:8082/teachers/${teacherData.id}`,
          )
          const data = response.data
          console.log('Teacher data retrieve successfully', data)
          const courseExists = data.courses.some(value => value.id == courseId)
          if (!courseExists) {
            data.courses.push({ id: Number(courseId) })
            console.log('Student data update successfully', data)
            const responses = await axios.put(
              `http://localhost:8082/teachers/${teacherData.id}`,
              data,
            )
            console.log('Teacher data update successfully', responses)
            registeredStudentId.value = response.data.id
            console.log(studentData)
            registrationSuccess.value = true
          }
        }
      } catch (error) {
        console.log('asdasdas', error.response.data)
        // registeredStudentId.value = error.response.data.student.id
        // registeredStudentEmail.value = error.response.data.student.email
        // registeredStudentName.value = error.response.data.student.name
        // registrationFail.value = true
      }
    }

    const proceedToHome = () => {
      router.push('/') // Navigate to the home page
    }

    return {
      student,
      teacher,
      submitForm,
      registrationSuccess,
      registrationFail,
      registeredStudentId,
      registeredStudentEmail,
      registeredStudentName,
      proceedToHome,
      type,
    }
  },
})
</script>

<style scoped>
/* Basic styling for the form */
form {
  max-width: 400px;
  margin: 0 auto;
  padding: 1rem;
  border: 1px solid #ccc;
  border-radius: 5px;
}

div {
  margin-bottom: 1rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
}

input {
  width: 100%;
  padding: 0.5rem;
  border-radius: 3px;
  border: 1px solid #ccc;
}

button {
  padding: 0.5rem 1rem;
  background-color: #034694;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 3px;
}

button:hover {
  background-color: #022e5e;
}
</style>

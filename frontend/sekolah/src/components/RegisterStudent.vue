<template>
  <div>
    <h2>Register Student</h2>

    <!-- Show form if registration is not yet successful -->
    <form
      @submit.prevent="submitForm"
      v-if="!registrationSuccess && !registrationFail"
    >
      <div>
        <label for="name">Name:</label>
        <input type="text" v-model="student.name" id="name" required />
      </div>

      <div>
        <label for="age">Age:</label>
        <input type="number" v-model="student.age" id="age" required />
      </div>

      <div>
        <label for="grade">Grade:</label>
        <input type="number" v-model="student.grade" id="grade" required />
      </div>

      <div>
        <label for="email">Email:</label>
        <input type="email" v-model="student.email" id="email" required />
      </div>

      <button type="submit">Register</button>
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
import { Student } from '@/interfaces/Student' // Adjust the path based on your project structure
import axios from 'axios'
import { useRouter } from 'vue-router'

export default defineComponent({
  name: 'RegisterStudent',
  setup() {
    const student = ref<Student>({
      name: '',
      age: 0,
      grade: 0,
      email: '',
      course: [],
    })

    const registrationSuccess = ref(false) // To track if registration was successful
    const registrationFail = ref(false)
    const registeredStudentId = ref<string | null>(null) // To store the student ID
    const router = useRouter() // Access to router for navigation
    const registeredStudentEmail = ref<string | null>(null)
    const registeredStudentName = ref<string | null>(null)

    const submitForm = async () => {
      const studentData = {
        ...student.value,
        grade: student.value.grade.toString(), // Convert grade to string for backend
      }
      try {
        // Make a POST request to the backend to register the student
        const response = await axios.post(
          'http://localhost:8080/students',
          studentData,
        )
        console.log('Student registered successfully', response.data)
        registeredStudentId.value = response.data.id // Store the registered student ID
        registrationSuccess.value = true // Set registration success to true
      } catch (error) {
        console.log('asdasdas', error.response.data)
        registeredStudentId.value = error.response.data.student.id
        registeredStudentEmail.value = error.response.data.student.email
        registeredStudentName.value = error.response.data.student.name
        registrationFail.value = true
      }
    }

    const proceedToHome = () => {
      router.push('/') // Navigate to the home page
    }

    return {
      student,
      submitForm,
      registrationSuccess,
      registrationFail,
      registeredStudentId,
      registeredStudentEmail,
      registeredStudentName,
      proceedToHome,
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

<template>
  <div>
    <h2>Student List</h2>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Email</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="student in students" :key="student.id">
          <td>{{ student.id }}</td>
          <td>{{ student.name }}</td>
          <td>{{ student.email }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import * as axios from 'axios'
import { type Student } from '../interface/Student' // Import the Student interface

export default defineComponent({
  data() {
    return {
      students: [] as Student[],
    }
  },
  mounted() {
    this.fetchStudents()
  },
  methods: {
    fetchStudents() {
      axios
        .get('/api/students')
        .then((response: { data: Student[] }) => {
          this.students = response.data
        })
        .catch((error: unknown) => {
          console.error(error)
        })
    },
  },
})
</script>

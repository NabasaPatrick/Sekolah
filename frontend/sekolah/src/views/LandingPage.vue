<template>
  <div class="landing-page">
    <h1>Welcome to Sekolahss</h1>
    <p>Your one-stop platforasdasdm for education.</p>
    <div class="buttons">
      <button @click="navigateToStudentPortal">Student Portal</button>
      <button @click="navigateToTeacherPortal">Teacher Portal</button>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import { useRouter } from 'vue-router'

export default defineComponent({
  setup() {
    const router = useRouter()

    return {
      navigateToStudentPortal() {
        router.push('/student-portal')
      },
      navigateToTeacherPortal() {
        router.push('/teacher-portal')
      },
    }
  },
})
</script>

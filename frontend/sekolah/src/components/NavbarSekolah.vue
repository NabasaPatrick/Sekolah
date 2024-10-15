<template>
  <nav class="navbar">
    <div class="navbar-brand">
      <router-link to="/">Sekolah</router-link>
    </div>
    <div class="navbar-nav">
      <router-link to="/student-portal">Student Portal</router-link>
      <router-link to="/teacher-portal">Teacher Portal</router-link>
    </div>
  </nav>
</template>

<script lang="ts">
export default {
  // ...
}
</script>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  background-color: #f0f0f0;
}

.navbar-brand {
  font-size: 1.2rem;
  font-weight: bold;
}

.navbar-nav {
  display: flex;
  gap: 1rem;
}

.navbar-nav a {
  text-decoration: none;
  color: #333;
}
</style>

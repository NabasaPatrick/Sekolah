<template>
  <div class="app">
    <header class="header">
      <h1>Sekolah</h1>
      <nav class="navbar">
        <router-link to="/">Home</router-link>
        <router-link to="/student-portal">Student Portal</router-link>
        <router-link to="/teacher-portal">Teacher Portal</router-link>
      </nav>
    </header>

    <main class="main">
      <div class="breadcrumb">
        <router-link to="/">Home</router-link> /
        <span v-if="$route.name">{{ $route.name }}</span>
      </div>

      <router-view />
    </main>

    <footer class="footer">&copy; 2023 Sekolah</footer>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'

export default defineComponent({
  // ...
})
</script>

<style>
/* Reset margins and paddings for full-width layout */
html,
body {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
}

.app {
  font-family: 'Arial', sans-serif;
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  margin: 0;
  width: 100%; /* Remove or increase max-width */
}

/* Full-width Header */
.header {
  width: 100%; /* Ensure full width */
  background-color: #034694; /* Chelsea blue */
  color: white;
  padding: 1rem 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.header-content {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 1rem;
}

.header h1 {
  margin: 0;
  font-size: 1.8rem;
}

.navbar {
  display: flex;
  gap: 1rem;
}

.navbar a {
  color: white;
  text-decoration: none;
  font-size: 1rem;
  transition: color 0.3s;
}

.navbar a:hover {
  color: #ffd700; /* Chelsea yellow on hover */
}

/* Main Content */
.main {
  flex-grow: 1;
  padding: 2rem;
  max-width: 1200px;
  margin: 0 auto;
}

.breadcrumb {
  margin-bottom: 1rem;
  font-size: 0.9rem;
  color: #888;
}

.breadcrumb a {
  color: #034694;
  text-decoration: none;
}

.breadcrumb a:hover {
  text-decoration: underline;
}

/* Full-Width Footer */
.footer {
  width: 100%; /* Ensure full width */
  background-color: #034694;
  padding: 1rem 0;
  text-align: center;
  font-size: 0.9rem;
  color: white;
  border-top: 1px solid #ddd;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 1rem;
}

.footer a {
  color: white;
  text-decoration: none;
}

.footer a:hover {
  color: #ffd700;
}

/* Add responsiveness */
@media (max-width: 768px) {
  .header-content {
    flex-direction: column;
    text-align: center;
  }

  .navbar {
    flex-direction: column;
    gap: 0.5rem;
  }

  .main {
    padding: 1rem;
  }
}
</style>

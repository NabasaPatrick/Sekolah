<template>
  <div class="course-list">
    <h3>Available Courses</h3>
    <ul>
      <li v-for="course in availableCourses" :key="course.id">
        {{ course.name }} (Enrolled: {{ course.enrolledCount }})
        <button @click="enrollInCourse(course)">Enroll</button>
      </li>
    </ul>
  </div>
</template>

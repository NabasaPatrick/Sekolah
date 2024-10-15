import { createRouter, createWebHistory } from 'vue-router'
import LandingPage from '../views/LandingPage.vue'
import AboutView from '../views/AboutView.vue'
import StudentPortal from '../views/StudentPortal.vue'
import RegisterStudent from '../components/RegisterStudent.vue'
import RegisterTeacher from '../components/RegisterTeacher.vue'
import DetailCourse from '../components/DetailCourse.vue'
import EnrollmentForm from '../components/EnrollmentForm.vue'
import TeacherPortal from '../views/TeacherPortal.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: LandingPage,
    },
    {
      path: '/about-us', // Change the path to '/about-us'
      name: 'about',
      component: AboutView,
    },
    {
      path: '/student-portal',
      name: 'StudentPortal',
      component: StudentPortal,
    },
    {
      path: '/teacher-portal',
      name: 'TeacherPortal',
      component: TeacherPortal,
    },
    { path: '/register-student', component: RegisterStudent },
    { path: '/register-teacher', component: RegisterTeacher },
    {
      path: '/detail-course/:courseId',
      name: 'DetailCourse',
      component: DetailCourse,
      props: true, // Pass route params as props
    },
    {
      path: '/enrollment-course/:type/:courseId',
      name: 'EnrollmentForm',
      component: EnrollmentForm,
      props: true, // Pass route params as props
    },
  ],
})

export default router

import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import RegistrationForm from '../components/RegistrationForm'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView
  },
  {
    path: '/',
    name: 'Objects',
    component: HomeView
  },
  {
    path: '/register',
    name: 'RegistrationForm',
    component: RegistrationForm
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

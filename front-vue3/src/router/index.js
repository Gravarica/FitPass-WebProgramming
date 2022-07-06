import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ObjectsView from '../views/ObjectsView.vue'
import RegistrationView from '../views/RegistrationView'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView
  },
  {
    path: '/objects',
    name: 'Objects',
    component: ObjectsView
  },
  {
    path: '/register',
    name: 'Register',
    component: RegistrationView
  }

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

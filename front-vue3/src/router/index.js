import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ObjectsView from '../views/ObjectsView.vue'
import RegistrationView from '../views/RegistrationView'
import TrainerRegistrationView from '../views/TrainerRegistrationView.vue'
import SelectSportObject from '../components/SelectSportObject.vue'
import SelectTraining from '../components/SelectTraining.vue'

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
  },
  {
    path: '/register/trainer',
    name: 'RegisterTrainer',
    component: SelectSportObject,
    // childern:[
    //   {
    //     path: '/',
    //     name: "SelectSportObject",
    //     component: SelectSportObject
    //   }]
  },
  {
    path: '/register/trainer/training',
    name: 'SelectTraining',
    component: SelectTraining
  }


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

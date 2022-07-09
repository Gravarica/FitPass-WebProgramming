import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ObjectsView from '../views/ObjectsView.vue'
import RegistrationView from '../views/RegistrationView'
import TrainerRegistrationView from '../views/TrainerRegistrationView.vue'
import SelectSportObject from '../components/SelectSportObject.vue'
import SelectTraining from '../components/SelectTraining.vue'
import MyProfileView from '../views/MyProfileView'
import UsersView from '../views/UsersView'
import DeatilsView from '../views/DetailsView'
import EditProfileView from '../views/EditProfileView'
import SubscriptionView from '../views/SubscriptionView'
import SubscribeView from '../views/SubscribeView'
import CheckoutView from '../views/CheckoutView'

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
  },
  {
    path: '/myprofile',
    name: 'MyProfile',
    component: MyProfileView
  },
  {
    path: '/users',
    name: 'Users',
    component: UsersView
  },
  {
    path: '/details',
    name: 'Details',
    component: DeatilsView
  },
  {
    path: '/editprofile',
    name: 'EditProfile',
    component: EditProfileView
  },
  {
    path: '/subscription',
    name: 'Subscription',
    component: SubscriptionView
  },
  {
    path: '/subscribe',
    name: 'Subscribe',
    component: SubscribeView
  },
  {
    path: '/checkout',
    name: 'Checkout',
    component: CheckoutView,
    props: (route) => ({
       ...route.params
    })
  }


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkActiveClass: "active",
  linkExactActiveClass: "exact-active"
})

export default router

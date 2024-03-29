import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ObjectsView from '../views/ObjectsView.vue'
import RegistrationView from '../views/RegistrationView'
import TrainerRegistrationView from '../views/TrainerRegistrationView.vue'
import SelectSportObject from '../components/SelectSportObject.vue'
import SelectTraining from '../components/SelectTraining.vue'
import NewSportObject from '../views/NewSportObject.vue'
import ManagerRegistration from '../components/ManagerRegistration.vue'
import CustomerTrainings from '../views/CustomerTrainings.vue'
import TrainerTrainings from '../views/TrainerTrainings.vue'
import MyProfileView from '../views/MyProfileView'
import UsersView from '../views/UsersView'
import DeatilsView from '../views/DetailsView'
import EditProfileView from '../views/EditProfileView'
import SubscriptionView from '../views/SubscriptionView'
import SubscribeView from '../views/SubscribeView'
import CheckoutView from '../views/CheckoutView'
import ScheduleTraining from '../views/NewTrainingView.vue'
import PromoCode from '../views/PromoCode'
import CreateCommentView from '../views/CreateCommentView.vue'
import CommentsView from '../views/CommentsView.vue'
import CustomerScheduleTraining from '../views/CustomerNewTraining.vue'
import MyObjectView from '../views/MyObjectView.vue'
import NewContent from '@/views/NewContentView.vue'
import NewTraining from '@/views/NewTraining.vue'
import EditContent from '@/views/EditContentView.vue'
import TrainersView from '@/views/TrainersView.vue'
import ObjectUsersView from '@/views/ObjectUsersView.vue'
import ObjectTrainingsView from '@/views/ObjectTrainingsView.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView,
    props: (route) => ({
      ...route.params
    })
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
    component: TrainerRegistrationView,
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
    path: '/add/sport_object',
    name: "AddSportObject",
    component: NewSportObject
  },
  {
    path: '/register/manager',
    name: "RegisterManager",
    component: ManagerRegistration
  },
  {
    path: '/customer/trainings',
    name: "CustomerTrainings",
    component: CustomerTrainings
  },
  {
    path: '/trainer/trainings',
    name: "TrainerTrainings",
    component: TrainerTrainings
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
    component: DeatilsView,
    props: (route) => ({
      ...route.params
    })
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
  },
  {
    path: '/schedule/training',
    name: 'ScheduleTraining',
    component: ScheduleTraining
  },
  {
    path: '/promoCode',
    name: 'PromoCode',
    component: PromoCode
  },
  {
    path: '/createComment',
    name: 'CreateComment',
    component: CreateCommentView,
    props: (route) => ({
      ...route.params
    })
  },
  {
    path: '/comments',
    name: 'Comments',
    component: CommentsView
  },
  {
    path: '/schedule/training/customer',
    name: 'CustomerScheduleTraining',
    component: CustomerScheduleTraining
  },
  {
    path: '/myobject',
    name: 'MyObject',
    component: MyObjectView
  },
  {
    path: '/add/content',
    name: 'NewContent',
    component: NewContent
  },
  {
    path: '/add/training',
    name: 'NewTraining',
    component: NewTraining
  },
  {
    path: '/edit/content',
    name: 'EditContent',
    component: EditContent,
    props: (route) => ({
      ...route.params
    })
  },
  {
    path: '/object/trainers',
    name: 'ObjectTrainers',
    component: TrainersView,
    props: (route) => ({
      ...route.params
    })
  },
  {
    path: '/object/users',
    name: 'ObjectUsers',
    component: ObjectUsersView,
    props: (route) => ({
      ...route.params
    })
  },
  {
    path: '/object/trainings',
    name: 'ObjectTrainings',
    component: ObjectTrainingsView,
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

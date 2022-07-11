import "bootstrap/dist/css/bootstrap.css";
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import mitt from 'mitt'
import Notifications from '@kyvg/vue3-notification'
import Vue3StarRatings from "vue3-star-ratings";
const emitter = mitt()
const app = createApp(App)

app.config.globalProperties.emitter = emitter
app.use(router).mount('#app')
app.use(Notifications)
app.component("star-rating", VueStarRating.default)

import "bootstrap/dist/js/bootstrap"


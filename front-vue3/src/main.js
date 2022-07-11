import "bootstrap/dist/css/bootstrap.css";
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import mitt from 'mitt'
import OpenLayersMap from 'vue3-openlayers'
import Notifications from '@kyvg/vue3-notification'
import Vue3StarRatings from "vue3-star-ratings";
import 'vue3-openlayers/dist/vue3-openlayers.css'
const emitter = mitt()
const app = createApp(App)

app.config.globalProperties.emitter = emitter
app.use(router).mount('#app')
app.use(Notifications)
app.use(OpenLayersMap)
app.component("star-rating", VueStarRating.default)

import "bootstrap/dist/js/bootstrap"


import "bootstrap/dist/css/bootstrap.css";
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import mitt from 'mitt'
const emitter = mitt()
const app = createApp(App)

app.config.globalProperties.emitter = emitter
app.use(router).mount('#app')

import "bootstrap/dist/js/bootstrap"


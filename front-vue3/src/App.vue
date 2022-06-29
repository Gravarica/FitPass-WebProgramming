<template>
  <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
    <button @click = "isOpen = true">Show Popup</button>
  </nav>
  <Popup :open="isOpen" @close = "isOpen = !isOpen">
    
  </Popup>
  <router-view/>
</template>
<script>

import Popup from "./components/Popup.vue";
import { ref } from "vue";

export default { 
    components: { Popup },
    setup(){
      const isOpen = ref(false)

      return {isOpen}
    }
  }
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

</style>

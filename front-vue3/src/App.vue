<template>
  <div class="app">
    <Navigation/>
     <router-view/>
  </div>
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


<script>
import Navigation from "./components/Navigation"

export default{
    name : "App",
    components:{
      Navigation,
    }
}
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Ralewey:wght@400;500&display=swap");
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
  font-family: "Raleway", sans-serif;
  font-weight: 400;
}
.app {
  min-height: 100vh;
  position: relative;
  background-color: #f1f1f1;
}
.container {
  padding: 0 20px;
  max-width: 1140px;
  margin: 0 auto;
}

</style>

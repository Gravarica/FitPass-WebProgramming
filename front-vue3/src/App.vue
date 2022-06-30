<template>
  <div class="app">
    <Navigation @show-login="showLogin"/>
     <router-view/>
  </div>

  <div>
    <LoginPopup v-if="login" @close-login="closeLogin"/> 
  </div>
</template>

<script>
import Navigation from "./components/Navigation"
import LoginPopup from "./components/LoginPopup.vue"

export default{
    name : "App",
    data(){
      return{
        login : false,
        register : false
      }
    },
    
    components:{
      Navigation,
      LoginPopup,
    },
    
    methods:{
      showLogin(){
        this.login = true
      },
      closeLogin(){
        this.login = false
      }
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

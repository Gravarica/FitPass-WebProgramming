<template>
    <Navigation @show-login="showLogin" @logout="logout" v-bind="this.loggedUser" ref="navBar"/>
    <router-view/>
  <div>
    <LoginPopup v-if="login" @close-login="closeLogin" @logged-in="captureLogIn" @show-buttons="showButtons"/> 
  </div>
  <div>
    <RegistrationPopup v-if="register" @close-reg="closeRegister"/> 
  </div>
</template>

<script>
import Navigation from "./components/Navigation"
import LoginPopup from "./components/LoginPopup.vue"
import RegistrationPopup from "./components/RegistrationPopup.vue"
import OpenLayersMap from 'vue3-openlayers'
import 'vue3-openlayers/dist/vue3-openlayers.css'
import axios from "axios";

export default{
    name : "App",
    data(){
      return{
        login : false,
        sportObjects: null,
        loggedUser: {
          username: '',
          role: '',
          success: false,
        },
      }
    },
    provide() {
        return{
          objects: this.sportObjects
        }
    },

    components:{
    Navigation,
    LoginPopup,
    RegistrationPopup,
    OpenLayersMap
    },
    
    methods:{
      showLogin(){
        this.login = true
      },
      showButtons(type){
        this.$refs.navBar.showRightNavBarButtons(type)
      },
      closeLogin(){
        this.login = false
      },
      captureLogIn(loginData){
        this.loggedUser = loginData;
      },
      logout(){
        axios
          .post('http://localhost:8081/WebShopREST/rest/users/logout')
          .then(response => {
            this.loggedUser = null;
            this.$refs.navBar.showRightNavBarButtons("USER")  
          })
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

<template>
    <Navigation @show-login="showLogin" @logout="logout" v-bind="this.loggedUser" ref="navBar"/>
    <router-view/>
    <div class="my-notification">
      <notifications position="top center" width="250px" height="100px"/>
    </div>
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
import { computed } from "@vue/reactivity"

export default {
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
        logged: computed( ()=> this.loggedUser.success)
      }       
    },
    created(){
        console.log('Saljem zahtev')
        axios
        .get('http://localhost:8081/WebShopREST/rest/users/currentUser')
        .then(response => {
          this.loggedUser = response.data;
        })
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
        if(type == null){
          return
        }
        this.$refs.navBar.showRightNavBarButtons(type)
      },
      closeLogin(){
        this.login = false
      },
      captureLogIn(loginData){
        this.loggedUser = loginData;
        if(this.loggedUser.success){
            this.$notify({
              title: 'Welcome ' + this.loggedUser.username,
              text: 'You have been logged in'
            })
            
            this.login = false;
          } else {
            this.$notify({
              title: 'Wrong credentials',
              text: 'Please try again'
            })
          }
      },
      logout(){
        axios
          .post('http://localhost:8081/WebShopREST/rest/users/logout')
          .then(response => {
            this.loggedUser = null;
            this.$refs.navBar.showRightNavBarButtons("USER")  
            this.$router.push({ path: '/'})
          })
      },
      
    }
}
</script>

<style lang="scss">
@font-face {
  font-family: "Lato";
  src: local("Lato"),
       url(./fonts/Lato-Regular.ttf) format('truetype')
}
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
  font-family: "Lato", sans-serif;
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

<template>
  <div class="container-flex">
    <Navigation @show-login="showLogin" @show-reg="showRegister" @logout="logout" v-bind="this.loggedUser"/>
    <div class = "row mt-0">
      <router-view/>
    </div>
  </div>

  <div>
    <LoginPopup v-if="login" @close-login="closeLogin" @logged-in="captureLogIn"/> 
  </div>
  <div>
    <RegistrationPopup v-if="register" @close-reg="closeRegister"/> 
  </div>
</template>

<script>
import Navigation from "./components/Navigation"
import LoginPopup from "./components/LoginPopup.vue"
import RegistrationPopup from "./components/RegistrationPopup.vue"
import axios from "axios";

export default{
    name : "App",
    data(){
      return{
        login : false,
        register : false,
        loggedUser: {
          username: '',
          role: '',
          success: false,
        },
      }
    },
    
    components:{
    Navigation,
    LoginPopup,
    RegistrationPopup
    },
    
    methods:{
      showLogin(){
        this.login = true
      },
      showRegister(){
        this.register = true;
      },
      closeLogin(){
        this.login = false
      },
      closeRegister(){
        this.register = false
      },
      captureLogIn(loginData){
        this.loggedUser = loginData;
      },
      logout(){
        axios
          .post('http://localhost:8081/WebShopREST/rest/users/logout')
          .then(response => {
            this.loggedUser = null;  
          })
      }
    },
    mounted(){
      axios
        .get('http://localhost:8081/WebShopREST/rest/users/currentUser')
        .then((response) => {
          this.loggedUser = response.data
        })
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

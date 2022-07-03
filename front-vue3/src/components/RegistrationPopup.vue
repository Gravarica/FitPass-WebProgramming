<template>
    <div class="popup">
        <div class="popup-inner">
            <div class="login-box">
                <h2>Login</h2>
                <form>
                    <div class="user-box">
                    <input type="text" name="" required v-model="registrationDTO.firstName">
                    <label>First Name</label>
                    </div>
                    
                    <div class="user-box">
                      <input type="text" name="" required v-model="registrationDTO.lastName">
                      <label>Last Name</label>
                    </div>
                    
                    <div class="user-box">
                    <input type="date" name="" required="" v-model="registrationDTO.dateOfBirth">
                    <!-- <label>Date of Birth</label> -->
                    </div>

                    <div class="form-check form-check-inline">
                      <input class="form-check-input" v-model="registrationDTO.gender" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="MALE">
                      <label class="form-check-label" for="inlineRadio1">Male</label>
                    </div>
                    <div class="form-check form-check-inline">
                      <input class="form-check-input" v-model="registrationDTO.gender" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="FEMALE">
                      <label class="form-check-label"  for="inlineRadio2">Female</label>
                    </div>
                   
                    <div class="user-box">
                    <input type="text" name="" required="" v-model="registrationDTO.username">
                    <label>Username</label>
                    </div>

                    <div class="user-box">
                        <input v-if="!showPassword" type="password" name="password" id="id_password" required="" v-model="registrationDTO.password">
                        <!-- <i class="far fa-eye" id="togglePassword" style="margin-left: -30px; cursor: pointer;"></i> -->
                    <label>Password</label>
                    </div>

                    <div class="user-box">
                    <input type="password" name="" required="" v-model="repeatPassword">
                    <label>Repeat Password</label>
                    </div>

                    <div id="err" class="err"></div>

            <div class="flex-container">
              <div class="flex-child">
                <a href="#" @click="register">
                  <span></span>
                  <span></span>
                  <span></span>
                  <span></span>
                  Submit
                </a>
              </div>
              <div class="flex-child">
                <a href="#" @click="closeRegister()">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    Cancel
                </a>
              </div>
            </div>
          </form>
        </div>
      </div>
  </div>
</template>


<script>
import axios from 'axios'

    export default {
        name : "registrationPopup",
        showPassword : true,
        repeatPassword : '',
        data(){
            return{
                registrationDTO : {username:'', password: '', firstName: '', lastName: '', gender: '', role: 'CUSTOMER', dateOfBirth: ''},
                registeredUser : null,
            };
        },
        
        methods:{
            closeRegister(){
                this.$emit("close-reg")
            },
          register(){
              axios
                .post('http://localhost:8081/WebShopREST/rest/users/registration',this.registrationDTO)
                .then((response) => {
                  this.registeredUser = response.data
                })
              // if(this.registeredUser == null){
              //   let notify = document.getElementById("err");
              //   notify.innerHTML = "Username allready exists, please try again.";
              //   notify.style.direction="block";
              // }
              // if(!this.registrationDTO.password == this.repeatPassword){
              //   console.log("USAO") 
              //   console.log(this.repeatPassword)
              //   let notify = document.getElementById("err");
              //   notify.innerHTML = "PASSWORD INCORECT";
              //   notify.style.direction="block";
              // }
            }
        }
    }
</script>


<style lang="scss" scoped>

.popup{
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        z-index: 99;
        background-color: rgba(0,0,0,0.3);

        display: flex;
        align-items: center;
        justify-content: center;

        // .popup-inner{
        //     background-color: white;
        //     padding: 32px;
        // }

    }

.flex-container {
    display: flex;
}

.flex-child {
    flex: 1;
}  

.flex-child:first-child {
    margin-right: 60px;
} 

html {
  height: 100%;
}
body {
  margin:0;
  padding:0;
  font-family: sans-serif;
  background: linear-gradient(#141e30, #243b55);
}

.login-box {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 400px;
  padding: 40px;
  transform: translate(-50%, -50%);
  background: rgba(0,0,0,.5);
  box-sizing: border-box;
  box-shadow: 0 15px 25px rgba(0,0,0,.6);
  border-radius: 10px;
}

.login-box h2 {
  margin: 0 0 30px;
  padding: 0;
  color: #fff;
  text-align: center;
}

.login-box .user-box {
  position: relative;
}

.login-box .user-box input {
  width: 100%;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  margin-bottom: 30px;
  border: none;
  border-bottom: 1px solid #fff;
  outline: none;
  background: transparent;
}
.login-box .user-box label {
  position: absolute;
  top:0;
  left: 0;
  padding: 10px 0;
  font-size: 16px;
  color: #fff;
  pointer-events: none;
  transition: .5s;
}

.login-box .user-box input:focus ~ label,
.login-box .user-box input:valid ~ label {
  top: -20px;
  left: 0;
  color: #03e9f4;
  font-size: 12px;
}

.login-box form a {
  position: relative;
  display: inline-block;
  padding: 10px 20px;
  color: #03e9f4;
  font-size: 16px;
  text-decoration: none;
  text-transform: uppercase;
  overflow: hidden;
  transition: .5s;
  margin-top: 40px;
  letter-spacing: 4px
}

.login-box a:hover {
  background: #03e9f4;
  color: #fff;
  border-radius: 5px;
  box-shadow: 0 0 5px #03e9f4,
              0 0 25px #03e9f4,
              0 0 50px #03e9f4,
              0 0 100px #03e9f4;
}

.login-box a span {
  position: absolute;
  display: block;
}

.login-box a span:nth-child(1) {
  top: 0;
  left: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(90deg, transparent, #03e9f4);
  animation: btn-anim1 1s linear infinite;
}

@keyframes btn-anim1 {
  0% {
    left: -100%;
  }
  50%,100% {
    left: 100%;
  }
}

.login-box a span:nth-child(2) {
  top: -100%;
  right: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(180deg, transparent, #03e9f4);
  animation: btn-anim2 1s linear infinite;
  animation-delay: .25s
}

@keyframes btn-anim2 {
  0% {
    top: -100%;
  }
  50%,100% {
    top: 100%;
  }
}

.login-box a span:nth-child(3) {
  bottom: 0;
  right: -100%;
  width: 100%;
  height: 2px;
  background: linear-gradient(270deg, transparent, #03e9f4);
  animation: btn-anim3 1s linear infinite;
  animation-delay: .5s
}

@keyframes btn-anim3 {
  0% {
    right: -100%;
  }
  50%,100% {
    right: 100%;
  }
}

.login-box a span:nth-child(4) {
  bottom: -100%;
  left: 0;
  width: 2px;
  height: 100%;
  background: linear-gradient(360deg, transparent, #03e9f4);
  animation: btn-anim4 1s linear infinite;
  animation-delay: .75s
}

@keyframes btn-anim4 {
  0% {
    bottom: -100%;
  }
  50%,100% {
    bottom: 100%;
  }
}

.err {
  color: white;
  background-color: red;
}

</style>
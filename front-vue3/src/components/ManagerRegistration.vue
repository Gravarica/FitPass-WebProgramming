<template>
    <section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 20px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Register</h3>
            <form>

              <div class="row">
                <div class="col-md-6 mb-4">

                  <div class="form-outline">
                    <input type="text" id="firstName" class="form-control form-control-lg"  v-model="this.registrationDTO.firstName"/>
                    <label class="form-label" for="firstName">First Name</label>
                  </div>

                </div>
                <div class="col-md-6 mb-4">

                  <div class="form-outline">
                    <input type="text" id="lastName" class="form-control form-control-lg"  v-model="this.registrationDTO.lastName"/>
                    <label class="form-label" for="lastName">Last Name</label>
                  </div>

                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3 d-flex align-items-center">

                  <div class="form-outline datepicker w-100">
                    <input type="date" class="form-control form-control-lg" id="birthdayDate"  v-model="this.registrationDTO.dateOfBirth"/>
                    <label for="birthdayDate" class="form-label">Birthday</label>
                  </div>

                </div>
                <div class="col-md-6 mb-3">

                  <h6 class="mb-2 pb-1">Gender: </h6>

                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="femaleGender"
                      value="FEMALE" v-model="this.registrationDTO.gender" />
                    <label class="form-check-label" for="femaleGender">Female</label>
                  </div>

                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="maleGender"
                      value="MALE" v-model="this.registrationDTO.gender"/>
                    <label class="form-check-label" for="maleGender">Male</label>
                  </div>

                </div>
              </div>
              <hr/>
              <div class="row mt-4">
                <div class="col-md-12 pb-2">

                  <div class="form-outline">
                    <input type="text" id="phoneNumber" class="form-control form-control-lg"  v-model="this.registrationDTO.username"/>
                    <label class="form-label" for="username">Username</label>
                  </div>

                </div>
              </div>

              <div class="row">
                <div class="col-12">

                  <input type="password" id="password" class="form-control form-control-lg"  v-model="this.registrationDTO.password"/>
                  <label class="form-label select-label">Password</label>

                </div>
              </div>

              <div class="row">
                <div class="col-12">

                  <input type="password" id="password-re" class="form-control form-control-lg"  v-model="this.passwordCheck"/>
                  <label class="form-label select-label">Confirm Password</label>

                </div>
              </div>

              <div class="mt-4 pt-2">
                <input class="btn btn-lg btn-grad" type="submit" value="Submit" @click="register"/>
              </div>

            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  
</section>

<div aria-live="polite" aria-atomic="true" style="position: relative; min-height: 200px;" v-if="toast">
  <div class="toast" style="position: absolute; top: 0; right: 0;">
    <div class="toast-header">
      <img src="../assets/point.png" class="rounded mr-2" alt="...">
      <strong class="mr-auto">Bootstrap</strong>
      <small>11 mins ago</small>
      <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="toast-body">
      Passwords dont match
    </div>
  </div>
</div>

</template>

<script>
    import router from '@/router'
import axios from 'axios'
    export default {
        data(){
            return {
                registrationDTO: {
                    username: '',
                    password: '',
                    firstName: '',
                    lastName: '',
                    role: 'MANAGER',
                    dateOfBirth: '',
                    gender: 'MALE'
                },
                passwordCheck: '',
                toast: false
            }
        },
        methods: {
            register(){
                if (this.registrationDTO.password != this.passwordCheck){
                    this.toast = true
                    return
                }
                axios
                    .post('http://localhost:8081/WebShopREST/rest/users/registration', this.registrationDTO)
                    .then((response) => {
                        console.log('Uspesna registracija')
                        this.$router.push({path: '/'})
                    })
            }
        }
    }
</script>


<style>

    .gradient-custom {
/* fallback for old browsers */

/* Chrome 10-25, Safari 5.1-6 */
background: -webkit-linear-gradient(to bottom right, rgba(240, 147, 251, 1), rgba(245, 87, 108, 1));
background-color: #f7b42c;
background-image: linear-gradient(315deg, #f7b42c 0%, #fc575e 74%);
/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

.card-registration .select-input.form-control[readonly]:not([disabled]) {
font-size: 1rem;
line-height: 2.15;
padding-left: .75em;
padding-right: .75em;
}
.card-registration .select-arrow {
top: 13px;
}

.buzon{
    background-color: #f7b42c;
    background-image: linear-gradient(315deg, #f7b42c 0%, #fc575e 74%);
    color: white;
    border-radius: 5px;
}

.btn-grad {
            background-image: linear-gradient(to right, #FF512F 0%, #DD2476  51%, #FF512F  100%);
            margin: 10px;
            margin-left: 0;
            padding: 10px 70px;
            text-align: center;
            text-transform: uppercase;
            transition: 0.5s;
            background-size: 200% auto;
            color: white;            
            box-shadow: 0 0 20px #eee;
            border-radius: 10px;
            display: block;
          }

.btn-grad:hover {
            background-position: right center; /* change the direction of the change here */
            color: #fff;
            text-decoration: none;
          }
         

</style>


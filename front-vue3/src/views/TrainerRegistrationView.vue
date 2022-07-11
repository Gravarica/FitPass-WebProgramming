<template>
    <section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 20px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 center">Register Trainer</h3>
            <form>

              

              <div class="row">
                <div class="col-md-6 mb-4 pt-3">

                  <div class="form-outline">
                    <input type="text" id="firstName" placeholder="First Name" class="form-control form-control-lg"  v-model="state.trainerRegistrationDTO.registrationDTO.firstName"/>
                    <span class="jabuka" v-if="v$.trainerRegistrationDTO.registrationDTO.firstName.$error">
                        {{ v$.trainerRegistrationDTO.registrationDTO.firstName.$errors[0].$message}}
                    </span>
                  </div>

                </div>
                <div class="col-md-6 mb-4 pt-3">

                  <div class="form-outline">
                    <input type="text" id="lastName" placeholder="Last Name" class="form-control form-control-lg"  v-model="state.trainerRegistrationDTO.registrationDTO.lastName"/>
                    <span clas="jabuka" v-if="v$.trainerRegistrationDTO.registrationDTO.lastName.$error">
                        {{ v$.trainerRegistrationDTO.registrationDTO.lastName.$errors[0].$message}}
                    </span>
                  </div>

                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3 d-flex align-items-center">

                  <div class="form-outline datepicker w-100">
                    <input type="date" max="2000-01-01" class="form-control form-control-lg" id="birthdayDate"  v-model="state.trainerRegistrationDTO.registrationDTO.dateOfBirth"/>
                    <label for="birthdayDate" class="form-label">Date of Birth</label>
                  </div>

                </div>
                <div class="col-md-6 mb-3">

                  <h6 class="mb-2 pb-1">Gender: </h6>

                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="femaleGender"
                      value="FEMALE" v-model="state.trainerRegistrationDTO.registrationDTO.gender" />
                    <label class="form-check-label" for="femaleGender">Female</label>
                  </div>

                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="maleGender"
                      value="MALE" v-model="state.trainerRegistrationDTO.registrationDTO.gender"/>
                    <label class="form-check-label" for="maleGender">Male</label>
                  </div>

                </div>
              </div>
              <hr/>
              <div class="row mt-4">
                <div class="col-md-12 pb-4">
                  <div class="form-outline">
                    <input type="text" id="phoneNumber" class="form-control form-control-lg" placeholder="Username" v-model="state.trainerRegistrationDTO.registrationDTO.username"/>
                    <span class="jabuka" v-if="v$.trainerRegistrationDTO.registrationDTO.username.$error">
                        {{ v$.trainerRegistrationDTO.registrationDTO.username.$errors[0].$message}}
                    </span>
                  </div>

                </div>
              </div>

              <div class="row">
                <div class="col-12 pb-4">

                  <input type="password" id="password" class="form-control form-control-lg" placeholder="Password" v-model="state.trainerRegistrationDTO.registrationDTO.password"/>
                  <span class="jabuka" v-if="v$.trainerRegistrationDTO.registrationDTO.password.$error">
                        {{ v$.trainerRegistrationDTO.registrationDTO.password.$errors[0].$message}}
                  </span>
                </div>
              </div>

              <div class="row">
                <div class="col-12 pb-2">
                  <input type="password" id="password-re" class="form-control form-control-lg"  placeholder="Confirm Password" v-model="state.passwordCheck"/>
                  <span class="jabuka" v-if="v$.passwordCheck.$error">
                        {{ v$.passwordCheck.$errors[0].$message}}
                  </span>
                </div>
              </div>

              <hr/>

              <div class="row" v-if="state.employ==false">
                <a href="#" @click="showForm" class="enrico gumica">Want to employ trainer right away, click here</a>
              </div>


              <div class="row " v-if="state.employ">

                <div class="pb-3">
                  <button type="button" @click="hideForm()" class="btn-close float-end" aria-label="Close"></button>
                </div>
                    
                    <div>
                        <select v-model="state.selected" required class="type form-select form-outline form-select-lg mb-4" aria-label=".form-select-lg example">
                            <option value=null disabled>Choose sport object</option>
                            <option v-for="it in state.sportObjects" :value="it">{{it.name}}</option>
                        </select>
                    </div>
              </div>
              
             <div class="row" v-if="state.employ">
                    <div>
                        <select v-model="state.trainerRegistrationDTO.training" required class="type form-select form-outline form-select-lg mb-4" aria-label=".form-select-lg example">
                            <option value=null disabled>Choose training</option>
                            <option v-for="it in state.trainings" :value="it">{{it.name}}</option>
                        </select>
                    </div>
              </div>

              <div class="d-grid gap-2 pt-5 md-6">
                <input class="btn btn-lg btn-grad" type="button" value="Submit" @click="register"/>
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
import useValidate from '@vuelidate/core'
import { required, sameAs, minLength, helpers, alpha} from '@vuelidate/validators'
import {reactive,computed} from 'vue'

import axios from 'axios'
    
    export default {

      setup(){
        const state = reactive({
          trainerRegistrationDTO : {
                    registrationDTO: {
                        username: '',
                        password: '',
                        firstName: '',
                        lastName: '',
                        role: 'TRAINER',
                        dateOfBirth: '',
                        gender: 'MALE',
                    },
                    training : null
                },

                passwordCheck: '',
                toast: false,
                sportObjects : null,
                trainings : null,
                selected : null,
                employ : false
        })
      
        const rules = computed(() => {
          return{
             trainerRegistrationDTO : {
                    registrationDTO: {
                        username: { 
                          required, 
                          minLength : minLength(3) 
                        },
                        password: {
                           required, 
                           pass : helpers.withMessage(
                              'Password must contain minimum six characters, at least one letter and one number',
                              pass
                           )
                        },
                        firstName: { 
                          required,
                          minLength : minLength(3),
                          alpha
                        },
                        lastName: { 
                          required, 
                          alpha
                        },
                        role: { required },
                        dateOfBirth: { required },
                        gender: { required },
                    },
                    training : { required }
                },
                passwordCheck: { 
                  required,
                  sameAs: helpers.withMessage(
                    'Passwords must be equal',
                    sameAs(state.trainerRegistrationDTO.registrationDTO.password)
                  )
                },
          }
        })
      
        const pass = (value) => value.regex("[a-zA-z]{6}[0-9]*")

        const v$ = useValidate(rules,state)
        
        return{
          state,
          v$
        }

      },
      
        watch:{
            selected(object){
                this.fillTrainings(object.id)
            }
        },
        methods: {
            register(){
                this.v$.$validate()
                if(!this.v$.$error){
                   axios
                    .post('http://localhost:8081/WebShopREST/rest/users/registration', this.registrationDTO)
                
                  this.$router.push({path: '/'})
                  alert("Succsessful registration")
                }
            },
            fillTrainings(id){
                axios
                    .get('http://localhost:8081/WebShopREST/rest/trainings/without/trainer/' + id)
                    .then((response) =>{
                        this.state.trainings = response.data
                    })
            },
            showForm(){
              this.state.employ = true
            },
            hideForm(){
              this.state.employ = false
            }
        },
        created(){
             axios
                .get('http://localhost:8081/WebShopREST/rest/sport_objects/get')
                .then((response) =>{
                    this.state.sportObjects = response.data
                })
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
         
.gumica{
  font-size: large;
}


.jabuka{
  color: red;
}

</style>


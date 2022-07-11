<template>
    <section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 20px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 center">Registration</h3>
            <form>

              <div class="row">
                <div class="col-md-6 mb-4">

                  <div class="form-outline">
                    <input type="text" id="firstName" placeholder="First Name" class="form-control form-control-lg"  v-model="state.registrationDTO.firstName"/>
                    <span class="jabuka" v-if="v$.registrationDTO.firstName.$error">
                        {{ v$.registrationDTO.firstName.$errors[0].$message}}
                    </span>
                  </div>

                </div>
                <div class="col-md-6 mb-4">

                  <div class="form-outline">
                    <input type="text" id="lastName" placeholder="Last Name" class="form-control form-control-lg"  v-model="state.registrationDTO.lastName"/>
                    <span class="jabuka" v-if="v$.registrationDTO.lastName.$error">
                        {{ v$.registrationDTO.lastName.$errors[0].$message}}
                    </span>
                  </div>

                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3 d-flex align-items-center">

                  <div class="form-outline datepicker w-100">
                    <input type="date" class="form-control form-control-lg" id="birthdayDate"  v-model="state.registrationDTO.dateOfBirth"/>
                    <label for="birthdayDate" class="form-label">Date of Birth</label>
                  </div>

                </div>
                <div class="col-md-6 mb-3">

                  <h6 class="mb-2 pb-1">Gender: </h6>

                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="femaleGender"
                      value="FEMALE" v-model="state.registrationDTO.gender" />
                    <label class="form-check-label" for="femaleGender">Female</label>
                  </div>

                  <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" id="maleGender"
                      value="MALE" v-model="state.registrationDTO.gender"/>
                    <label class="form-check-label" for="maleGender">Male</label>
                  </div>

                </div>
              </div>
              <hr/>
              <div class="row mt-4">
                <div class="col-md-12 pb-4">

                  <div class="form-outline">
                    <input type="text" id="phoneNumber" placeholder="Username" class="form-control form-control-lg"  v-model="state.registrationDTO.username"/>
                    <span class="jabuka" v-if="v$.registrationDTO.username.$error">
                        {{ v$.registrationDTO.username.$errors[0].$message}}
                    </span>
                  </div>

                </div>
              </div>

              <div class="row">
                <div class="col-12 pb-4">

                  <input type="password" id="password" placeholder="Password" class="form-control form-control-lg"  v-model="state.registrationDTO.password"/>
                  <span class="jabuka" v-if="v$.registrationDTO.password.$error">
                        {{ v$.registrationDTO.password.$errors[0].$message}}
                    </span>
                </div>
              </div>

              <div class="row">
                <div class="col-12">

                  <input type="password" placeholder="Confirm Password" id="password-re" class="form-control form-control-lg"  v-model="state.passwordCheck"/>
                  <span class="jabuka" v-if="v$.passwordCheck.$error">
                        {{ v$.passwordCheck.$errors[0].$message}}
                    </span>

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
</template>

<script>
import router from '@/router'
import axios from 'axios'
import useValidate from '@vuelidate/core'
import { required, sameAs, minLength, helpers,alpha} from '@vuelidate/validators'
import {reactive,computed} from 'vue'
    export default {

      setup(){
        const state = reactive({
          registrationDTO: {
                    username: '',
                    password: '',
                    firstName: '',
                    lastName: '',
                    role: 'CUSTOMER',
                    dateOfBirth: '',
                    gender: 'MALE'
                },
                passwordCheck: '',
                toast: false,
                canUsername : true,
                users : null
        })

        const rules = computed(() => {
          return{
            registrationDTO: {
                    username: {
                      required, 
                      minLength : minLength(3),
                      // user : helpers.withMessage(
                      //   'Username allready exists',
                      //   user
                      // )
                    },
                    
                    password: {
                      required, 
                      // pass : helpers.withMessage(
                      //   'Password must contain minimum six characters, at least one letter and one number',
                      //   pass
                      // )
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
                    gender: { required }
                },
                passwordCheck: {
                  required,
                  sameAs: helpers.withMessage(
                    'Passwords must be equal',
                    sameAs(state.registrationDTO.password)
                  )
                }
          }
        })

       const pass = (value) => value.regex("[a-zA-z]{6}[0-9]*")
       const user = (value) => value.canUsername
       
       const v$ = useValidate(rules,state)
        
        return{
          state,
          v$
        }

      },  
        methods: {
            register(){
              this.v$.$validate()
              if(!this.v$.$error){
                  axios
                    .post('http://localhost:8081/WebShopREST/rest/users/registration', this.state.registrationDTO)
                    .then((response) => {
                        console.log('Uspesna registracija')
                        this.$router.push({path: '/'})
                    })
              }
            },
            checkUsername(username){
              for(const it in this.state.users){
                  if(it.username == username){
                    return false;
                  }
              }     
              return true
            }
        },
        created(){
           axios
              .get('http://localhost:8081/WebShopREST/rest/users/get')
              .then((response) => {
                  this.state.users = response.data
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
         

</style>


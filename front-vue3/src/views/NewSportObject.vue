<template>
<section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col">
        <div class="card card-registration my-4">
          <div class="row g-0">
            <div class="col-xl-6 d-none d-xl-block">
              <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-registration/img4.webp"
                alt="Sample photo" class="img-fluid"
                style="border-top-left-radius: .25rem; border-bottom-left-radius: .25rem;" />
            </div>
            <div class="col-xl-6">
              <div class="card-body p-md-5 text-black">
                <h3 class="mb-5 text-uppercase">Adding New Object</h3>

                <form id="myForm">
                <div class="row">
                  <div class="mb-4">
                    <div class="form-outline">
                      <input type="text" id="form3Example1m" placeholder="Name"  required v-model="state.addObjectDTO.name" class="form-control form-control-lg" />
                      <span class="jabuka" v-if="v$.addObjectDTO.name.$error">
                        {{ v$.addObjectDTO.name.$errors[0].$message}}
                      </span>
                    </div>
                  </div>
                </div>

                <div class="row">
                    <div class="pb-4">
                         <select v-model="state.addObjectDTO.type" class="type form-select form-outline form-select-lg" required aria-label=".form-select-lg example">
                            <option value="" disabled>Please choose type of Sport Object</option>
                            <option v-for="it in state.types" :value="it">{{convertType(it)}}</option>
                        </select>
                        <span class="jabuka" v-if="v$.addObjectDTO.type.$error">
                          {{ v$.addObjectDTO.type.$errors[0].$message}}
                        </span>
                    </div>
                </div>

                 <div class="row">
                    <div class="col-md-6 pb-3">
                        <select v-model="state.addObjectDTO.location.address.city" class="type form-select form-outline form-select-lg"  required aria-label=".form-select-lg example">
                            <option value="" disabled>City</option>
                            <option value="Beograd">Beograd</option>
                            <option value="Novi Sad">Novi Sad</option>
                            <option value="Nis">Subotica</option>
                            <option value="Krajugevac">Krajugevac</option>
                            <option value="Cacak">Cacak</option>
                        </select>

                      <span class="jabuka" v-if="v$.addObjectDTO.location.address.city.$error">
                        {{ v$.addObjectDTO.location.address.city.$errors[0].$message}}
                      </span>
                    
                    </div>
                    
                    <div class="col-md-6 pb-3">
                        <input type="text" v-model="state.addObjectDTO.location.address.postalNumber" required id="form3Example1m" placeholder="Postal Number" class="form-control form-control-lg" />
                        <span class="jabuka" v-if="v$.addObjectDTO.location.address.postalNumber.$error">
                          {{ v$.addObjectDTO.location.address.postalNumber.$errors[0].$message}}
                        </span>
                    </div>
                </div>

                <div class="form-outline pb-3 row">
                  <div class="col-md-8">
                    <input type="text" required v-model="state.addObjectDTO.location.address.street" id="form3Example8" placeholder="Adress" class="form-control form-control-lg" />
                     <span class="jabuka" v-if="v$.addObjectDTO.location.address.street.$error">
                          {{ v$.addObjectDTO.location.address.street.$errors[0].$message}}
                        </span>
                  </div>
                  <div class="col-md-4">
                    <input type="text" required v-model="state.addObjectDTO.location.address.number" id="form3Example8" placeholder="Number" class="form-control form-control-lg" />
                     <span class="jabuka" v-if="v$.addObjectDTO.location.address.number.$error">
                          {{ v$.addObjectDTO.location.address.number.$errors[0].$message}}
                      </span>
                  </div>
                </div>

                <div class="row" v-if="state.showManagers">
                    <div>
                         <select v-model="state.addObjectDTO.managerUsername" required class="type form-select form-outline form-select-lg" aria-label=".form-select-lg example">
                            <option value="" disabled>Please choose manager</option>
                            <option v-for="it in state.managers" :value="it.username">{{getFullName(it)}}</option>
                        </select>
                    </div>
                    <span class="jabuka" v-if="v$.addObjectDTO.managerUsername.$error">
                          {{ v$.addObjectDTO.managerUsername.$errors[0].$message}}
                    </span>
                </div>

                <div class="row" v-if="!state.showManagers">
                    <div>
                         <p>They are no available managers, but you can allways create new one</p>
                           <div class="d-grid gap-2">
                            <router-link class="d-grid gap-2 enrico" :to="{name : 'RegisterManager'}"><button class="btn btn-warning" type="button">Add New Manager</button></router-link>
                          </div>
                    </div>
                </div>

                 <hr class="solid">

                <div class="row mt-2">
                    <div>
                         <p>Please select sport object's work hour:</p>
                    </div>
                </div>

                <div class="form-outline mb-4 row">
                  <div class="col-md-6">
                    <input v-model="state.addObjectDTO.startTime" type="text" required id="form3Example8" placeholder="From" class="form-control form-control-lg" />
                     <span class="jabuka" v-if="v$.addObjectDTO.startTime.$error">
                          {{ v$.addObjectDTO.startTime.$errors[0].$message}}
                      </span>
                  </div>
                  <div class="col-md-6">
                    <input v-model="state.addObjectDTO.endTime" type="text" required id="form3Example8" placeholder="To" class="form-control form-control-lg" />
                     <span class="jabuka" v-if="v$.addObjectDTO.endTime.$error">
                          {{ v$.addObjectDTO.endTime.$errors[0].$message}}
                      </span>  
                  </div>
                </div>

                
                
                <div class="d-grid gap-2 pt-5 md-6">
                  <input type="button" class="btn btn-lg btn-grad" @click="addNewSportObject()" value="Submit">
                </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
</template>
    
<script>
import axios from 'axios'
import useValidate from '@vuelidate/core'
import { required, sameAs, minLength, helpers, alpha,numeric, maxLength} from '@vuelidate/validators'
import {reactive,computed} from 'vue'

    export default{
        setup(){
         const state = reactive({
              types : null,
                selected : null,
                managers : null,
                showManagers : true,
                canSubmit : false,
                newSportObject : null,
                
                addObjectDTO : { 
                  name: "", 
                  type : "" , 
                  managerUsername: "", 
                  startTime: "", endTime: "", 
                  
                  location:{ 
                    address:{
                      street : "" ,
                      number: "", 
                      city : "" , 
                      postalNumber : ""
                      }, 
                    
                    longitude:"", 
                    latitude:""
                  }, 
                    
                    logo : null
                }
         }) 


        const rules = computed(() => {
          return{
              addObjectDTO : { 
                  name: {required,alpha}, 
                  type : {required}, 
                  managerUsername: {required}, 
                  startTime: {required} , 
                  endTime: {required}, 
                  location:{ 
                    address:{
                      street : { required,alpha},
                      number: {required,}, 
                      city : {required}, 
                      postalNumber : {
                        required,
                        numeric,
                        minLength : helpers.withMessage('Must be 5 characters long' , minLength(5)),
                        maxLength : helpers.withMessage('Must be 5 characters long' , maxLength(5))
                      }
                      } 
                    
                    // longitude:"", 
                    // latitude:""
                  }, 
                    
                    //logo : null
                }
          }
        })

        const v$ = useValidate(rules,state)
        
        return{
          state,
          v$
        }

        },

        created(){
            axios
                .get('http://localhost:8081/WebShopREST/rest/sport_objects/types')
                .then((response) =>{
                    this.state.types = response.data
                })
        
            axios
                .get('http://localhost:8081/WebShopREST/rest/users/available/managers')
                .then((response) =>{
                    this.state.managers = response.data
                    if(this.state.managers == null){
                        this.state.showManagers = false
                    }
                })
        },
        methods:{
            checkForManagers(){
                return this.managers == null
            },
            getFullName(object){
                return object.firstName + " " + object.lastName;
            },
            canSubmit(){
                return this.canSubmit
            },
            addNewSportObject(){
                this.v$.$validate()
                 if(!this.v$.$error){
                    axios
                    .post('http://localhost:8081/WebShopREST/rest/sport_objects/create', this.addObjectDTO)
                    .then((response) => {
                        this.newSportObject = response.data
                    })
                 }
                
                // this.$router.push("/")
            },
            convertType(it){
                switch(it){
                    case "GYM":
                        return "Gym"
                    case "POOL":
                        return "Pool"
                     case "SPORTS_CENTER":
                        return "Sports Center"
                     case "DANCE_STUDIO":
                        return "Dance Studio"
                }
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

.stoma{
    font-size:x-large
}

hr.solid {
  border-top: 3px solid #bbb;
}

select:invalid { color: gray; }

.enrico {
  text-decoration: none;
  color: inherit;
}
</style>
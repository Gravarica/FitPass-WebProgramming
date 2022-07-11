<template>
<section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 20px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 center">NEW TRAINING</h3>
            <form onsubmit="scheduleTraining()">

              <div class="row pb-4">
                    <div class="">
                        <select v-model="selected" required class="type form-select form-outline form-select-lg " aria-label=".form-select-lg example">
                            <option value=null disabled>Choose sport object</option>
                            <option v-for="it in state.sportObjects" :value="it">{{it.name}}</option>
                        </select>
                        <span class="jabuka" v-if="v$.newTrainingDTO.sportObject.$error">
                          {{ v$.newTrainingDTO.sportObject.$errors[0].$message}}
                        </span>
                    </div>
              </div>

               <div class="row pb-4">
                    <div>
                         <select v-model="state.newTrainingDTO.customer" required class="type form-select form-outline form-select-lg" aria-label=".form-select-lg example">
                            <option value=null disabled>Choose customer</option>
                            <option v-for="it in state.customers" :value="it">{{getFullName(it)}}</option>
                        </select>
                        <span class="jabuka" v-if="v$.newTrainingDTO.customer.$error">
                          {{ v$.newTrainingDTO.customer.$errors[0].$message}}
                        </span>
                    </div>
              </div>


              <div class="row pb-4">
                    <div>
                         <select v-model="state.newTrainingDTO.training" required class="type form-select form-outline form-select-lg" aria-label=".form-select-lg example">
                            <option value=null disabled>Choose training</option>
                            <option v-for="it in state.trainings" :value="it">{{it.name}}</option>
                        </select>
                        <span class="jabuka" v-if="v$.newTrainingDTO.training.$error">
                          {{ v$.newTrainingDTO.training.$errors[0].$message}}
                        </span>
                    </div>
              </div>

            
              <hr/>
               <div class="row">
                <div class="mb-3 d-flex align-items-center">

                  <div class="form-outline datepicker w-100">
                    <label for="birthdayDate" class="form-label">Please select training date:</label>
                    <input type="date" v-model="state.newTrainingDTO.checkInDate" class="form-control form-control-lg" id="birthdayDate"/>
                     <span class="jabuka" v-if="v$.newTrainingDTO.checkInDate.$error">
                          {{ v$.newTrainingDTO.checkInDate.$errors[0].$message}}
                      </span>
                  </div>

                </div>
              </div>

              <div class="d-grid gap-2 pt-5 md-6">
                <input class=" btn-lg btn-warning" type="button" @click="scheduleTraining()" value="Submit"/>
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
import { required, helpers} from '@vuelidate/validators'
import {reactive,computed} from 'vue'

    export default {
      
      setup(){
        const state = reactive({
          sportObjects : null,
          trainings : null,
          customers : null,
          
          
          newTrainingDTO : { 
            sportObject : null , 
            training : null , 
            customer : null , 
            checkInDate : null
          },
        })
     
        const rules = computed(() =>{
          return{
            newTrainingDTO : { 
              sportObject : {required} , 
              training : {required}, 
              customer : {required}, 
              checkInDate : {required}
            }
          }
        })

        const v$ = useValidate(rules,state)
         return{
          state,
          v$
        }

      },

      data(){
        return{
          selected : null
        }
      },
      
      methods: {
            fillTrainings(id){
                axios
                    .get('http://localhost:8081/WebShopREST/rest/trainings/sport_object/' + id)
                    .then((response) =>{
                        this.state.trainings = response.data
                    })
            },
            fillCustomers(id){
                 axios
                    .get('http://localhost:8081/WebShopREST/rest/users/sport_object/visited/' + id)
                    .then((response) =>{
                        this.state.customers = response.data
                    })
            },
            scheduleTraining(){
                this.v$.$validate()
                 if(!this.v$.$error){
                    axios
                    .post('http://localhost:8081/WebShopREST/rest/training_histories/schedule',this.newTrainingDTO)
                    .then((response) =>{
                        console.log(response.data)
                    })
                 }
            },
            getFullName(object){
                return object.firstName + " " + object.lastName 
            }
        },
        watch:{
            selected(object){
                this.state.newTrainingDTO.sportObject = object
                this.fillTrainings(object.id)
                this.fillCustomers(object.id)
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
         
select:invalid { color: gray; }

</style>


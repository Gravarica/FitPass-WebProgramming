<template>

<section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 20px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 center">New Training</h3>
            <form>
                
                <div class="row speed pb-3">
                    <div class="col-md-12">
                        <div class="form-outline">
                            <input v-model="state.newContentDTO.name" @keyup="checkName()" type="text" id="lastName" placeholder="Name" class="form-control form-control-lg"/>
                        </div>
                         <span class="jabuka row jabukaZZ" v-if="v$.newContentDTO.name.$error">
                                {{ v$.newContentDTO.name.$errors[0].$message}}
                        </span>
                        <span v-if="isAvailable" class="notavailable">Name allready taken, try again.</span>
                    </div>
                </div>

                <div class="pb-4">
                     <select required v-model="this.state.newContentDTO.type" class="type form-select form-outline form-select-lg" aria-label=".form-select-lg example">
                        <option value="" disabled>Please select type of training</option>
                        <option value="GROUP">Group</option>
                        <option value="PERSONAL">Personal</option>
                    </select>
                     <span class="jabuka row jabukaZZ" v-if="v$.newContentDTO.type.$error">
                                {{ v$.newContentDTO.type.$errors[0].$message}}
                        </span>
                </div>

                <div class="pb-4">
                     <select required v-model="this.state.newContentDTO.trainer" class="type form-select form-outline form-select-lg" aria-label=".form-select-lg example">
                        <option value=null disabled>Select trainer</option>
                        <option v-for="it in this.state.trainers" :value="it" >{{getFullName(it)}}</option>
                    </select>
                    <span class="jabuka row jabukaZZ" v-if="v$.newContentDTO.trainer.$error">
                                {{ v$.newContentDTO.trainer.$errors[0].$message}}
                        </span>
                </div>

                <hr class="solid">

                <div class="row speed pb-3">
                    <div class="col-md-12">
                        <div class="form-outline ">
                            <label class="form-label kobaja" for="lastName">Please select picture for new training:</label>
                            <input type="file" id="lastName"  class="form-control form-control-lg"/>
                        </div>
                         <span class="jabuka row jabukaZZ" v-if="v$.newContentDTO.imgUrl.$error">
                                {{ v$.newContentDTO.imgUrl.$errors[0].$message}}
                         </span>
                    </div>
                </div>

                <hr class="solid">

                <div class="row speed pb-4">
                    <div class="col-md-12">

                        <div class="form-outline">
                            <label class="form-label kobaja" for="lastName">Description:</label>
                            <textarea id="lastName" class="form-control form-control-lg" rows="3"></textarea>           
                        </div>

                    </div>
                </div>

                <div class="row speed pb-3">
                    <div class="col-md-12">
                        <div class="form-outline ">
                            <input v-model="state.newContentDTO.duration" type="text" id="lastName" placeholder="Duration (in minutes)" class="form-control form-control-lg"/>
                        </div>
                         <span class="jabuka row jabukaZZ" v-if="v$.newContentDTO.duration.$error">
                                {{ v$.newContentDTO.duration.$errors[0].$message}}
                         </span>
                    </div>
                </div>
                
                <div class="row speed">
                    <div class="d-grid gap-2 pt-5 md-6">
                        <input class="btn btn-lg btn-grad" type="button" value="Submit" @click="createContent()"/>
                    </div>
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
import axios from "axios";
import useValidate from '@vuelidate/core'
import { required, sameAs, minLength, helpers, alpha,numeric, maxLength} from '@vuelidate/validators'
import {reactive,computed} from 'vue'
    
    export default {
      setup(){
        const state = reactive({
            newContentDTO : {
                name : "",
                imgUrl : "",
                type : "",
                duration : null,
                description : "",
                trainer : null
            },

            types : null,
            loggedUser : null,
            trainers : null
        })
      
        const rules = computed(() =>{
            return{
                newContentDTO : {
                    name : {required},
                    type : {required},
                    duration : {numeric},
                    description : {alpha},
                    imgUrl : {},
                    trainer : {required}
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
            .get('http://localhost:8081/WebShopREST/rest/sport_objects/content/types')
            .then((response) =>{
                this.state.types = response.data
                console.log(this.state.types)
            })

        axios
            .get('http://localhost:8081/WebShopREST/rest/users/loggedUser')
            .then((response) =>{
                this.state.loggedUser = response.data
                console.log(this.state.loggedUser)
            })

        axios
            .get('http://localhost:8081/WebShopREST/rest/users/get/trainers')
            .then((response) =>{
                this.state.trainers = response.data
            })

      },
      methods:{
        createContent(){
            this.v$.$validate()
            if(!this.v$.$error && this.isAvailable == false){
                 axios
                    .post('http://localhost:8081/WebShopREST/rest/trainings/create',this.state.newContentDTO)
                    .then((response)=>{
                        alert("You have successfully added new content to your object!")
                        this.$router.push({path: '/'})
                })
            }
        },
        checkName(){
             axios
                .get('http://localhost:8081/WebShopREST/rest/trainings/check/name/' + this.state.newContentDTO.name + "/" + this.state.loggedUser.objectId)
                .then((response) =>{
                  this.isAvailable = response.data
                })
        },
        getFullName(object){
            return object.firstName + " " + object.lastName
        }
      },
      data(){
        return{
          isAvailable : false,
          responseMessage : ""
        }
      }
    }
</script>


<style>
    .selectzz{
        width:100%;
        margin-left: 0px;
    }
    .speed{
        margin-bottom: 10px;
    }
    .kobaja{
        padding-bottom: 10px;
    }

    .jabukaZZ{
        margin-left: 5px;
    }
</style>
<template>

<section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 20px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 center">New Content</h3>
            <form onsubmit="return false">
                
                <div class="row speed pb-3">
                    <div class="col-md-12">
                        <div class="form-outline ">
                            <input type="text" id="lastName" placeholder="Name" class="form-control form-control-lg"/>
                        </div>
                    </div>
                </div>

                <div class="pb-4">
                    <select required v-model="this.state.newContentDTO.type" class="type form-select form-outline form-select-lg" aria-label=".form-select-lg example">
                        <option value="" disabled>Please select type of content</option>
                        <option v-for="it in state.types" :value="it">{{showType(it)}}</option>
                    </select>
                </div>

                <div class="row speed pb-3">
                    <div class="col-md-12">
                        <div class="form-outline ">
                            <input type="file" id="lastName"  class="form-control form-control-lg"/>
                        </div>
                    </div>
                </div>

                <div class="row speed pb-4">
                    <div class="col-md-12">

                        <div class="form-outline">
                            <label class="form-label kobaja" for="lastName">Description:</label>
                            <textarea id="lastName" class="form-control form-control-lg" rows="3"></textarea>           
                        </div>

                    </div>
                </div>
                
                <div class="row speed">
                    <div class="d-grid gap-2 pt-5 md-6">
                        <input class="btn btn-lg btn-grad" type="submit" value="Submit" @click="submitComment()"/>
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
                description : ""
            },

            types : null,
            loggedUser : null
        })
      
        const rules = computed(() =>{
            return{
                newContentDTO : {
                    name : {required},
                    type : {required}
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
            })
      },
      methods:{
        showType(object){
            return object.toLower()
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
</style>
<template>
    <section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 20px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5 center">Edit Content</h3>
            <form>
                
                <div class="row speed pb-3">
                    <div class="col-md-12">
                        <div class="form-outline">
                            <input v-model="state.name" @keyup="checkName()" type="text" id="lastName" placeholder="Name" class="form-control form-control-lg"/>
                        </div>
                         <span class="jabuka row jabukaZZ" v-if="v$.name.$error">
                                {{ v$.name.$errors[0].$message}}
                        </span>
                        <span v-if="isAvailable" class="notavailable">Name allready taken, try again.</span>
                    </div>
                </div>

                <div class="pb-4">
                     <select required v-model="this.state.type" class="type form-select form-outline form-select-lg" aria-label=".form-select-lg example">
                        <option value="" disabled>Please select type of content</option>
                        <option v-for="it in state.types" :value="it">{{getNames(it)}}</option>
                    </select>
                    <span class="jabuka row jabukaZZ" v-if="v$.type.$error">
                                {{ v$.type.$errors[0].$message}}
                    </span>
                </div>

                <hr class="solid">

                <div class="row speed pb-3">
                    <div class="col-md-12">
                        <div class="form-outline ">
                            <label class="form-label kobaja" for="lastName">Please select picture for new content:</label>
                            <input type="file" id="lastName"  class="form-control form-control-lg"/>
                        </div>
                         <span class="jabuka row jabukaZZ" v-if="v$.imgUrl.$error">
                                {{ v$.imgUrl.$errors[0].$message}}
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
                            <input v-model="state.duration" type="text" id="lastName" placeholder="Duration" class="form-control form-control-lg"/>
                        </div>
                         <span class="jabuka row jabukaZZ" v-if="v$.duration.$error">
                                {{ v$.duration.$errors[0].$message}}
                         </span>
                    </div>
                </div>
                
                <div class="row speed">
                    <div class="d-grid gap-2 pt-5 md-6">
                        <input class="btn btn-lg btn-grad" type="submit" value="Submit" @click="createContent()"/>
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
        props: {
            name: String,
            description: String,
            ctype: String,
            duration: Number
        },
        setup(){
            const state = reactive({
                    name: '',
                    description: '',
                    ctype: '',
                    duration: '',
                    imgUrl: '',

            types : null,
            loggedUser : null
            })

            const rules = computed(() =>{
            return{
                newContentDTO : {
                    name : {required},
                    type : {required},
                    duration : {numeric},
                    description : {alpha},
                    imgUrl : {required}
                }
            }
            })
            
            const v$ = useValidate(rules,state)
                return{
                state,
                v$
            }
            
        },
        methods: {
            getNames(object){
            switch(object){
                case "PERSONAL_TRAINING":
                    return "Personal Training"
                
                case "GROUP_TRAINING":
                    return "Group Training"
                
                case "SAUNA":
                    return "Sauna"
                
                case "YOGA":
                    return "Yoga"
                
                case "PILATES":
                    return "Pilates"
                
            }
            }      
        }
    }
</script>


<style>
</style>
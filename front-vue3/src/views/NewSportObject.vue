<template>
<section class="h-100 bg-dark">
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
                <h3 class="mb-5 text-uppercase">New Object</h3>

                <div class="row">
                  <div class="mb-4">
                    <div class="form-outline">
                      <input type="text" id="form3Example1m" placeholder="Name"  required v-model="name" class="form-control form-control-lg" />
                    </div>
                  </div>
                </div>

                <div class="row">
                    <div>
                         <select class="type form-select form-outline form-select-lg mb-4" required aria-label=".form-select-lg example">
                            <option value="" disabled selected hidden>Please choose type of Sport Object</option>
                            <option v-for="it in types" :value="it">{{it}}</option>
                        </select>
                    </div>
                </div>

                 <div class="row">
                    <div class="col-md-6">
                        <select class="type form-select form-outline form-select-lg mb-4"  required aria-label=".form-select-lg example">
                            <option value="" disabled selected hidden>City</option>
                            <option value="Beograd">Beograd</option>
                            <option value="Novi Sad">Novi Sad</option>
                            <option value="Nis">Subotica</option>
                            <option value="Krajugevac">Krajugevac</option>
                            <option value="Cacak">Cacak</option>
                        </select>
                    </div>
                    
                    <div class="col-md-6">
                         <input type="text" v-model="postalNumber" required id="form3Example1m" placeholder="Postal Number" class="form-control form-control-lg" />
                    </div>
                </div>

                <div class="form-outline mb-4">
                  <input type="text" required v-model="adress" id="form3Example8" placeholder="Adress" class="form-control form-control-lg" />
                </div>

                <div class="row" v-if="showManagers">
                    <div>
                         <select required class="type form-select form-outline form-select-lg mb-4" aria-label=".form-select-lg example">
                            <option value="" disabled selected hidden>Please choose manager</option>
                            <option v-for="it in managers" :value="it">{{getFullName(it)}}</option>
                        </select>
                    </div>
                </div>

                <div class="row" v-if="!showManagers">
                    <div>
                         <p>They are no available managers, but you can allways create new one</p>
                         <router-link :to="{name : ''}">Add Manager</router-link>
                    </div>
                </div>

                 <hr class="solid">

                <div class="form-outline mb-4 row">
                  <div class="col-md-6"><input type="text" required id="form3Example8" placeholder="From" class="form-control form-control-lg" /></div>
                  <div class="col-md-6"><input type="text" required id="form3Example8" placeholder="To" class="form-control form-control-lg" /></div>
                </div>

                <div class="row mt-4 mb-3">
                    <div>
                         <p>Please select sport object's work hour</p>
                    </div>
                </div>
                
                <div class="d-flex justify-content-end pt-5">
                  <input type="submit" @click="addNewSportObject" class="btn btn-warning btn-lg ms-2" value="Submit">
                </div>
                
                

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
import { FLIPPED_ALIAS_KEYS, tsNonNullExpression } from '@babel/types'
import axios from 'axios'

    export default{
        data(){
            return {
                types : null,
                selected : null,
                managers : null,
                showManagers : true,
                canSubmit : false,
                name : null,
                type : null,
                city : null,
                postalNumber : null,
                adress : null,
                manager : null,
                from : null,
                to : null,
            }
        },
        created(){
            axios
                .get('http://localhost:8081/WebShopREST/rest/sport_objects/types')
                .then((response) =>{
                    this.types = response.data
                })
        
            axios
                .get('http://localhost:8081/WebShopREST/rest/users/available/managers')
                .then((response) =>{
                    this.managers = response.data
                    if(this.managers == null){
                        this.showManagers = false
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
            }
        }
    }
</script>

<style>
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
</style>
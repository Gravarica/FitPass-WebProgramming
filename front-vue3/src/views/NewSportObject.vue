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

                <form id="myForm" onsubmit="addNewSportObject()">
                <div class="row">
                  <div class="mb-4">
                    <div class="form-outline">
                      <input type="text" id="form3Example1m" placeholder="Name" pattern="[a-z]{3-15}" required v-model="addObjectDTO.name" class="form-control form-control-lg" />
                    </div>
                  </div>
                </div>

                <div class="row">
                    <div>
                         <select v-model="addObjectDTO.type" class="type form-select form-outline form-select-lg mb-4" required aria-label=".form-select-lg example">
                            <option value="" disabled>Please choose type of Sport Object</option>
                            <option v-for="it in types" :value="it">{{convertType(it)}}</option>
                        </select>
                    </div>
                </div>

                 <div class="row">
                    <div class="col-md-6">
                        <select v-model="addObjectDTO.location.address.city" class="type form-select form-outline form-select-lg mb-4"  required aria-label=".form-select-lg example">
                            <option value="" disabled>City</option>
                            <option value="Beograd">Beograd</option>
                            <option value="Novi Sad">Novi Sad</option>
                            <option value="Nis">Subotica</option>
                            <option value="Krajugevac">Krajugevac</option>
                            <option value="Cacak">Cacak</option>
                        </select>
                    </div>
                    
                    <div class="col-md-6">
                         <input type="text" v-model="addObjectDTO.location.address.postalNumber" required id="form3Example1m" placeholder="Postal Number" class="form-control form-control-lg" />
                    </div>
                </div>

                <div class="form-outline mb-4 row">
                  <div class="col-md-8"><input type="text" required v-model="addObjectDTO.location.address.street" id="form3Example8" placeholder="Adress" class="form-control form-control-lg" /></div>
                  <div class="col-md-4"><input type="text" required v-model="addObjectDTO.location.address.number" id="form3Example8" placeholder="Number" class="form-control form-control-lg" /></div>
                </div>

                <div class="row" v-if="showManagers">
                    <div>
                         <select v-model="addObjectDTO.managerUsername" required class="type form-select form-outline form-select-lg mb-4" aria-label=".form-select-lg example">
                            <option value="" disabled>Please choose manager</option>
                            <option v-for="it in managers" :value="it.username">{{getFullName(it)}}</option>
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
                  <div class="col-md-6"><input v-model="startTime" type="text" required id="form3Example8" placeholder="From" class="form-control form-control-lg" /></div>
                  <div class="col-md-6"><input v-model="endTime" type="text" required id="form3Example8" placeholder="To" class="form-control form-control-lg" /></div>
                </div>

                <div class="row mt-4 mb-3">
                    <div>
                         <p>Please select sport object's work hour</p>
                    </div>
                </div>
                
                <div class="d-flex justify-content-end pt-5">
                  <input type="submit" class="btn btn-warning btn-lg ms-2" value="Submit">
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

    export default{
        data(){
            return {
                types : null,
                selected : null,
                managers : null,
                showManagers : true,
                canSubmit : false,
                newSportObject : null,
                addObjectDTO : { name: "", type : "" , managerUsername: "", startTime: "", endTime: "", location:{ address:{ street : "" , number: "", city : "" , postalNumber : ""}, longitude:"", latitude:""} , logo : null}
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
            },
            addNewSportObject(){
                axios
                    .post('http://localhost:8081/WebShopREST/rest/sport_objects/create', this.addObjectDTO)
                    .then((response) => {
                        this.newSportObject = response.data
                    })
                this.$router.push("/")
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
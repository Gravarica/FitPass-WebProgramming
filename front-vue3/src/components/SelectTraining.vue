<template>

 <section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 20px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Please select Training, that you wish to add trainer to.</h3>
            <form>
              <table class="table table-hover table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>Name</th>
                        <th>Duration</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <tbody>
                  <tr v-for="t in showTrainings()" @click="setSelectedItem(t)">
                    <td>{{t.name}}</td>
                    <td>{{t.duration}}</td>
                    <td>{{t.description}}</td>
                  </tr>
                </tbody>
              </table>
              <div class="mt-4 pt-2">
                <li><router-link :to="{name : ''}"><input :disabled="selectedItem==null" class="btn btn-lg btn-grad" type="submit" value="Next" @click="register"/></router-link></li>
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
    export default {
        data(){
            return {
                objectID : -1,
                trainings : null,
                selectedItem : null,
                color : null
            }
        },
        methods: {
            setSelectedItem(object){
              this.selectedItem = object
              this.color = 'red'
            },
            getType(o){
                return o.type.toString()
            },
            showTrainings(id){
              axios
              .get('http://localhost:8081/WebShopREST/rest/trainings/sport_object/' + id)
              .then((response) => {
                    console.log("USAO")
                    this.trainings = response.data;
                    if(this.trainings == null){console.log("NULA")}
                    else{console.log("KITA")}
                }) 
            },
        },
       created(){
          this.emitter.on('load-trainings', (evt) => {
              console.log("USAO")
              this.objectID = evt
              console.log("objectID" + this.objectID)
              this.showTrainings(evt)
            })
       },
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

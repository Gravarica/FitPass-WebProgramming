<template>

 <section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 20px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Please select Sport Object, so we can show you all available type of trainings</h3>
            <form>
              <table class="table table-hover table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>Name</th>
                        <th>City</th>
                        <th>Addres</th>
                    </tr>
                </thead>
                <tbody>
                  <tr v-for="o in sportObjects" @click="setSelectedItem(o)">
                    <td>{{o.name}}</td>
                    <td>{{o.location.address.city}}</td>
                    <td>{{getStreet(o)}}</td>
                  </tr>
                </tbody>
              </table>
              <div class="mt-4 pt-2">
                <input v-if="selectedItem!=null" class="btn btn-lg btn-grad" type="submit" value="Next" @click="register"/>
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
import axios from 'axios'
    export default {
        data(){
            return {
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
                sportObjects : null,
                selectedItem : null
            }
        },
        methods: {
            register(){
                if (this.registrationDTO.password != this.passwordCheck){
                    this.toast = true
                    return
                }
                axios
                    .post('http://localhost:8081/WebShopREST/rest/users/registration', this.registrationDTO)
                    .then((response) => {
                        console.log('Uspesna registracija')
                        this.$router.push({path: '/'})
                    })
            },
            getStreet(object){
              return object.location.address.street + ", " + object.location.address.number;
            },
            setSelectedItem(object){
              this.selectedItem = object
            },
        },
         created(){
        axios
            .get('http://localhost:8081/WebShopREST/rest/sport_objects/get')
            .then((response) => {
                this.sportObjects = response.data;
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

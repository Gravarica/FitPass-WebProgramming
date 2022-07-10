<template>
     <section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 20px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Edit Profile</h3>
            <form onsubmit="return false">

              <div class="row">
                <div class="col-md-6 mb-4">

                  <div class="form-outline">
                    <input type="text" id="firstName" class="form-control form-control-lg"  v-model="this.loggedUserDTO.firstName"/>
                    <label class="form-label" for="firstName">First Name</label>
                  </div>

                </div>
                <div class="col-md-6 mb-4">

                  <div class="form-outline">
                    <input type="text" id="lastName" class="form-control form-control-lg"  v-model="this.loggedUserDTO.lastName"/>
                    <label class="form-label" for="lastName">Last Name</label>
                  </div>

                </div>
              </div>

              <div class="row">
                <div class="col-md-6 mb-3 d-flex align-items-center">

                  <div class="form-outline datepicker w-100">
                    <input type="date" class="form-control form-control-lg" id="birthdayDate"  v-model="this.loggedUserDTO.dateOfBirth"/>
                    <label for="birthdayDate" class="form-label">Birthday</label>
                  </div>

                </div>
              </div>
              <hr/>
              <div class="row mt-4">
                <div class="col-md-12 pb-2">

                  <div class="form-outline">
                    <input type="text" id="phoneNumber" class="form-control form-control-lg " disabled :placeholder="this.loggedUserDTO.username"/>
                    <label class="form-label" for="username">Username</label>
                  </div>

                </div>
              </div>

              <div class="row">
                <div class="col-12">
                  <div class="invalid-feedback" v-if="wrongPassword">Wrong old password</div>
                  <input type="password" id="password" class="form-control form-control-lg"  v-model="this.password"/>
                  <label class="form-label select-label">Old Password</label>

                </div>
              </div>

              <div class="row">
                <div class="col-12">
                  <div class="feedback" v-if="this.passwordMatch">Passwords dont match</div>
                  <input type="password" id="password-re" class="form-control form-control-lg"  v-model="this.passwordNew"/>
                  <label class="form-label select-label">New Password</label>

                </div>
              </div>

              <div class="row">
                <div class="col-12">
                  <div class="feedback" v-if="!this.oldAndNewMatch">Passwords dont match</div>
                  <input type="password" id="password-re" class="form-control form-control-lg"  v-model="this.passwordCheck"/>
                  <label class="form-label select-label">Confirm Password</label>

                </div>
              </div>

              <div class="mt-4 pt-2">
                <input class="btn btn-lg btn-grad" type="submit" value="Submit" @click="submit"/>
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
    import axios from 'axios';

    export default {
        data() {
            return {
                loggedUserDTO: {username: '', firstName: '', lastName: '', dateOfBirth: '', role: '', gender: '', password: ''},
                password: '',
                passwordNew: '',
                passwordCheck: '',
                wrongPassword: false,
                passwordMatch: false,
                oldAndNewMatch: true,
            }
        },
        mounted(){
            axios
                .get('http://localhost:8081/WebShopREST/rest/users/account_info')
                .then((response) => {
                    this.loggedUserDTO = response.data;
                    console.log(this.loggedUserDTO)
                })
        },
        methods: {
            submit() {
                if(this.passwordNew != this.passwordCheck){
                    this.oldAndNewMatch = false;
                    return
                }
                this.oldAndNewMatch = true;
                let userEditDTO = {
                    username: this.loggedUserDTO.username,
                    firstName: this.loggedUserDTO.firstName,
                    lastName: this.loggedUserDTO.lastName,
                    dateOfBirth: this.loggedUserDTO.dateOfBirth,
                    oldPassword: this.password,
                    newPassword: this.passwordNew
                }
                axios
                    .put("http://localhost:8081/WebShopREST/rest/users/editProfile", userEditDTO)
                    .then((response) => {
                        this.loggedUserDTO = response.data
                    })
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
.feedback {
    color: #DD2476
}
</style>
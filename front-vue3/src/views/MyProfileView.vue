<template>
<section class="vh-100 background">
    <div class="conti">
    <div class="container emp-profile background">
            <div class="row d-flex justify-content-center">
                <div class="col-md-4">
                    <div class="profile-img img-thumbnail">
                        <img src='./../assets/profile/ADMIN.png' alt=""/>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="profile-head">
                                <h2>
                                    {{this.userData.firstName}} {{this.userData.lastName}}
                                </h2>
                                <h4>
                                    {{this.getRole()}}
                                </h4>
                                    <input type="submit" class="btn-grad" name="btnAddMore" value="Edit Profile" @click='goToMyProfile'/>
                    </div>
                </div>
            </div>
            <div class="row raspored mt-5">
                <div class="col-md-8">
                    <div class="tab-content profile-tab" id="myTabContent">
                        <div class="container" id="home" role="tabpanel" aria-labelledby="home-tab">
                                    <div class="row">
                                        <div class="col-md-8">
                                            <label>Username</label>
                                        </div>
                                        <div class="col-md-4">
                                            <p>{{this.userData.username}}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-8">
                                            <label>First Name</label>
                                        </div>
                                        <div class="col-md-4">
                                            <p>{{this.userData.firstName}}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-8">
                                            <label>Last Name</label>
                                        </div>
                                        <div class="col-md-4">
                                            <p>{{this.userData.lastName}}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-8">
                                            <label>Date of Birth:</label>
                                        </div>
                                        <div class="col-md-4">
                                            <p>{{this.getDateOfBirth()}}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-8">
                                            <label>Gender</label>
                                        </div>
                                        <div class="col-md-4">
                                            <p>{{this.getGender()}}</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-8">
                                            <label>E-Mail</label>
                                        </div>
                                        <div class="col-md-4">
                                            <p>gravarica@gmail.com</p>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-8">
                                            <label>Phone Number</label>
                                        </div>
                                        <div class="col-md-4">
                                            <p>0637437123</p>
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
import axios from 'axios'

    export default {
        data(){
            return{
                userData: {username: '', firstName: '', lastName: '', dateOfBirth: '', role: '', gender: ''},
                username: '',
            }
        },
        mounted(){
            axios
                .get('http://localhost:8081/WebShopREST/rest/users/account_info')
                .then((response) => {
                    this.userData = response.data;
                })
            
        },
        methods:{
            getDateOfBirth(){
                let date = this.userData.dateOfBirth.split('-')
                return date[2] + '.' + date[1] + '.' + date[0] + '.'
            },
            getGender(){
                if (this.userData.gender == 'MALE'){
                    return 'Male'
                } 

                return 'Female'
            },
            getRole(){
                console.log('EEEEEEE' + this.userData.role)
                if (this.userData.role == 'ADMIN'){
                    return 'Administrator'
                } else if (this.userData.role == 'CUSTOMER'){
                    return 'Customer'
                } else if (this.userData.role == 'MANAGER') {
                    return 'Manager'
                } 

                return 'Trainer'
            },
            getImgUrl(role, gender){
                let images = require.context('../assets/profile/', false, /\.png$/);
                console.log(role)
                if(this.userData.role != 'CUSTOMER'){
                    return images('./' + role + ".png")
                }
                
                return images('./' + role + '_' + gender + ".png")
            },
            goToMyProfile(){
                this.$router.push({
                    path: '/editprofile'
                })
            }
        }
    }
</script>

<style>
    .background{
  background: -webkit-linear-gradient(to bottom right, rgba(240, 147, 251, 1), rgba(245, 87, 108, 1));
  background-color: #f7b42c;
  background-image: linear-gradient(315deg, #f7b42c 0%, #fc575e 74%);
}
.conti{
    padding-top: 8%;
}
.emp-profile{
  padding: 3%;
  margin-top: 0%;
  width: 40%;
  margin-bottom: 11%;
  border-radius: 0.5rem;
  background: #fff;
}
.profile-img{
  text-align: left;
  border-radius: 20px;
  padding-top: 10px;
  padding-left: 15px;
  margin-right: 40px;
  padding-right: 10px;
  border: 1 px solid salmon;
}
.conti p, label{
    font-size: 20px;
}
.profile-img img{
  width: 140px;
  height: 140px;
  border-radius: 20px;
  border: 3 px solid salmon;
}

.img-thumbnail{
  border: 3 px solid salmon;
}

.profile-head h2{
  color: #333;
}
.profile-head h4{
  color: #0062cc;
}
.profile-edit-btn{
  border: none;
  border-radius: 1.5rem;
  width: 30%;
  height: 40px;
  padding: 0%;
  font-weight: 600;
  color: #6c757d;
  cursor: pointer;
}
.proile-rating{
  font-size: 12px;
  color: #818182;
  margin-top: 5%;
}
.proile-rating span{
  color: #495057;
  font-size: 15px;
  font-weight: 600;
}
.profile-head .nav-tabs{
  margin-bottom:5%;
}
.profile-head .nav-tabs .nav-link{
  font-weight:600;
  border: none;
}
.profile-head .nav-tabs .nav-link.active{
  border: none;
  border-bottom:2px solid #0062cc;
}
.profile-work{
  padding: 14%;
  margin-top: -15%;
}
.profile-work p{
  font-size: 12px;
  color: #818182;
  font-weight: 600;
  margin-top: 10%;
}
.profile-work a{
  text-decoration: none;
  color: #495057;
  font-weight: 600;
  font-size: 14px;
}
.profile-work ul{
  list-style: none;
}
.profile-tab label{
  font-weight: 600;
}
.profile-tab p{
  font-weight: 600;
  color: #0062cc;
}

.btn-grad {
  background-image: linear-gradient(to right, #FF512F 0%, #DD2476  51%, #FF512F  100%);
  padding: 10px 25px;
  margin-top: 40px;
  text-align: center;
  text-transform: uppercase;
  transition: 0.5s;
  background-size: 200% auto;
  color: white;            
  box-shadow: 0 0 0px #eee;
  border: 0;
  border-radius: 10px;
  display: block;
}

.btn-grad:hover {
  background-position: right center; /* change the direction of the change here */
  color: #fff;
  text-decoration: none;
}

.raspored{
  padding-left: 5%;
  padding-right: 5%;
}
</style>
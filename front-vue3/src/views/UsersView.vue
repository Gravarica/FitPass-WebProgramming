<template>
<div class="container-flex">
    <section class="ftco-section sheesh vh-100">
		<div class="container obod">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h2 class="heading-section pt-4">Users in System</h2>
				</div>
			</div>
            <div class="row">
                <div class="col-md-8 ms-left">
                    <div class="inputic">
                        <input type="text" class="putin" placeholder="Pretraga...">
                    </div>
                </div>
            </div>
			<div class="row">
				<div class="col-md-12">
					<div class="table-wrap tablica">
						<table class="table table-hover cabela">
						  <thead class="thead-primary table-primary">
						    <tr>
						      <th>#</th>
						      <th>Username</th>
						      <th>Name</th>
						      <th>Role</th>
							  <th>Status</th>
							  <th>Points</th>
                              <th></th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr v-for="(user,index) in this.users">
						      <th scope="row">{{index + 1}}</th>
						      <td>{{user.username}}</td>
						      <td>{{user.firstName}} {{user.lastName}}</td>
						      <td>{{user.role}}</td>
							  <td>{{getCustomerType(user)}}</td>
							  <td>{{user.totalPoints}}</td>
                              <td><button type="button" class="btn btn-danger" @click="showPopup(user)" v-if="user.role != 'ADMIN'">Delete</button></td>
						    </tr>
						  </tbody>
						</table>
					</div>
				</div>
			</div>
		
        <ConfirmationDialogue @execute-del="execute()" @close="closePopup()" ref="popup" v-if="show">
            <h5>Are you sure you want to delete this user. This action can't be undone!</h5>
        </ConfirmationDialogue>

        </div>
	</section>
</div>
</template>

<script>
import axios from 'axios'
import ConfirmationDialogue from '@/components/ConfirmationDialogue.vue'

    export default {
        data(){
            return{
                users: null,
                show : false,
                deleteUser : null
            }
            
        },
        mounted(){
            axios
                .get('http://localhost:8081/WebShopREST/rest/users/get')
                .then((response) => {
                    this.users = response.data
                })
        },
        methods:{
            getCustomerType(user){
                if(user.customerType == null){
                    return ''
                }

                return user.customerType.name
            },
            execute(){
                axios
                    .delete('http://localhost:8081/WebShopREST/rest/users/delete/' + this.deleteUser.username)
                
                this.users = this.users.filter(i => i.username !== this.deleteUser.username)
                this.show = false
                alert("You have successfully deleted user" + this.deleteUser.firstName + " " + this.deleteUser.lastName)
            },
        
            showPopup(object){
                this.show = true
                this.deleteUser = object
                console.log(this.deleteUser.username)
            },
            closePopup(){
                this.show = false
            }
        },
    
        components : {ConfirmationDialogue}
    }
</script>

<style>
    .sheesh{
        background: -webkit-linear-gradient(to bottom right, rgba(240, 147, 251, 1), rgba(245, 87, 108, 1));
        background-color: #f7b42c;
        background-image: linear-gradient(315deg, #f7b42c 0%, #fc575e 74%);
        padding-top: 10%;
    }
    .obod{
        background-color: white;
        border-radius: 10px;
    }
    .tablica{
        font-size: 15px;
        padding: 30px;
    }
    .putin {
    box-sizing: border-box;
    margin-top: 10px;
    margin-left: 10px;
    padding: 10px;
    border: 1px solid #eee;
    box-shadow:0 0 15px 4px rgba(0,0,0,0.06);
    border-radius:10px;
  }
  .inputic{
    margin-left: 15px;
  }
  .cabela{
    box-shadow: 0 0 10px 4px rgba(0,0,0,0.07);;
  }
</style>
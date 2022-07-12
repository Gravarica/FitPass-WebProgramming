<template>
    <div class="container-flex">
    <section class="ftco-section sheesh vh-100">
        <div class="headerinho d-flex justify-content-center">
            <h1> Trainers </h1>
        </div>
		<div class="container obod">
			<div class="row">
				<div class="col-md-12">
                    
					<div class="table-wrap tablica">
                        <div class="scrollable-panelz">
						<table class="table table-hover cabela">
						  <thead class="thead-primary table-primary">
						    <tr>
						      <th>#</th>
						      <th class="hoverboard">Username</th>
						      <th class="hoverboard">First Name</th>
                              <th class="hoverboard">Last Name</th>
						      <th>Role</th>		  
						    </tr>
						  </thead>
						  <tbody>
						    <tr v-for="(user,index) in this.users">
						      <th scope="row">{{index + 1}}</th>
						      <td>{{user.username}}</td>
						      <td>{{user.firstName}}</td>
                              <td>{{user.lastName}}</td>
						      <td>{{user.role}}</td>
						    </tr>
						  </tbody>
						</table>
					</div>
                    </div>
				</div>
			</div>

        </div>
	</section>
</div>
</template>


<script>
import axios from 'axios'


    export default {
        props: {
            objectId: Number
        },
        data(){
            return{
                users: null
            }
        },
        created(){
            axios
                .get('http://localhost:8081/WebShopREST/rest/trainings/trainers/object/' + this.$props.objectId)
                .then(response => {
                    this.users = response.data
                })
        }
    }
</script>


<style>
</style>
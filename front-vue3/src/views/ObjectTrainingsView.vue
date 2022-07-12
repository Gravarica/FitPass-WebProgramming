<template>
    <div class="container-flex">
    <section class="ftco-section sheesh vh-100">
        <div class="headerinho d-flex justify-content-center">
            <h1> Trainings </h1>
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
						      <th class="hoverboard">Name</th>
						      <th class="hoverboard">Trainer</th>
                              <th class="hoverboard">Type</th>
                              <th class="hoverboard">Description</th>
                              <th class="hoverboard">Duration (min)</th>	  
						    </tr>
						  </thead>
						  <tbody>
						    <tr v-for="(user,index) in this.trainings">
						      <th scope="row">{{index + 1}}</th>
						      <td>{{user.name}}</td>
						      <td>{{this.getTrainer(user.trainer)}}</td>
                              <td>{{user.type}}</td>
                              <td>{{user.description}}</td>
						      <td>{{user.duration}}</td>
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
import TrainerRegistrationViewVue from './TrainerRegistrationView.vue'


    export default {
        props: {
            objectId: Number
        },
        data(){
            return{
                trainings: null
            }
        },
        created(){
            axios
                .get('http://localhost:8081/WebShopREST/rest/trainings/sport_object/' + this.$props.objectId)
                .then(response => {
                    this.trainings = response.data
                })
        },
        methods: {
            getTrainer(trainer){
            if (trainer == null){
                return 'No Trainer'
            } 

                return trainer.firstName + " " + trainer.lastName
            }
        }
        
    }
</script>


<style>
</style>
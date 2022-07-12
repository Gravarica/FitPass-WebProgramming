<template>
 <div class="gradient-custom mw-100 container-fluid vh-200">

  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Training History</h1>
        <p class="lead text-center">Here you can see your training history in previous month.</p>
        <p>
          <a href="#" @click="loadTrainingHistory()" class="btn btn-primary my-2">ALL TRAININGS</a>
          <a href="#" @click="showPersonal()" class="btn btn-primary my-2">PERSONAL TRAININGS</a>
          <a href="#" @click="showGroup()" class="btn btn-primary my-2">GROUP TRAININGS</a>
        </p>
      </div>
    </div>
  </section>

<div class="container-fluid kokain" v-if="hasHistory">
    <div class="row row-cols-md-3">
        <div class="col-5" v-for="t in trainingHistory">
        <TrainingAlbumCard 
            @update-list="updateList()"
            @show-popup = "showPopup"
            :sport-object-name="t.training.object.name"
            :training-name="t.training.name"
            :check-in-date="t.checkInDate"
            :duration="t.training.duration"
            :training-type="t.training.type"
            :customer="t.customer"
            :is-trainer="true"
            :can-delete="t.canCancel"
            :object = t>
        </TrainingAlbumCard>
        </div>   
    </div>
    <ConfirmationDialogue  @execute-del="execute()" @close="closePopup()" ref="popup" v-if="show">
                    <h5>Are you sure you want to cancel this training. This action can't be undone!</h5>
    </ConfirmationDialogue>
</div>

<div class="active container-fluid" v-if="!hasHistory">
    <div class="main-container container">
        <div class="inner-container container">
            <div class="container">
                <h2>You dont have any trainings, but you can allways schedule a new one!</h2>
            </div>
            <div class="container pt-5 center">
                <router-link class="enrico" :to="{name: 'ScheduleTraining'}"><button class="btn btn-warning btn-lg ludilo">Schedule New Training</button></router-link>
            </div>
        </div>
    </div>
</div>


</div>

</template>


<script>
import axios from 'axios'
import TrainingAlbumCard from '../components/TrainingAlbumCard.vue'
import ConfirmationDialogue from "@/components/ConfirmationDialogue.vue";

   export default {
    data() {
        return {
            trainingHistory: null,
            loggedUser: null,
            hasHistory : null,
            toDelete : null,
            show : false
        };
    },
    components:{
        TrainingAlbumCard,
        ConfirmationDialogue 
    },
    methods: {
        getSource(name) {
            return "../assets/" + name + ".png";
        },
        loadTrainingHistory(){
            axios
            .get("http://localhost:8081/WebShopREST/rest/training_histories/trainer/" + this.loggedUser.username)
            .then((response) => {
                console.log(this.hasHistory)
                this.trainingHistory = response.data;
                if (response.data.lenght == 0) {
                    this.hasHistory = false;
                }else{
                    this.hasHistory = true
                }
                console.log(this.hasHistory)
            });
        },
        showPersonal(){
             axios
            .get("http://localhost:8081/WebShopREST/rest/training_histories/trainer/personal/" + this.loggedUser.username)
            .then((response) => {
                this.trainingHistory = response.data;
            });
        },
        showGroup(){
            axios
            .get("http://localhost:8081/WebShopREST/rest/training_histories/trainer/group/" + this.loggedUser.username)
            .then((response) => {
                this.trainingHistory = response.data;
            });
        },
        updateList(id){
            console.log(id)
            this.trainingHistory = this.trainingHistory.filter(i => i.id !== id)   
        },
        showPopup(id){
            console.log(id)
            this.show = true
            this.toDelete = id
        },
        closePopup(){
            this.show = false
        },
        execute(){
            console.log(this.toDelete)
            axios
                .delete('http://localhost:8081/WebShopREST/rest/training_histories/cancel/' + this.toDelete)
            
            this.trainingHistory = this.trainingHistory.filter(i => i.id !== this.toDelete)
            this.closePopup()
            alert("You have successfully canceled training")
        }
    },
    created() {
        axios
            .get("http://localhost:8081/WebShopREST/rest/users/loggedUser")
            .then((response) => {
            console.log(this.hasHistory);
            this.loggedUser = response.data;
            this.loadTrainingHistory()
        });
        
    }
}
</script>

<style>
    .kokain{
        padding-left: 250px;
    }
</style>
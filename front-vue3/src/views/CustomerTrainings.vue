<template>

  <div class="gradient-custom wh-100">

  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Training History</h1>
        <p class="lead text-center">Here you can see your training history in previous month.</p>
        <p>
          <!-- <a href="#" class="btn btn-primary my-2">Main call to action</a>
          <a href="#" class="btn btn-secondary my-2">Secondary action</a> -->
        </p>
      </div>
    </div>
  </section>

<div class="container" v-if="hasHistory">
    <div class="row row-cols-md-3">
        <div class="col-5" v-for="t in trainingHistory">
        <TrainingAlbumCard
            :sport-object-name="t.training.object.name"
            :training-name="t.training.name"
            :check-in-date="t.checkInDate"
            :duration="t.training.duration"
            :training-type="t.training.type"
            :customer="t.customer"
            :is-trainer="false">
        </TrainingAlbumCard>
        </div>   
    </div>
</div>



</div>
</template>

<script>
import axios from 'axios'
import TrainingAlbumCard from '../components/TrainingAlbumCard.vue'

   export default{
    data() {
        return {
            trainingHistory: null,
            loggedUser: null,
            hasHistory : false
        };
    },
    components:{
        TrainingAlbumCard
    },
    methods: {
        getSource(name) {
            return "../assets/" + name + ".png";
        },
        loadTrainingHistory(){
            axios
            .get("http://localhost:8081/WebShopREST/rest/training_histories/customer/" + this.loggedUser.username)
            .then((response) => {
                this.trainingHistory = response.data;
                if (this.trainingHistory != null) {
                    this.hasHistory = true;
                }
            });
        }
    },
    created() {
        axios
            .get("http://localhost:8081/WebShopREST/rest/users/loggedUser")
            .then((response) => {
            console.log(response.data);
            this.loggedUser = response.data;
            console.log("USERNAME: " + this.loggedUser.username);
            this.loadTrainingHistory()
        });
        
    }
}
</script>

<style>

</style>
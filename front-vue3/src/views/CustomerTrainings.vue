<template>

  <div class="gradient-custom mw-100 container-fluid vh-200">

  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-9 col-md-8 mx-auto">
        <h1 class="fw-light">Training History</h1>
        <p class="lead text-center">Here you can see your training history in previous month.</p>
        <p class="lead text-center" v-if="this.subscription.active">Of course you can allways schedule a new one!</p>
        <p class="lead text-center" v-else>Your subscription has expired, hurry up and renew it so you can start training ASAP!</p>
        <p>
          <div class="container pt-5 center" v-if="hasHistory">
                <router-link v-if="this.subscription.active || this.subscription.totalAppearances - this.subscription.doneTrainings > 0" class="enrico" :to="{name: 'CustomerScheduleTraining'}"><button class="btn btn-warning btn-lg ludilo">Schedule New Training</button></router-link>
                <router-link v-else class="enrico" :to="{name: 'Subscribe'}"><button class="btn btn-warning btn-lg ludilo">Renew Subscription</button></router-link>
            </div>
        </p>
      </div>
    </div>
  </section>

<div class="container-fluid kokain gradient-custom vh-100" v-if="hasHistory">
    <div class="row row-cols-md-3">
        <div class="col-5" v-for="t in trainingHistory">
        <TrainingAlbumCard
            :sport-object-name="t.training.object.name"
            :training-name="t.training.name"
            :check-in-date="t.checkInDate"
            :duration="t.training.duration"
            :training-type="t.training.type"
            :trainer="t.trainer"
            :is-trainer="false">
        </TrainingAlbumCard>
        </div>   
    </div>
</div>

<div class="active container-fluid" v-if="!hasHistory">
    <div class="main-container container">
        <div class="inner-container container">
            <div class="container">
                <h2>You don't have any trainings in last month, hurry up and schedule a new one!</h2>
            </div>
            <div class="container pt-5 center">
                <router-link v-if="this.canSchedule" class="enrico" :to="{name: 'CustomerScheduleTraining'}"><button class="btn btn-warning btn-lg ludilo">Schedule New Training</button></router-link>
            </div>
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
            hasHistory : true,
            canSchedule : true,
             subscription: {active: false}
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
            console.log(this.hasHistory)
            axios
            .get("http://localhost:8081/WebShopREST/rest/training_histories/customer/" + this.loggedUser.username)
            .then((response) => {
                console.log(response.data)
                this.trainingHistory = response.data;
                if (response == undefined) {
                    this.hasHistory = false;
                }
                console.log(this.hasHistory)
            });
        }
    },
    created() {
        axios
            .get("http://localhost:8081/WebShopREST/rest/users/loggedUser")
            .then((response) => {
            this.loggedUser = response.data;
            console.log("USERNAME: " + this.loggedUser.username);
            this.loadTrainingHistory()
        });
        
        axios
            .get('http://localhost:8081/WebShopREST/rest/subscriptions/get/user')
            .then((response) =>{
                console.log("DATAAAAAA" + response.data)
                this.subscription = response.data
            })
    }
}
</script>

<style>
.lol{
    padding: 10;
}

.enrico {
  text-decoration: none;
  color: inherit;
}

.ludilo{
    width: 500px;
    align-self: center;
}

.center{
   text-align: center;
}
</style>
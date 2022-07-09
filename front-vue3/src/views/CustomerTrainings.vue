<template>

  <body>

  <section class="py-5 text-center container">
    <div class="row py-lg-5">
      <div class="col-lg-6 col-md-8 mx-auto">
        <h1 class="fw-light">Training History</h1>
        <p class="lead text-muted">Something short and leading about the collection below—its contents, the creator, etc. Make it short and sweet, but not too short so folks don’t simply skip over it entirely.</p>
        <p>
          <a href="#" class="btn btn-primary my-2">Main call to action</a>
          <a href="#" class="btn btn-secondary my-2">Secondary action</a>
        </p>
      </div>
    </div>
  </section>

<!-- <div class="card-deck" v-for="t in trainingHistory">
        <TrainingAlbumCard 
                    :sport-object-name="t.training.object.name"
                    :training-name="t.training.name">

        </TrainingAlbumCard>
</div> -->

<!-- <div class="card">
    <img src="../assets/Flex.png" class="card-img-top" alt="...">
    <div class="card-body">
      <h5 class="card-title">Card title</h5>
      <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
    </div>
</div> -->

<div class="container">
    <div class="row row-cols-md-3">
        <div class="col-5" v-for="t in trainingHistory">
        <TrainingAlbumCard
            :sport-object-name="t.training.object.name"
            :training-name="t.training.name">
        </TrainingAlbumCard>
        </div>
       
</div>
</div>





<footer class="text-muted py-5">
  <!-- <div class="container">
    <p class="float-end mb-1">
      <a href="#">Back to top</a>
    </p>
    <p class="mb-1">Album example is &copy; Bootstrap, but please download and customize it for yourself!</p>
    <p class="mb-0">New to Bootstrap? <a href="/">Visit the homepage</a> or read our <a href="../getting-started/introduction/">getting started guide</a>.</p>
  </div> -->
</footer>
</body>
</template>

<script>
import axios from 'axios'
import TrainingAlbumCard from '../components/TrainingAlbumCard.vue'

   export default{
    data() {
        return {
            trainingHistory: null,
            loggedUser: null
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
                console.log("USAO")
                this.trainingHistory = response.data;
                console.log(this.trainingHistory)
                if (this.trainingHistory == null) {
                    console.log("PRAZNO");
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
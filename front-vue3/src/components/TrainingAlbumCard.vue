<template>
     <div>
        <div class="card lotina">
            <img :src="getSource()" class="card-img-top" alt="...">
            <div class="card-header okvir infozz bg-dark text-white">
                Training Info
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Gym: {{sportObjectName}}</li>
                <li class="list-group-item">Name: {{trainingName}}</li>
                <li class="list-group-item">Type: {{trainingType}}</li>
                <li class="list-group-item" v-if="isTrainer">Customer: {{getFullName(customer)}}</li>
                <li class="list-group-item" v-else>Trainer: {{getFullName(trainer)}}</li>
                <li class="list-group-item">Date: {{checkInDate}}</li>
                <li class="list-group-item">Duration: {{setDuration()}}</li>
            </ul>
            <div class="d-grid gap-2 pt-0 md-6" v-if="canDelete">
                <input type="submit" class=" btn btn-warning btn-lg" @click="showPopup()" value="Cancel Training">
               
            </div>
    </div>
     </div>
</template>


<script>
import axios from "axios";

    export default{
    data() {
        return {
        };
    },
    props: {
        sportObjectName: String,
        trainingName: String,
        trainingType: String,
        duration: Number,
        checkInDate: Date,
        isTrainer: Boolean,
        trainer: Object,
        customer: Object,
        canDelete: Boolean,
        object : Object
    },
    methods: {
        getSource() {
            let images = require.context("../assets/", false, /\.png$/);
            return images("./" + this.sportObjectName + ".png");
        },
        setDuration() {
            return this.duration + "min";
        },
        getFullName(object) {
            return object.firstName + " " + object.lastName;
        },
        showPopup(){
            console.log("ID" + this.object.id)
            this.$emit('show-popup',this.object.id)
        },
        closePopup(){
            this.show = false
        },
        execute(){
            console.log(this.object.id)
            axios
                .delete('http://localhost:8081/WebShopREST/rest/training_histories/cancel/' + this.object.id)
            
            this.$emit('update-list',this.object.id)
            this.closePopup()
       }
    }
}

</script>

<style>

.lotina{
    width: 25rem;
    margin-bottom: 100px;
    border: 2px solid #a4a4e8;
}

.infozz{
    text-align: center;
    margin-bottom: 0px;
}

</style>

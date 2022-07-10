<template>
     <div>
        <div class="card lotina">
            <img :src="getSource()" class="card-img-top" alt="...">
            <div class="card-header info bg-dark text-white">
                Training Info
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Gym: {{sportObjectName}}</li>
                <li class="list-group-item">Name: {{trainingName}}</li>
                <li class="list-group-item">Type: {{trainingType}}</li>
                <li class="list-group-item" v-if="isTrainer">Customer: {{getFullName(trainer)}}</li>
                <li class="list-group-item" v-else>Trainer: {{getFullName(customer)}}</li>
                <li class="list-group-item">Date: {{checkInDate}}</li>
                <li class="list-group-item">Duration: {{setDuration()}}</li>
            </ul>
            <div class="d-grid gap-2 pt-0 md-6" v-if="canDelete">
                <input type="submit" class=" btn btn-warning btn-lg" value="Cancel Training">
            </div>
    </div>
     </div>
</template>


<script>
    export default{
        data(){
            return{

            }
        },
        props:{
            sportObjectName : String,
            trainingName : String,
            trainingType : String,
            duration : Number,
            checkInDate : Date,
            isTrainer : Boolean,
            trainer : Object,
            customer : Object,
            canDelete : Boolean
        },
        methods:{
            getSource(){
                let images = require.context('../assets/', false, /\.png$/);
                return images('./' + this.sportObjectName + ".png")
            },
            setDuration(){
                return this.duration + "min"
            },
            getFullName(object){
                return object.firstName + " " + object.lastName
            }
        }
    }

</script>

<style>

.lotina{
    width: 18rem;
}

.info{
    text-align: center;
    
}

</style>

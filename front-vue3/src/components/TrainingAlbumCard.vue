<template>
     <div>
        <div class="card lotina">
            <img :src="getSource()" class="card-img-top" alt="...">
            <ul class="list-group list-group-flush">
                <li class="list-group-item">Gym: {{sportObjectName}}</li>
                <li class="list-group-item">Training Name: {{trainingName}}</li>
                <li class="list-group-item">Training Type: {{trainingType}}</li>
                <li class="list-group-item">Date: {{checkInDate}}</li>
                <li class="list-group-item">Duration: {{setDuration()}}</li>
            </ul>
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
            checkInDate : Date
        },
        methods:{
            getSource(){
                let images = require.context('../assets/', false, /\.png$/);
                return images('./' + this.sportObjectName + ".png")
            },
            setDuration(){
                return this.duration + "min"
            }
        }
    }

</script>

<style>

.lotina{
    width: 18rem;
}

</style>

<template>
    <Carousel :settings="settings" :breakpoints="breakpoints">
        <Slide class="card__wrapper" v-for="object in sportObjects" :key="object.id">
            <SportObject
               :name="object.name"
               :city="object.location.address.city"
               :id="object.id">
                <template v-slot:body>
                    <p>{{object.location.address.street}} {{object.location.address.number}}</p>
                    <p><span :class ="[{ badgezy2: object.status}, { badgezy1 : !object.status}]">{{object.status ? 'Otvoreno' : 'Zatvoreno'}} </span></p>
                    <p><span class="badge badgezy">{{object.averageGrade}}</span></p>
                </template>   
            </SportObject>
        </Slide>

        <template #addons>
            <navigation />
        </template>
    </Carousel>
</template>

<script>
import SportObject from "@/components/SportObject.vue";
import { Carousel, Slide, Navigation } from "vue3-carousel";
import 'vue3-carousel/dist/carousel.css';
import axios from 'axios'

export default {
    components:{
        SportObject,
        Carousel,
        Navigation,
        Slide
    },
    data(){
        return{
            sportObjects: null,

            settings: {
                itemsToShow: 4,
                wrapAround: true,
                snapAlign: "center",
            },
            breakpoints: {
            300: {
            itemsToShow: 5,
            },
            700: {
            itemsToShow: 5,
            },
            },
        }
    },
    created(){
        axios
            .get('http://localhost:8081/WebShopREST/rest/sport_objects/get')
            .then((response) => {
                this.sportObjects = response.data;
            })
    },
    methods: {
        filterByCity(city){
            axios
            .get('http://localhost:8081/WebShopREST/rest/sport_objects/search/city/' + city)
            .then((response) => {
                this.sportObjects = response.data;
            })
        }
    },
}


</script>


<style>

.openClass {
    color: #468847;
    border: 1px solid #468847;
    background-color: transparent;
}

.closedClass {
    color: #df4759;
    border: 1px solid #df4759;
    background-color: transparent;
}

.carousel__item {
  min-height: 200px;
  width: 100%;
  background-color: var(--vc-clr-primary);
  color:  var(--vc-clr-white);
  font-size: 20px;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.carousel__slide {
  padding: 10px;
}

.carousel__prev,
.carousel__next {
  box-sizing: content-box;
  border: 5px solid white;
}

.badgezy1{
    border-radius: 5px;
    margin-right: 5px;
    margin-left: 5px;
    margin-top: 5px;
    margin-bottom: 10px;
    border: 1px solid #ee7600;
    color: #ee7600;
    background-color: transparent;
    padding: 5px;
}

.badgezy2{
    border-radius: 5px;
    margin-right: 5px;
    margin-top: 5px;
    margin-bottom: 10px;
    margin-left: 5px;
    font-size: 15px;
    font-weight: 100;
    border: 1px solid #357960;
    color: #357950;
    background-color: transparent;
    padding: 5px;
}

.badgezy{
    border-radius: 5px;
    background-color: #ee7600;
    margin-right: 10px;
    margin-top: 5px;
    margin-bottom: 10px;
    margin-left: 10px;
    padding: 5px;
    font-size: 16;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
}
</style>
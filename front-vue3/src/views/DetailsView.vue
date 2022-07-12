<template>
    <div class="container image-slider">
    </div>
    <div class="container-fluid konti pl-md-3 pr-md-3 pl-1 pr-1">
        <div class="row mt-5 spoljni-okvir">
            <div class="col-md-12 col-lg-9">
                <div class="header-info">
                    <div class="d-flex">
                        <img :src="this.getImgUrl()" class="slika"/>
                        <div class="ml-1 naslovnica">
                            <h1 class="black-text mb-0">{{this.object.name}}</h1>
                            <div class="pointer">
                                <i class="fas fa-star orange-text"></i>
                                <p>Ocena:</p>
                                <span class="badge badgez">{{this.object.averageGrade}}</span>
                            </div>
                        </div>
                    </div>
                    <div class="disciplines mt-1 position-relative">
                        <span class="badge badgez1">{{this.getType()}}</span>
                    </div>
                </div>
                <div class="row main-info-row">
                    <div class="col-4 radno-vreme">
                        <h4 class="black-text headline">Radno vreme</h4>
                        <div class="work-hours mt-1 pr-3">
                            <div class="d-flex justify-content-between mb-2">
                                <span>Ponedeljak</span>
                                <span>{{this.object.workHour.startTime}} - {{this.object.workHour.endTime}}</span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span>Utorak</span>
                                <span>{{this.object.workHour.startTime}} - {{this.object.workHour.endTime}}</span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span>Sreda</span>
                                <span>{{this.object.workHour.startTime}} - {{this.object.workHour.endTime}}</span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span>Cetvrtak</span>
                                <span>{{this.object.workHour.startTime}} - {{this.object.workHour.endTime}}</span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span>Petak</span>
                                <span>{{this.object.workHour.startTime}} - {{this.object.workHour.endTime}}</span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span>Subota</span>
                                <span>{{this.object.workHour.startTime}} - {{this.object.workHour.endTime}}</span>
                            </div>
                            <div class="d-flex justify-content-between mb-2">
                                <span>Nedelja</span>
                                <span>{{this.object.workHour.startTime}} - {{this.object.workHour.endTime}}</span>
                            </div>
                        </div>
                    </div>
                    <div class="col-7 pl-lg-2 pr-lg-3 mt-1 mt-md-0 description" >
                        <h4 class="black-text headline">Opis</h4>
                        <div class="text-content mt-1">
                            <p>
                                {{this.object.description}}
                            </p>
                        </div>
                    </div>
                </div>
                <div class="comments">
                    <h4 class="black-text title pb-1">Komentari i ocene</h4>
                    <div class="comment-item pt-1 mb-1" v-for="comment in this.comments">
                        <div class="top-part d-flex align-items-center pointer sheeshz">
                            <span class="badge badgez" style="margin-right: 20px">{{comment.grade}}</span>
                            <span>{{comment.time}}</span>
                        </div>
                            <p class="mt-1 mb-0 comment-text">"{{comment.text}}"</p>
                    </div>
                </div>
            </div>
            <div class="col-md-12 col-lg-3">
                <div class="basic-info">
                    <div class="map-container">
                        <OneObjectMap :longitude="this.object.location.longitude"
                                      :latitude="this.object.location.latitude"></OneObjectMap>
                    </div>
                    <div class="address-info">
                        <h6 class="pr-2 address-holder mb-1 position-relative">
                            <i class="fas fa-map-marker-alt pin mr-1"></i>
                            {{this.object.location.address.street}} {{this.object.location.address.number}}, {{this.object.location.address.city}}
                        </h6>
                    </div>
                </div>    
            </div>
            
        </div>
    </div>
</template>

<script>
    import Map from '../components/Map.vue';
import OneObjectMap from '@/components/OneObjectMap.vue';
import axios from 'axios';
    export default {
        components: {
            Map,
            OneObjectMap
        },
        props: {
            objectId: null
        },
        data(){
            return{
                object: { name: '',
                          sportObjectType: '',
                          contents: null,
                          status: false,
                          location: null,
                          averageGrade: null,
                          workHour: null,
                          description: ''},
                comments: {
                    grade: null,
                    time: '',
                    text: ''
                }
            }
        },
        created() {
            axios
                .get('http://localhost:8081/WebShopREST/rest/sport_objects/get/' + this.$props.objectId)
                .then(response => {
                    this.object = response.data
                })
            axios
                .get('http://localhost:8081/WebShopREST/rest/comments/get/id/' + this.$props.objectId)
                .then(response => {
                    this.comments = response.data
                })
        },
        methods: {
            getType(){
                if(this.object.sportObjectType == 'GYM'){
                    return 'Teretana'
                } else if(this.object.sportObjectType == 'POOL'){
                    return 'Pool'
                } else if(this.object.sportObjectType == 'DANCE_STUDIO'){
                    return 'Dance Studio'
                }

                return 'Sports Center'
            },
            getImgUrl(object){
                let images = require.context('../assets/sheesh/', false, /\.png$/);
                return images('./' + this.object.name + ".png")
        }
        }
    }
</script>

<style>
    @import url('https://fonts.googleapis.com/css2?family=Lato&display=swap');

    .badgez{
    border-radius: 5px;
    background-color: #ee7600;
    margin-right: 15px;
    margin-top: 5px;
    margin-bottom: 10px;
    padding: 5px;
    font-size: 16;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
}
.badgez1{
    border-radius: 10px;
    margin-right: 10px;
    margin-top: 5px;
    margin-bottom: 10px;
    font-size: 15px;
    font-weight: 100;
    border: 1px solid #ee7600;
    color: #ee7600;
    background-color: transparent;
    padding: 5px;
}
.naslovnica{
    margin-left:15px;
}
.main-info-row{
    margin-top: 20px;
}
.work-hours{
    border-top: 1px solid 	#F0F0F0;
    padding-top: 20px;
    color:  #6d6d64;
    margin-right: 0px;
}


.spoljni-okvir{
    margin-left: 300px;
    margin-right: 300px;
}

.radno-vreme{
    margin-right: 70px;
    font-size: 20px;
}

.slika{
    border-radius: 10px;
    margin-top: 4.47px;
}

.pointer{
    display: flex;
    align-items: center;
}

.pointer p {
    text-align: center;
}

.pointer > * {
    margin: 0px 10px;
    margin-left: 0px;
}
.text-content p{
    text-align: left;
    border-top: 1px solid 	#F0F0F0;
    padding-top: 20px;
    color: #6d6d64
}

.text-content > *{
    margin: 0px auto;
}
.description{
    margin-left: 0px;
    font-size: 20px;
}
.map-container{
    height: 300px;
    width: 280px;
    padding-left: 20px;
    margin-bottom: 20px;
    padding-top: 20px;
}
.basic-info{

    margin-top: 20px;
    background-color:#cccccc;
    border-radius: 10px;
    width: 300px;
    height: 400px;
}

.address-holder{
    margin-left: 20px;
    margin-right: 20px;
    padding-bottom: 20px;
    margin-top: 20px;
}

#map{
    height: 100%;
}

.main-info-row{
    margin-bottom: 50px;
    margin-top: 30px;
    margin-right: 250px;
}

.title{
    border-bottom: 1px solid #F0F0F0;
    margin-bottom: 20px;
    padding-bottom: 10px;
    font-size: 30px;
}

.comment-text{
    color: #6d6d64;
}

.comments{
    font-size: 20px;
}

.sheeshz{
    margin-bottom: 20px;
    color: #6d6d64;
}

.comment-item{
    border-bottom: 1px solid #6d6d64;
    padding-bottom: 20px;
    margin-top: 30px;
}

.headline{
    padding-bottom: 5px;
    font-size: 30px;
}

.slika{
    border: 1px solid #a4a4e8
}

template{
    height: 100%;
    font-family: "Lato";
}
</style>
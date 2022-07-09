<template>
    <div class="container-fluid bar">
        <div class="row">
              <div class="col-md-8">
                    <form class="form-inline">
                        <input type="text" id="search" name="search" class="input-small putin" placeholder="Pretraga..." v-model="this.searchDTO.parameter">
                        <select class="selectz" name="searchon" v-model="this.searchDTO.type">
                            <option value="">Discipline</option>
                            <option value="GYM">Teretana</option>
                            <option value="POOL">Bazen</option>
                            <option value="SPORTS_CENTER">Sportski centar</option>
                            <option value="DANCE_STUDIO">Plesni studio</option>
                        </select>
                        <select class="selectz" name="searchon" v-model="this.searchDTO.city">
                            <option value="">Izaberite Grad</option>
                            <option value="Beograd">Beograd</option>
                            <option value="Novi Sad">Novi Sad</option>
                            <option value="Nis">Nis</option>
                            <option value="Subotica">Subotica</option>
                            <option value="Kragujevac">Kragujevac</option>
                            <option value="Cacak">Cacak</option>
                        </select>
                        <input type="submit" class="button btn-succes" v-on:click="submit" value="Pretrazi"/>
                    </form>       
              </div>      
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col scrollable-panel ">
                    <div class="container" v-for="obj in this.sportObjects">
                            <SportObjectCard :name="obj.name"
                                             :street="obj.location.address.street"
                                             :number="obj.location.address.number"
                                             :city="obj.location.address.city"
                                             :averageGrade="obj.averageGrade"></SportObjectCard>
                    </div>
                </div>
                <div class="col-xl-6">
                    <Map></Map>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import SportObjectCard from "../components/SportObjectCard.vue";
import Map from '../components/Map.vue';
import axios from 'axios'
export default{
    components:{
        SportObjectCard,
        Map
    },
    inject: ['objects'],
    data(){
        return{
            sportObjects: null,
            searchDTO: { parameter: '', city: '', type: ''}
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
        submit(){
            console.log('EEEEEEEEE' + this.searchDTO)
            if(this.searchDTO.parameter == '' || this.searchDTO.parameter == 'Pretraga...'){
                if(this.searchDTO.type == ""){
                    axios
                    .get('http://localhost:8081/WebShopREST/rest/sport_objects/search/city/' + this.searchDTO.city)
                    .then((response) => {
                        this.sportObjects = response.data
                    })
                }
            } else if (this.searchDTO.type == "" && this.searchDTO.city == "") {
                axios
                    .get('http://localhost:8081/WebShopREST/rest/sport_objects/search/' + this.searchDTO.parameter)
                    .then((response) => {
                        this.sportObjects = response.data
                    })
            } else {
                axios
                    .get('http://localhost:8081/WebShopREST/rest/sport_objects/search', this.searchDTO)
                    .then((response) => {
                        this.sportObjects = response.data
                    })
            }
            

        }
    }
}
</script>

<style>

.gradient-custom {
/* fallback for old browsers */

/* Chrome 10-25, Safari 5.1-6 */
background: -webkit-linear-gradient(to bottom right, rgba(240, 147, 251, 1), rgba(245, 87, 108, 1));
background-color: #f7b42c;
background-image: linear-gradient(315deg, #f7b42c 0%, #fc575e 74%);
/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

 .putin {
    box-sizing: border-box;
    margin-top: 10px;
    margin-left: 10px;
    padding: 10px;
    border: 1px solid #eee;
    box-shadow:0 0 15px 4px rgba(0,0,0,0.06);
    border-radius:10px;
  }

.selectz{
    margin-top: 10px;
    margin-left: 20px;
    box-sizing: border-box;
    padding: 10px;
    width: 200px;
    box-shadow:0 0 15px 4px rgba(0,0,0,0.06);
    border-radius:10px;
}
.button{
    margin-top: 10px;
    margin-left: 20px;
    box-sizing: border-box;
    border: 0;
    padding: 10px;
    box-shadow:0 0 15px 4px rgba(0,0,0,0.06);
    border-radius:10px;
    background-color: orangered;
    color: #eee;
}
.bar{
    border-bottom: 1px solid;
    padding-bottom: 10px;
    
}

.scrollable-panel{
      overflow-y:scroll;
      height: 82.4vh;
      width:100%;
      }
</style>
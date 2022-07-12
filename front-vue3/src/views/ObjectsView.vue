<template>
    <div class="container-fluid bar">
        <div class="row suiii">
              <div class="col-md-8">
                    <form class="form-inline" onsubmit="return false">
                        <select @change="onChange($event)" class="selectz" style="width: 230px; margin-right: 20px;">
                            <option>Sort by</option>
                            <option value="name-asc">Naziv (rastuce)</option>
                            <option value="name-desc">Naziv (opadajuce)</option>
                            <option value="loc-asc">Lokacija (rastuce)</option>
                            <option value="loc-desc">Lokacija (opadajcue)</option>
                            <option value="grade-asc">Prosecna ocena (rastuce)</option>
                            <option value="grade-desc">Prosecna ocena (opadajuce)</option>
                        </select>
                        <input type="text" id="search" name="search" class="input-small putin" placeholder="Search..." v-model="this.searchInput">
                        <select class="selectz" name="searchon" @change="filterByType($event)">
                            <option value="NONE">Disciplines</option>
                            <option value="GYM">Gym</option>
                            <option value="POOL">Pool</option>
                            <option value="SPORTS_CENTER">Sports center</option>
                            <option value="DANCE_STUDIO">Dance studio</option>
                        </select>
                        <select class="selectz" name="searchon" @change="filterByCity($event)">
                            <option value="none">Choose a city</option>
                            <option value="Beograd">Beograd</option>
                            <option value="Novi Sad">Novi Sad</option>
                            <option value="Nis">Nis</option>
                            <option value="Subotica">Subotica</option>
                            <option value="Kragujevac">Kragujevac</option>
                            <option value="Cacak">Cacak</option>
                        </select>
                        <input type="submit" class="button btn-succes" v-on:click="this.submit()" value="Search" v-bind:disabled="!this.checked"/>
                        <input type="checkbox" id="checkbox" v-model="this.checked" style="margin-left:20px;">
                        <label for="checkbox" style="margin-left:10px" >Combined search</label>
                    </form>       
              </div>      
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col scrollable-panel ">
                    <div v-for="obj in this.filteredList()">
                            <SportObjectCard :name="obj.name"
                                             :street="obj.location.address.street"
                                             :number="obj.location.address.number"
                                             :city="obj.location.address.city"
                                             :averageGrade="obj.averageGrade"
                                             :startTime="obj.workHour.startTime"
                                             :endTime="obj.workHour.endTime"
                                             :status="obj.status"
                                             :id="obj.id"
                                             :tip="obj.sportObjectType"></SportObjectCard>
                    </div>
                </div>
                <div class="col-xl-6" style="margin-top:20px">
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
export default {
    components:{
        SportObjectCard,
        Map
    },
    inject: ['objects'],
    data(){
        return{
            sportObjects: null,
            searchDTO: { parameter: '', city: '', type: ''},
            searchInput: '',
            checked: false
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
        onChange(event){
            let data = event.target.value
            if (data == 'name-asc'){
                this.sortByName(true)
            } else if (data == 'name-desc'){
                this.sortByName(false)
            } else if (data == 'loc-asc'){
                this.sortByLocation(true)
            } else if (data == 'loc-desc'){
                this.sortByLocation(false)
            } else if (data == 'grade-asc'){
                this.sortByGrade(true)
            } else if (data == 'grade-desc'){
                this.sortByGrade(false)
            }
        },
        sortByName(asc){
            axios
                .get('http://localhost:8081/WebShopREST/rest/sport_objects/sort/name/' + asc)
                .then(response => {
                    this.sportObjects = response.data
                })
        },
        sortByLocation(asc){
            axios
                .get('http://localhost:8081/WebShopREST/rest/sport_objects/sort/location/' + asc)
                .then(response => {
                    this.sportObjects = response.data
                })
        },
        sortByGrade(asc){
            axios
                .get('http://localhost:8081/WebShopREST/rest/sport_objects/sort/grade/' + asc)
                .then(response => {
                    this.sportObjects = response.data
                })
        },
        getType(param){
                if(param == 'GYM'){
                    return 'Teretana'
                } else if(param == 'POOL'){
                    return 'Pool'
                } else if(param == 'DANCE_STUDIO'){
                    return 'Dance Studio'
                }

                return 'Sports Center'
        },
        filteredList(){
            return this.sportObjects.filter((obj) => {
                return obj.name.toLowerCase().indexOf(this.searchInput.toLowerCase()) != -1 ||
                       obj.location.address.city.toLowerCase().indexOf(this.searchInput.toLowerCase()) != -1 ||
                       this.getType(obj.type).toLowerCase().indexOf(this.searchInput.toLowerCase()) != -1 ||
                       obj.location.address.street.toLowerCase().indexOf(this.searchInput.toLowerCase()) != -1
            })
        },
        filterByCity(event){
            if(!this.checked){
                axios
                    .get('http://localhost:8081/WebShopREST/rest/sport_objects/search/city/' + event.target.value)
                    .then((response) => {
                        this.sportObjects = response.data;
                    })
            } else {
                this.searchDTO.city = event.target.value
            }
            
        },
        filterByType(event){
            if(!this.checked){
                axios
                    .get('http://localhost:8081/WebShopREST/rest/sport_objects/search/type/' + event.target.value)
                    .then((response) => {
                        this.sportObjects = response.data;
                    })
            } else {
                this.searchDTO.type = event.target.value
            }
            
        },
        submit(){
            axios
                .get('http://localhost:8081/WebShopREST/rest/sport_objects/search/'+this.searchInput+'/'+this.searchDTO.type+'/'+this.searchDTO.city)
                .then(response => {
                    this.sportObjects = response.data
                })
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
    margin-right: 10px;
    margin-left: 20px;
    box-sizing: border-box;
    border: 0;
    padding: 10px;
    box-shadow:0 0 15px 4px rgba(0,0,0,0.06);
    border-radius:10px;
    background-color: orangered;
    color: #eee;
    transition: 0.5s;
}

.button:hover{
    background-color: white;
    color: orangered;
    border-color: orangered;
    border: 1px solid orangered;
    transition: 0.5s;
}

.bar{
    border-bottom: 1px solid;
    padding-bottom: 10px;
    
}

.scrollable-panel{
      overflow-y:scroll;
      height: 82.4vh;
      width:100%;
      margin-top: 20px;
      }

.vertical-line{
    border-left: 1px solid #6d6d64;
}

.suiii{
    border-bottom: 1px solid #F0F0F0;
    padding-bottom: 10px;
    margin-left: 0px;
    margin-right: 0px;
}

</style>
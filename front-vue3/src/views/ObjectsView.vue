<template>
    <div class="container-fluid bar">
        <div class="row">
              <div class="col-md-8">
                    <form class="form-inline" action="#" method="post">
                        <input type="text" id="search" name="search" class="input-small putin" placeholder="Search...">
                        <select class="selectz" name="searchon">
                            <option class="option" value="0">First Name</option>
                            <option value="1">Last Name</option>
                        </select>
                        <select class="select" name="searchon">
                            <option value="0">First Name</option>
                            <option value="1">Last Name</option>
                        </select>
                        <button type="submit" class="button btn-succes">Search</button>
                    </form>       
              </div>      
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col scrollable-panel">
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
            sportObjects: null
        }
    },
    created(){
        axios
            .get('http://localhost:8081/WebShopREST/rest/sport_objects/get')
            .then((response) => {
                this.sportObjects = response.data;
            })
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
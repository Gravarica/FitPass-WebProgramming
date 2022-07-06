<template>
    <div class="container-fluid bar">
        <div class="row">
              <div class="col-md-8">
                    <form class="form-inline" action="#" method="post">
                        <input type="text" id="search" name="search" class="input-small" placeholder="Search...">
                        <select class="select" name="searchon">
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
                    <h2>TITLE HEADING</h2>
                    <h5>Title description, Dec 7, 2020</h5>
                    <div class="fakeimg">Fake Image</div>
                    <p>Some text..</p>
                    <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>

                    <h2 class="mt-5">TITLE HEADING</h2>
                    <h5>Title description, Sep 2, 2020</h5>
                    <div class="fakeimg">Fake Image</div>
                    <p>Some text..</p>
                    <p>Sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import SportObjectCard from "../components/SportObjectCard.vue";
import axios from 'axios'
export default{
    components:{
        SportObjectCard,
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
    input[type=text] {
    box-sizing: border-box;
    margin-top: 10px;
    margin-left: 10px;
    padding: 10px;
    border: 1px solid #eee;
    box-shadow:0 0 15px 4px rgba(0,0,0,0.06);
    border-radius:10px;
  }

.select{
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
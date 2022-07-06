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
                <div class="col">
                    <div class="container" v-for="obj in this.objects">
                            <SportObjectCard :name="obj.name"
                                             :street="obj.address.street"
                                             :number="obj.address.number"
                                             :city="obj.address.city"
                                             :averageGrade="obj.averageGrade"></SportObjectCard>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import SportObjectCard from "../components/SportObjectCard.vue";
export default{
    components:{
        SportObjectCard,
    },
    inject: ['objects'],
    data(){
        return{
            objectsList: null
        }
    },
    created(){
        console.log(this.objects)
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
    border: 1px solid;
    padding-bottom: 10px;
}
</style>
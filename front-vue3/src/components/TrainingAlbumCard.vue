<template>
    <!-- <div class="card shadow-sm">
            <img class="card-img-top" :src="getSource(sportObjectName)" alt="Card image cap"/>
            <div class="card-body">
              <p class="card-text">:training-name</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                  <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                </div>
                <small class="text-muted">9 mins</small>
              </div>
            </div>
          </div> -->

    <!-- <div class="card container">
        <div class="card-body">
            <img class="card-img-top" :src="getSource(sportObjectName)" alt="Card image cap"/>
            <h5 class="card-title">Card title</h5>
            <p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
        </div>
        <div class="card-footer">
            <small class="text-muted">Last updated 3 mins ago</small>
        </div>
  </div> -->

     <div>
        <div class="card lotina">
        <img :src="getSource()" class="card-img-top" alt="...">
        <div class="card-body">
        <h5 class="card-title">{{trainingName}}</h5>
        <p class="card-text">{{sportObjectName}}</p>
        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
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
            trainingName : String
        },
        methods:{
            getSource(){
                let images = require.context('../assets/', false, /\.png$/);
                return images('./' + this.sportObjectName + ".png")
            }
        }
    }

</script>

<style>

.lotina{
    width: 18rem;
}

</style>

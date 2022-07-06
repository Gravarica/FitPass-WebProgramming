<template>
    <div class="container card-basic">
                    <section class="mx-5 my-5" style="max-width: 40rem;">
                        
                      <div class="card booking-card v-2 mt-2 mb-4 rounded-bottom">
                        <div class="bg-image hover-overlay ripple ripple-surface ripple-surface-light" data-mdb-ripple-color="light">
                           <div class="card-img__wrapper">
                              <img :src="getImgUrl(name)" class="img-fluid"/>
                           </div>
                          
                          <a href="#!">
                            <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                          </a>
                        </div>
                        <div class="card-body">
                            <div style="display:flex">
                                <span class="badge badgez">{{averageGrade}}</span>
                                <h4 class="card-title font-weight-bold"><a>{{name}}</a></h4>
                            </div>
                          
                            <p class="card-text">{{street}}  {{number}}, {{city}}</p>
                            <hr class="my-2">
                            <p class="h5 font-weight-bold mb-2">Sadrzaj</p>
                            <ul class="list-unstyled d-flex justify-content-start align-items-center mb-0">
                                <div style="display:flex" class="sadrzaj">
                                    <li>Yoga -</li>
                                    <li>Gym -</li>
                                    <li>Pool</li>
                                </div>
                            </ul>

                            <div style="display:flex" class="mt-2">
                                <span class="badge badgez1">Zatvoreno</span>
                                <p class="text1">Otvara se u 09:00</p>
                            </div>
                        </div>
                      </div>
                      
                    </section>
                  </div>
</template>

<script>
    export default {
        props: {
            name: String,
            averageGrade: String,
            street: String,
            number: String,
            city: String
        },
        methods:{
           getImgUrl(object){
            let images = require.context('../assets/', false, /\.png$/);
            return images('./' + object + ".png")
        }
        }
    }
</script>

<style>
    .badgez{
    border-radius: 5px;
    background-color: #ee7600;
    margin-right: 10px;
    margin-top: 5px;
    margin-bottom: 10px;
    padding: 5px;
    font-size: 16;
    font-family: Verdana, Geneva, Tahoma, sans-serif;
}

.badgez1{
    border-radius: 5px;
    margin-right: 10px;
    margin-top: 5px;
    margin-bottom: 10px;
    border: 1px solid #ee7600;
    color: #ee7600;
    background-color: transparent;
    padding: 5px;
}

.text1{
    margin-right: 10px;
    margin-top: 5px;
    margin-bottom: 10px;
}

.sadrzaj > li{
    margin-right: 10px;
    font-size: medium;
    font-family: verdana;
}

.card-basic{
    width: 70%;
    margin-top: 50px;
}

.card.booking-card.v-2 {
    background-color: transparent;
  }
  
  .card.booking-card.v-2 .fa {
    color: #f7aa00;
  }
  
  .card.booking-card.v-2 .card-body .card-text {
    color: #db2d43;
  }
  
  .card.card.booking-card.v-2 .chip {
    background-color: #87e5da;
  }
  
  .card.booking-card.v-2 .card-body hr {
    border-top: 1px solid #f7aa00;
  }

  .card-img__wrapper {
  position: relative;
}

</style>
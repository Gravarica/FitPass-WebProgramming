<template>
    <div class="container-fluid vh-100 sheesh-cevap" style="background: linear-gradient(315deg, #f7b42c 0%, #fc575e 74%);">
    <div class="naslov container d-flex justify-content-center">
        <h1>Subscribe</h1>
    </div>
    <div class="container p-5">
      <div class="row">
        <div class="col-lg-4 col-md-12 mb-4 cardinho">
          <div class="card card-sheesh h-100 shadow-lg">
            <div class="card-body">
              <div class="text-center p-3">
                <h5 class="card-title">Casual</h5>
                <small>WEEK</small>
                <br><br>
                <span class="h2">1499</span> din.
                <br><br>
              </div>
              <p class="card-text">If you are not looking far ahead in time, this is perfect choice for you!</p>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
              </svg> 5 trainings </li>
              <li class="list-group-item"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
              </svg> 1 showing in a day</li>
              <li class="list-group-item"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
              </svg> Access to all content</li>
            </ul>
            <div class="card-body text-center">
              <button class="btn-grad" style="border-radius:30px" @click="this.goToCheckout(this.casual)">Select</button>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-12 mb-4 cardinho">
          <div class="card card-sheesh h-100 shadow-lg">
            <div class="card-body">
              <div class="text-center p-3">
                <h5 class="card-title">Standard</h5>
                <small>MONTH</small>
                <br><br>
                <span class="h2">4599</span> din. 
                <br><br>
              </div>
              <p class="card-text">Looking for a monthly commitment to your bulk? <br/> Our standard package will suit your muscle growth.</p>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
              </svg> 16 trainings </li>
              <li class="list-group-item"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
              </svg> 1 showing in a day</li>
              <li class="list-group-item"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
              </svg> Access to all content</li>
            </ul>
            <div class="card-body text-center">
              <button class="btn-grad" style="border-radius:30px" @click="this.goToCheckout(this.standard)">Select</button>
            </div>
          </div>
        </div>
        <div class="col-lg-4 col-md-12 mb-4 cardinho">
          <div class="card card-sheesh h-100 shadow-lg">
            <div class="card-body">
              <div class="text-center p-3">
                <h5 class="card-title">Gym Rat</h5>
                <small>YEAR</small>
                <br><br>
                <span class="h2">18499</span> din.
                <br><br>
              </div>
              <p class="card-text">Do you like to sleep at the gym? <br/>Well, we got a solution for you. </p>
            </div>
            <ul class="list-group list-group-flush">
              <li class="list-group-item"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
              </svg> 365 trainings</li>
              <li class="list-group-item"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
              </svg> 3 showings in a day </li>
              <li class="list-group-item"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
              </svg> Access to all content </li>
            </ul>
            <div class="card-body text-center">
              <button class="btn-grad" style="border-radius:30px" @click="this.goToCheckout(this.gymRat)">Select</button>
            </div>
          </div>
        </div>
      </div>    
    </div>
       
    </div>
</template>

<script>
    export default {
        data(){
            return{
                casual: { type: "WEEK", price: 1499, totalAppearances: 5, dailyAppearance: 1, title: 'CASUAL'},
                standard: { type: "MONTH", price: 4599, totalAppearances: 16, dailyAppearance: 1, title: 'STANDARD'},
                gymRat: { type: "YEAR", price: 18499, totalAppearances: 365, dailyAppearance: 3, title: 'GYM RAT'},
                promoCode: {}
            }
        },
        methods: {
            goToCheckout(data){
                // Ovde cu proslediti objekat
                this.$router.push({name: 'Checkout',
                                   params: {
                                        title : data.title,
                                        type : data.type,
                                        price : data.price,
                                        totalAppearances : data.totalAppearances,
                                        dailyAppearance: data.dailyAppearance
                                }})
            }
        }
    }
</script>

<style>
    .card-sheesh {
      border:none;
      padding: 10px 50px;
    }

    .card-sheesh::after {
      position: absolute;
      z-index: -1;
      opacity: 0;
      -webkit-transition: all 0.6s cubic-bezier(0.165, 0.84, 0.44, 1);
      transition: all 0.6s cubic-bezier(0.165, 0.84, 0.44, 1);
    }

    .card-sheesh:hover {


      transform: scale(1.02, 1.02);
      -webkit-transform: scale(1.02, 1.02);
      backface-visibility: hidden; 
      will-change: transform;
      box-shadow: 0 1rem 3rem rgba(0,0,0,.75) !important;
    }

    .card-sheesh:hover::after {
      opacity: 1;
    }

    .card-sheesh:hover .btn-outline-primary{
      color:white;
      background:#007bff;
    }

    .sheesh-cevap{
        padding-top: 150px;
        box-sizing: border-box;
        border: 3px;
    }

    .naslov{
        padding-bottom: 20px;
    }

</style>
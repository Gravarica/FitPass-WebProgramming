<template>
<div class="container-fluid outer background vh-100">
    <div class="container conti-conti">
        <div class="info-holder checkout">
            <h6 class="text-down">Package</h6>
            <div class="info-holder-middle">
                <div class="info-holder-inner">
                    <div class="d-flex align-items-center justify-content-between">
                        <h4>{{title}}</h4>
                        <h4>{{price}} RSD</h4>
                    </div>
                    <span class="small text-down">1 {{type}} / {{totalAppearances}} Trainings / {{dailyAppearance}} appearances daily</span>
                </div>
            </div>
        </div>
        <div class="info-holder pricing">
            <h6 class="text-down">Your bill</h6>
            <div class="info-holder-middle">         
                <div class="infoz">
                    <div class="item d-flex align-items-center justify-content-between">
                        <span class="text-item">Subscription</span>
                        <span>{{price}} RSD</span>
                    </div>
                    <div class="item d-flex align-items-center justify-content-between" v-if="valid">
                        <span class="text-item">Promo code</span>
                        <span>-2999 RSD</span>
                    </div>
                </div>
                <div class="totally-spies">
                    <div class="d-flex justify-content-between">
                        <span class="h3 pr-1">Total</span>
                        <span class="h2">{{price}} RSD</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="promo-code">
            <div class="col-6 ms-l">
                <h6 class="small text-down">Got a promo code?</h6>
                <div class="form-holden">
                    <form class="card p-2">
                        <div class="input-group">
                            <input type="text" class="form-control lolz" placeholder="Enter">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-secondary">Redeem</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="accept-button d-flex justify-content-center">
            <button class="btn-grad levak">Confirm</button>
        </div>
    </div>
</div>
</template>

<script>
    export default {
        props: {
            title: {
                type: String,
                required: true
            },
            price: {
                type: Number,
                required: true,
            },
            totalAppearances: {
                type: Number,
                required: true
            },
            dailyAppearance: {
                type: Number,
                required: true
            },
            type: {
                type: String,
                required: true
            }
        },
        data() {
            return{
                promoCode: ''
            }
        }
    }
</script>

<style>
.outer{
    padding-top: 7%;
}

.conti-conti{
  background-color: white;
  border-radius: 20px;
  padding: 50px;
}

.info-holder-middle {
  border: 1px solid #fc575e;
  border-radius: 5px;
  margin-bottom: 20px;
  padding: 20px;
}

.text-down{
  color: #6d6d64;
}

.text-item{
  color: #6d6d64;
  font-size: 20px;
}

.item{
  padding-bottom: 10px;
}

.infoz{
  border-bottom: 1px solid lightgray;
  margin-bottom: 20px;
}

.promo-code{
  margin-right: 100px;
}

.levak{
    width: 25%;
    height: 50px;
}

.btn-secondary{
  background-image: linear-gradient(to right, #FF512F 0%, #DD2476  51%, #FF512F  100%);
  text-align: center;
  transition: 0.5s;
  background-size: 200% auto;
  color: white;            
  box-shadow: 0 0 0px #eee;
  border-color: #fc575e;
  display: block;
}

.btn-secondary:hover{
  background-position: right center; /* change the direction of the change here */
  color: #fff;
  text-decoration: none;
  border-color:rgba(245, 87, 108, 1);
}

.card{
  border-color: #fc575e;
}

.accept-button{
  margin-top: 50px;
}
</style>
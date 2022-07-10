<template>
<div class="container-fluid outer background vh-100">
    <div class="container conti-conti">
        <div class="info-holder checkout">
            <h6 class="text-down">Package</h6>
            <div class="info-holder-middle">
                <div class="info-holder-inner">
                    <div class="d-flex align-items-center justify-content-between">
                        <h4>{{title}}</h4>
                        <h4>{{priceDisplay}} RSD</h4>
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
                        <span>{{priceDisplay}} RSD</span>
                    </div>
                    <div class="item d-flex align-items-center justify-content-between" v-if="disablePromoCode">
                        <span class="text-item">Promo code</span>
                        <span>-{{this.discountPromo}} RSD</span>
                    </div>
                    <div class="item d-flex align-items-center justify-content-between" v-if="this.customerTypeDTO.show">
                        <span class="text-item">{{this.customerTypeDTO.name}}</span>
                        <span>-{{this.discountType}} RSD</span>
                    </div>
                </div>
                <div class="totally-spies">
                    <div class="d-flex justify-content-between">
                        <span class="h3 pr-1">Total</span>
                        <span class="h2">{{priceDisplay}} RSD</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="promo-code">
            <div class="col-6 ms-l">
                <h6 class="small text-down">Got a promo code?</h6>
                <div class="form-holden">
                    <form class="card p-2" onsubmit="return false">
                        <div class="input-group">
                            <input type="text" class="form-control lolz" placeholder="Enter" v-model="this.promoCode">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-secondary" @click="this.redeem()">Redeem</button>
                            </div>
                        </div>
                    </form>
                </div>
                <h6 class="small message-text">{{this.returnPromoCodeDTO.message}}</h6>
            </div>
        </div>
        <div class="accept-button d-flex justify-content-center">
            <button class="btn-grad levak">Confirm</button>
        </div>
    </div>
</div>
</template>

<script>
import axios from 'axios'

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
                promoCode: '',
                returnPromoCodeDTO: {discount: null, message: ''},
                priceDisplay: this.$props.price,
                disablePromoCode: false,
                discountPromo: null,
                discountType: null,
                customerTypeDTO: { name: '', discount: null, show: false}
            }
        },
        methods: {
            redeem(){
                if(!this.disablePromoCode){
                    axios
                    .get('http://localhost:8081/WebShopREST/rest/promos/redeem/' + this.promoCode)
                    .then(response => {
                        this.returnPromoCodeDTO = response.data
                        this.discountPromo = this.priceDisplay * this.returnPromoCodeDTO.discount/100
                        this.priceDisplay = this.priceDisplay - this.discountPromo
                        this.priceDisplay = Math.round(this.priceDisplay * 100) / 100
                        this.discountPromo = Math.round(this.discountPromo * 100) / 100
                        if(this.returnPromoCodeDTO.discount > 0){
                            this.disablePromoCode = true
                        }
                        
                    })
                } else {
                    this.returnPromoCodeDTO.message = 'You have already redeemed promo code'
                }
                
            }
        },
        mounted(){
            axios
                .get('http://localhost:8081/WebShopREST/rest/users/loggedUser/customerType')
                .then(response => {
                    this.customerTypeDTO = response.data
                    this.discountType = this.priceDisplay * this.customerTypeDTO.discount/100
                    this.priceDisplay = this.priceDisplay - this.discountType
                    this.discountType = Math.round(this.discountType * 100) / 100
                    this.priceDisplay = Math.round(this.priceDisplay * 100) / 100
                })
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

.message-text{
    padding-top: 5px;
    padding-left: 55px;
    color: #a4a4e8;
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
<template>
     <section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 20px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Create Promo Code</h3>
            <form onsubmit="return false">

                <div class="row">
                    <div class="col-md-12 mb-4">

                        <div class="form-outline">
                            <input type="text" id="firstName" class="form-control form-control-lg"  v-model="this.promoCodeDTO.code"/>
                            <label class="form-label" for="firstName">Enter code</label>
                        </div>

                    </div>
                </div>
                
                <div class="row">
                    <div class="col-md-12 mb-4">

                        <div class="form-outline">
                            <input type="number" id="lastName" class="form-control form-control-lg"  v-model="this.promoCodeDTO.numberOfUsages"/>
                            <label class="form-label" for="lastName">Number of usages</label>
                        </div>

                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 mb-4">

                        <div class="form-outline">
                            <input type="number" id="lastName" class="form-control form-control-lg"  v-model="this.promoCodeDTO.discount"/>
                            <label class="form-label" for="lastName">Discount</label>
                        </div>

                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12 mb-3 d-flex align-items-center">

                        <div class="form-outline datepicker w-100">
                            <input type="date" class="form-control form-control-lg" id="birthdayDate"  v-model="this.promoCodeDTO.expirationDate"/>
                            <label for="birthdayDate" class="form-label">Expiration date</label>
                        </div>

                    </div>
                </div>
                
                <div class="row">
                    <div class="col-5">
                        <input class="btn btn-lg btn-grad" type="submit" value="Submit" @click="this.createPromoCode()"/>
                    </div>
                    <div class="col-md-6 col-7 d-flex justify-content-center" style="padding-top:5px">
                        <p class="message-text" style="font-size:15px;">{{this.message}}</p>
                    </div>
                </div>

            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  
</section>
</template>

<script>
import axios from 'axios'


    export default {
        data() {
            return {
                message: '',
                promoCodeDTO: { code: '', discount: '', numberOfUsages: null, expirationDate: ''}
            }
        },
        methods: {
            createPromoCode() {
                axios
                    .post('http://localhost:8081/WebShopREST/rest/promos/create', this.promoCodeDTO)
                    .then(response => {
                        this.message = response.data
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

.card-registration .select-input.form-control[readonly]:not([disabled]) {
font-size: 1rem;
line-height: 2.15;
padding-left: .75em;
padding-right: .75em;
}
.card-registration .select-arrow {
top: 13px;
}

.buzon{
    background-color: #f7b42c;
    background-image: linear-gradient(315deg, #f7b42c 0%, #fc575e 74%);
    color: white;
    border-radius: 5px;
}

.btn-grad {
            background-image: linear-gradient(to right, #FF512F 0%, #DD2476  51%, #FF512F  100%);
            margin: 10px;
            margin-left: 0;
            padding: 10px 70px;
            text-align: center;
            text-transform: uppercase;
            transition: 0.5s;
            background-size: 200% auto;
            color: white;            
            box-shadow: 0 0 20px #eee;
            border-radius: 10px;
            display: block;
          }

.btn-grad:hover {
            background-position: right center; /* change the direction of the change here */
            color: #fff;
            text-decoration: none;
          }
</style>
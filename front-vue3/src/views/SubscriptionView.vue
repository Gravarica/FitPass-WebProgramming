<template>
    <div class="active container-fluid gradient-customz vh-100">
        <div class="main-container container">
            <ActiveSubscription
                v-bind="this.subscription"
                v-if="this.subscription.active"
            ></ActiveSubscription>
            <InactiveSubscription v-if="!this.subscription.active"></InactiveSubscription>
        </div>
        
    </div>
</template>

<script>
import axios from 'axios'
import ActiveSubscription from './../components/ActiveSubscription'
import InactiveSubscription from '../components/InactiveSubscription.vue'

    export default {
        components: {
    ActiveSubscription,
    InactiveSubscription
},
        data(){
            return{
                subscription: {code: '', type: '', payDay: '', expirationDate: '', username: '', active: false, totalAppearances: null, doneTrainings: null, dailyAppearance: null}
            }
        },
        mounted(){
            axios
                .get('http://localhost:8081/WebShopREST/rest/subscriptions/get/user')
                .then((response) => {
                    this.subscription = response.data
                })
        }
    }

</script>

<style>
.gradient-customz {
/* fallback for old browsers */

/* Chrome 10-25, Safari 5.1-6 */
background: -webkit-linear-gradient(to bottom right, rgba(240, 147, 251, 1), rgba(245, 87, 108, 1));
background-color: #f7b42c;
background-image: linear-gradient(315deg, #f7b42c 0%, #fc575e 74%);
/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
padding-top: 15%;
padding-bottom: 15%;
}

</style>
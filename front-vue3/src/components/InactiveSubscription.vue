<template>
    <div class="main-container container">
        <div class="inner-container container">
            <div class="header">
                <h1>No Subscription</h1>
            </div>
            <div class="content">
                <p>You currently don't have an active subscription</p>
                <p>Please subscribe so you can fully access our features!</p>
            </div>
            <div class="footer-button">
                <button class="btn-grad" @click="this.goToSubscribe()">Subscribe</button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        methods: {
            goToSubscribe() {
                this.$router.push({ path : '/subscribe'})
            }
        }
    }
</script>

<style>
    .content{
        font-size: 25px;
        margin-top: 50px;
        text-align: center;
        margin-bottom: 50px;
        color: #6d6d64
    }
</style>
<template>
    <div class="container main-container">
        <div class="inner-container container">
            <div class="header">
                <h1>Subscription</h1>
            </div>
            <div class="code">
                <h5>{{code}}</h5>
            </div>
            <div class="container info">
                <div class="row row-info">
                    <div class="col-lg-6 label">Start Date: </div>
                    <div class="col-lg-6 value">{{this.formatDate(payDay)}}</div>
                </div>
                <div class="row row-info">
                    <div class="col-lg-6 label">Expiration Date: </div>
                    <div class="col-lg-6 value">{{this.formatDate(expirationDate)}}</div>
                </div>
                <div class="row row-info">
                    <div class="col-lg-6 label">Type: </div>
                    <div class="col-lg-6 value">{{type}}</div>
                </div>
                <div class="row row-info">
                    <div class="col-lg-6 label">Showings per day: </div>
                    <div class="col-lg-6 value">{{dailyAppearance}}</div>
                </div>
                <div class="row row-info">
                    <div class="col-lg-6 label">Trainings remaining: </div>
                    <div class="col-lg-6 value">{{this.getRemainingTrainings(totalAppearances, doneTrainings)}}</div>
                </div>
            </div>
            <div class="footer-button">
                <button class="btn-grad" @click="this.goToSubscribe()">Renew Subscription</button>
            </div>
        </div>
    </div>
</template>

<script>

    export default {
        props: {
            payDay: {
                type: String,
                required: true
            },
            expirationDate: {
                type: String,
                required: true
            },
            type: {
                type: String,
                required: true
            },
            dailyAppearance: {
                type: Number,
                required: true
            },
            totalAppearances: {
                type: Number,
                required: true
            },
            doneTrainings: {
                type: Number,
                required: true
            },
            code: {
                type: String,
                required: true
            }
        },
        methods: {
            formatDate(date){
                let dateArray = date.split('-')
                return dateArray[2] + '.' + dateArray[1] + '.' + dateArray[0] + '.'
            },
            getRemainingTrainings(totalAppearances, doneTrainings){
                return totalAppearances - doneTrainings;
            },
            goToSubscribe() {
                this.$router.push({ path : '/subscribe'})
            }
        }
    }

</script>

<style>
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
.inner-container{
    background-color: white;
    padding: 30px;
    border-radius: 20px;
}
.header{
    text-align: center;
    padding-bottom: 5px;
}

.code{
    text-align: center;
    padding-bottom: 40px;
}

.label{
    text-align: center;
    font-size: 20px;
    padding-bottom: 15px;
}

.value{
    text-align: center;
    font-size: 20px;
    padding-bottom: 15px;
    color: #a4a4e8
}
.info{
    margin-bottom: 30px;
}

.footer-button{
    display: flex;
    justify-content: center;
}
</style>
<template>
    <div class = "card">
        <div class="card-img__wrapper">
            <img class="card-img" :src="getImgUrl(name)" :alt="title" />
        </div>
        <span class="card-name">{{city}}</span>
        <span class="card-title">{{name}}</span>
        <div class="card-body">
            <slot name="body"></slot>
            <button class="btn-grad"
                    style="display: block; margin-top : 16px"
                    @click="this.pushz()">Details</button>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        name: {
            type: String,
            required: true,
        },
        city: {
            type: String,
            required: true,
        },
        id: {
            type: Number,
            required: true
        }
    },
    methods:{
        getImgUrl(object){
            let images = require.context('../assets/', false, /\.png$/);
            return images('./' + object + ".png")
        },
        pushz(){
          this.$router.push({
            name: 'Details',
            params: {
              objectId : this.$props.id
            }
          })
        }
    }
}
</script>

<style lang="scss" scoped>

$border-radius-size: 14px;

.card {
  width: 300px;
  display: inline-block;
  margin: 0px;
  border-radius: $border-radius-size + 5;
  position: relative;
  text-align: center;
  box-shadow: -1px 15px 30px -12px rgba(50, 50, 50, 0.2);
}

.card-img__wrapper {
  position: relative;
  height: 230px;
  border-radius: 7%;
  border-bottom: 1px solid grey;
  border-top-left-radius: $border-radius-size;
  border-top-right-radius: $border-radius-size;
}

.card-img {
  width: 300px;
  height: 227px;
  border-radius: 7%;
}

.card-name {
  display: block;
  margin-top: 24px;
  margin-bottom: 6px;
  text-transform: uppercase;
  font-size: 12px;
  font-weight: 700;
}

.card-title {
  display: block;
  margin-bottom: 8px;
  font-size: 26px;
  color: black;
  font-weight: 900;
}

.card-body {
  padding: 20px;
  margin-bottom: 16px;
}

.btn-grad {
            background-image: linear-gradient(to right, #FF512F 0%, #DD2476  51%, #FF512F  100%);
            margin-left: 24px;
            margin-bottom: 0px;
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
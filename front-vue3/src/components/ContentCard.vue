<template>
    <div class = "card">
        <div class="card-img__wrapper">
            <img class="card-img" :src="getImgUrl(contentType)" :alt="title" @click="imagePressed"/>
        </div>
        <span class="card-name">{{getContentType(contentType)}}</span>
        <span class="card-title">{{name}}</span>
        <div class="card-body">
            <slot name="body"></slot>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            contentType: {
                type: String,
                required: true
            },
            name : {
                type: String,
                required: true
            }
        },
        methods: {
            getImgUrl(object){
            let images = require.context('../assets/', false, /\.png$/);
            return images('./' + object + ".png")
            },
            getContentType(cnt){
                if(cnt == "PERSONAL_TRAINING"){
                    return 'PERSONAL TRAINING'
                } else if (cnt == 'GROUP_TRAINING'){
                    return 'GROUP TRAINING'
                } else if (cnt == 'SAUNA'){
                    return 'SAUNA'
                } else if (cnt =='PILATES'){
                    return 'PILATES'
                }

                return 'YOGA'
            },
            imagePressed(){
                this.$emit('imagePressed')
            }  
        }
    }
</script>


<style scoped>
    
.card {
  width: 450px;
  display: inline-block;
  margin: 0px;
  position: relative;
  text-align: center;
  box-shadow: -1px 15px 30px -12px rgba(50, 50, 50, 0.2);
}

.card-img__wrapper {
  position: relative;
  height: 230px;
  border-bottom: 1px solid grey;
}

.card-img {
    height: 230px;
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
  margin-bottom: 20px;
}
</style>
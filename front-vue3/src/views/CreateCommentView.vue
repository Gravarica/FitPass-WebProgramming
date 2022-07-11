<template>
    <section class="vh-100 gradient-custom">
  <div class="container py-5 h-100">
    <div class="row justify-content-center align-items-center h-100">
      <div class="col-12 col-lg-9 col-xl-7">
        <div class="card shadow-2-strong card-registration" style="border-radius: 20px;">
          <div class="card-body p-4 p-md-5">
            <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Rate Sport Object</h3>
            <form onsubmit="return false">

                <div class="row speed">
                    <div class="col-md-12 mb-4">

                        <div class="form-outline ">
                            <select class="form-select selectz" aria-label="Default select example" v-model="this.comment.objectId">
                                <option v-for="obj in this.sportObjectsVisited" :value="obj.id">{{obj.name}}</option>
                            </select>
                        </div>

                    </div>
                </div>
                
                <div class="row speed">
                    <div class="col-md-12 mb-4">

                        <div class="form-outline">
                            <label class="form-label kobaja">Choose rating</label>
                            <star-rating v-model:rating="this.comment.grade"/>
                        </div>

                    </div>
                </div>

                <div class="row speed">
                    <div class="col-md-12 mb-4">

                        <div class="form-outline">
                            <label class="form-label kobaja" for="lastName">Text</label>
                            <textarea id="lastName" class="form-control form-control-lg" rows="3" v-model="this.comment.text"></textarea>           
                        </div>

                    </div>
                </div>
                
                <div class="row speed">
                    <div class="col-5">
                        <input class="btn btn-lg btn-grad" type="submit" value="Submit" @click="submitComment()"/>
                    </div>
                    <div class="col-md-6 col-7 d-flex justify-content-center" style="padding-top:5px">
                        <p class="message-text" style="font-size:15px;"></p>
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
    import axios from "axios";
import StarRating from "vue-star-rating";
    
    export default {
        components: {
            StarRating
        },
        data(){
            return{
                comment: { objectId: null, text: '', grade: null},
                sportObjectsVisited: null
            }
        },
        mounted(){
            axios
                .get('http://localhost:8081/WebShopREST/rest/training_histories/objects')
                .then(response => {
                    this.sportObjectsVisited = response.data
                })
        },
        methods: {
            submitComment(){
                axios
                    .post('http://localhost:8081/WebShopREST/rest/comments/create', this.comment)
                    .then(response => {
                        this.$notify({
                            title: 'Comment successfully submitted',
                            text: 'Your comment has been sent to validation, if there is no restricted text, it will be shown soon'
                        })
                        this.$router.push({ name: 'Home'})
                    })
            }
        }
    }
</script>


<style>
    .selectz{
        width:100%;
        margin-left: 0px;
    }
    .speed{
        margin-bottom: 10px;
    }
    .kobaja{
        padding-bottom: 10px;
    }
</style>
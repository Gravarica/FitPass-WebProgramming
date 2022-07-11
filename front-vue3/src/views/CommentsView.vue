<template>
    <div class="container-fluid vh-100 background outer-segment">
        <div class="header-top d-flex justify-content-center">
            <h1>Submitted comments</h1>
        </div>
        <div class="container middle-segment">
            <div class="container inner-segment">
                <div class="container-form">
                    <form onsubmit="return false">
                        <div class="row">
                            <div class="col-4">
                                <input type="text" class="putin" placeholder="Pretraga..."/>
                            </div>
                            <div class="col-4">
                                <select class="form-select selectz" aria-label="Default select example">
                                    <option>Sheesh</option>
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="row">
				<div class="col-md-12">
					<div class="table-wrap tablica">
						<table class="table table-hover cabela">
						  <thead class="thead-primary table-primary">
						    <tr>
						      <th>#</th>
						      <th>Username</th>
						      <th>Time</th>
                              <th>Object</th>
						      <th>Grade</th>
							  <th>Text</th>
							  <th></th>
                              <th></th>
						    </tr>
						  </thead>
						  <tbody>
						    <tr v-for="(comment,index) in this.comments" style="font-size:15px">
						      <th scope="row">{{index + 1}}</th>
						      <td>{{comment.customer.username}}</td>
						      <td>{{comment.time}}</td>
						      <td>{{comment.object.name}}</td>
							  <td>{{comment.grade}}</td>
							  <td>{{comment.text}}</td>
                              <td><button class="btn btn-success" @click="approve(comment, index)">APPROVE</button></td>
                              <td><button class="btn btn-danger" @click="decline(comment, index)">DECLINE</button></td>
						    </tr>
						  </tbody>
						</table>
					</div>
				</div>
			</div>
            </div>
        </div>
    </div>
</template>


<script>
import axios from 'axios'

    export default {
        data() {
            return{
                comments: null
            }
        },
        mounted() {
            axios
                .get('http://localhost:8081/WebShopREST/rest/comments/getByState/PENDING')
                .then(response => {
                    this.comments = response.data
                })
        },
        methods: {
            approve(comment, iter){
                axios
                    .put('http://localhost:8081/WebShopREST/rest/comments/approve/' + comment.id)
                    .then(response => {
                        this.comments.splice(iter, 1)
                    })
            },
            decline(comment, iter){
                axios
                    .put('http://localhost:8081/WebShopREST/rest/comments/decline/' + comment.id)
                    .then(response => {
                        this.comments.splice(iter, 1)
                    })
            }
        }
    }
</script>


<style>
    .middle-segment{
        background-color: white;
        border-radius: 20px;
        padding-top: 40px;
    }
    .outer-segment{
        padding-top:10%;
    }
    .container-form{
        padding-left: 20px;
    }
    .header-top{
        margin-bottom: 50px;
    }
</style>
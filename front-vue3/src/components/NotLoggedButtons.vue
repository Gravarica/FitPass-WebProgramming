<template>
    <form form class="d-flex ms-auto order-5">
        <li><router-link :to="{name : ''}">
                <button @click="showLogin()" class="log">Login</button>
                <!-- <transition name="fade" appear>
                    <div class="modal-overlay" @click="showModal = false" v-if="showModal"></div>
                </transition> -->
            </router-link>
        </li>
        <li><router-link  :to="{name : 'Register'}">
            <button class="reg">Register</button>
        </router-link></li>
    </form>
</template>


<script>
export default{
    name : 'notlogged',
    methods: {
        showLogin(){
            this.$emit('show-login')
        },
    
        showRegister(){
            this.$emit('show-reg')
        }
    }

}
</script>


<style lang="scss" scoped>
    button {
            cursor: pointer;
            border: 0;
            border-radius: 4px;
            font-weight: 520;
            margin: 0 0px;
            padding: 7px 0;
            box-shadow: 0 0 20px rgba(104, 85, 224, 0.2);
            transition: 0.4s;
            width: 100px;
        }

    button:hover {
            color: white;
            box-shadow: 0 0 20px rgba(104, 85, 224, 0.6);
            background-color: rgba(104, 85, 224, 1);
        }

     li{
            text-transform: uppercase;
            padding: 13px;
            margin-left: 16px;
            margin-top: 10px;
        }
</style>
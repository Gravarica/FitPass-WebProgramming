<template>
    <form form class="d-flex ms-auto order-5">
        <li class="p-4">{{username}} - {{role}}</li>
        <li><router-link :to="{ name : 'MyProfile'}">
                <button @click="myprofile()" class="log">MyProfile</button>
                <!-- <transition name="fade" appear>
                    <div class="modal-overlay" @click="showModal = false" v-if="showModal"></div>
                </transition> -->
            </router-link>
        </li>
        <li><router-link  :to="{name : ''}">
            <button @click="logout()" class="reg">Logout</button>
        </router-link></li>
    </form>
</template>

<script>
    export default{
        props: {
            username: String,
            role: String,
        },
        methods:{
            logout(){
                this.$emit('logout')
            },
            myprofile(){
                this.$emit('my-profile')
            }
        }
    }
</script>

<style lang="scss" scoped>
    nav{
        background-color: rgba(0,0,0,0.8);
    z-index: 99;
    width: 100%;
    position: fixed;
    transition: 0.5s ease all;
    color: #fff;
        position:relative;
        display: flex;
        flex-direction: row;
        padding: 7px 0;
        transition: 0.5s ease all;
        margin: 0;

        ul,
        .link{
            font-weight: 520;
            color: white;
            list-style: none;
            text-decoration: none;  
        }

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

        .modal-overlay{
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            z-index: 98;
            background-color: rgba(0,0,0,0.3);

        }

        .reg {
            color: white;
            background-color: rgba(104, 85, 224, 1);
            float: right;
        }

        .log {
            color: white;
            // color: rgb(104, 85, 224);
            // background-color: rgba(255, 255, 255, 1);
            background-color: transparent;
            border: 1px solid rgba(104, 85, 224, 1);
            float: right;
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

        .link{
            font-size: 14px;
            transition: 0.5s ease all;
            padding-bottom: 4px;
            border-bottom: 1px solid transparent;

            &:hover{
                color : #00afea;
                border-color: #00afea;
            }
        }

        .branding{
            display: flex;
            align-items: center;
        
            img{
                width: 50px;
                transition: 0.5s ease all;
            }
        }
    
        .navigation{
            display: flex;
            align-items: center;
            flex: 1;
            justify-content: flex-start; 
        }

        .icon{
            display: flex;
            align-items: center;
            position: absolute;
            top: 0;
            right: 24px;
            height: 100%;

            i{
                cursor: pointer;
                font-size: 24px;
                transition: 0.8s ease all;
            }
        }
    
        .icon-active{
            transform: rotate(180deg);
        }
    
        .dropdown-nav{
            display: flex;
            flex-direction: column;
            position: fixed;
            width: 100%;
            max-width: 250px;
            height: 100%;
            background-color: white;
            top: 0;
            left: 0;

            li{
                margin-left: 0;
                .link{
                    color: black;
                }
            }
        }
    
        .mobile-nav-enter-active,
        .mobile-nav-leave-active{
            transition: 1s ease all;
        }

        .mobile-nav-enter-from,
        .mobile-nav-leave-to{
            transform: translateX(-250px);
        }

        .mobile-nav-enter-to{
            transform: translateX(0);
        }
   }
</style>
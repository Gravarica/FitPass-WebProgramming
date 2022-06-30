<template>
    <header :class="{'scrolled-nav': scrolledNav}">
        <nav>
            <div class="branding">
                <img src="@/assets/logo.png" alt="">
            </div>

        <ul v-show="!mobile" class="navigation">
            <li><router-link class="link" :to="{name : 'Home'}">Home</router-link></li>
            <li><router-link class="link" :to="{name : ''}">Objects</router-link></li>
            <li><router-link class="link" :to="{name : ''}">About</router-link></li>
            <li><router-link class="link" :to="{name : ''}">Contact</router-link></li>
            <form form class="d-flex ms-auto order-5">
                 <li><router-link :to="{name : ''}">
                        <button @click="showLogin()" class="log">Login</button>
                        <!-- <transition name="fade" appear>
                            <div class="modal-overlay" @click="showModal = false" v-if="showModal"></div>
                        </transition> -->
                    </router-link>
                </li>
                <li><router-link  :to="{name : ''}">
                    <button @click="showRegister()" class="reg">Register</button>
                </router-link></li>
            </form>
        </ul>

        <div class="icon">
            <i @click="toggleMobileNav" v-show="mobile" class="far fa-bars" :class="{'icon-active' : mobileNav}"></i>
        </div>

        <transition name="mobile-nav">
        <ul v-show="mobileNav" class="dropdown-nav">
            <li><router-link class="link" :to="{name : 'Home'}">Home</router-link></li>
            <li><router-link class="link" :to="{name : ''}">Objects</router-link></li>
            <li><router-link class="link" :to="{name : ''}">About</router-link></li>
            <li><router-link class="link" :to="{name : ''}">Contact</router-link></li>
            <li><router-link class="link" :to="{name : ''}">Login</router-link></li>
            <li><router-link class="link" :to="{name : ''}">Register</router-link></li>
        </ul>

        </transition>

        </nav>
    </header>
</template>

<script>
export default {
    name: "navigation",
    data(){
        return{
            scrolledNav : null,
            mobile : null,
            mobileNav : null,
            windowWidth : null,
            showModal : null
        };
    },

    created(){
        window.addEventListener("resize",this.checkScreen);
        this.checkScreen();
    },

    mounted(){
        window.addEventListener("scroll", this.updateScroll);
    },

    methods: {
        toggleMobileNav(){
            this.mobileNav = !this.mobileNav
        },

        checkScreen(){
            this.windowWidth = window.innerWidth;
            if(this.windowWidth <= 750){
                this.mobile = true;
                return;
            }
        
            this.mobile = false;
            this.mobileNav = false;
        },

        updateScroll(){
            const scrollPosition = window.scrollY;
            if(scrollPosition > 50){
                this.scrolledNav = true;
            }
            this.scrolledNav = false;
        },

        showLogin(){
            this.$emit('show-login')
        },
    
        showRegister(){
            this.$emit('show-reg')
        }
    }
};
</script>

<style lang="scss" scoped>

header{
    background-color: rgba(0,0,0,0.8);
    z-index: 99;
    width: 100%;
    position: fixed;
    transition: 0.5s ease all;
    color: #fff;

    nav{
        position:relative;
        display: flex;
        flex-direction: row;
        padding: 7px 0;
        transition: 0.5s ease all;
        width: 90%;
        margin: 0 auto;
        @media(min-width: 1140px){
            max-width: 1140px;
        }

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
}
 
.scrolled-nav{
            background-color: black;
            box-shadow: 0 4px 6px -1px rgba(0,0,0,0.1), 0 2px 4px -1px rgba(0, 0, 0,0.06);

            nav{
                padding: 8px 0;

                .branding{
                    img{
                        width: 40px;
                        box-shadow: 0 4px 6px -1px rgba(0,0,0,0.1), 0 2px 4px -1px rgba(0, 0, 0,0.06);
                    }
                }

            }
        }


.fade-enter-active,
.fade-leave-active{
    transition: opacity 0.5s;
}

.fade-enter,
.fade-leave-to{
    opacity: 0;
}

</style>
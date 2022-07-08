<template>

    <form form class="d-flex ms-left">
        <li><router-link class="link" :to="{name : 'Home'}">Home</router-link></li>
        <li><router-link class="link" :to="{name : 'Objects'}">Objects</router-link></li>
        <li><router-link class="link" :to="{name : ''}">About</router-link></li>
        <li><router-link class="link" :to="{name : ''}">Contact</router-link></li>
         <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Add new</a>
           <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDropdown">
            <router-link class="link dropdown-item" :to="{name : 'RegisterTrainer'}">Trainer</router-link>
            <router-link class="link dropdown-item" :to="{name : ''}">Manager</router-link>
            <hr class="dropdown-divider">
            <router-link class="link dropdown-item" :to="{name : 'AddSportObject'}">Sport Object</router-link>
        </ul>
        </li>
    </form>       

</template>

<script>
    export default{

    }
</script>

<style lang="scss" scoped>

li{
            text-transform: uppercase;
            padding: 13px;
            margin-left: 16px;
            margin-top: 10px;
    }
</style>
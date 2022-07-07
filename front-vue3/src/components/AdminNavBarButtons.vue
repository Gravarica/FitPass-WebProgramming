<template>

    <form form class="d-flex ms-left">
        <li><router-link class="link" :to="{name : 'Home'}">Home</router-link></li>
        <li><router-link class="link" :to="{name : 'Objects'}">Objects</router-link></li>
        <li><router-link class="link" :to="{name : ''}">About</router-link></li>
        <li><router-link class="link" :to="{name : ''}">Contact</router-link></li>
         <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">REGISTER</a>
           <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><router-link class="link" :to="{name : 'RegisterTrainer'}">Trainer</router-link></li>
            <li><router-link class="link" :to="{name : ''}">Manager</router-link></li>
        </ul>
        </li>
    </form>       

</template>

<script>

</script>

<style lang="scss" scoped>

li{
            text-transform: uppercase;
            padding: 13px;
            margin-left: 16px;
            margin-top: 10px;
    }
</style>
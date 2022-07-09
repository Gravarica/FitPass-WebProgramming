<template>

    <form form class="d-flex ms-left my-2" >
        <li><router-link class="link" :to="{name : 'Home'}" exact>Home</router-link></li>
        <li><router-link class="link" :to="{name : 'Objects'}" exact>Objects</router-link></li>
        <li><router-link class="link" :to="{name : 'Users'}" exact>Users</router-link></li>
        <li><router-link class="link" :to="{name : 'EditProfile'}">Contact</router-link></li>
        <li class="nav-item dropdown disko">
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

.disko{
    padding-top: 10px;
}

li{
            text-transform: uppercase;
            padding: 13px;
            margin-left: 16px;
            margin-top: 10px;
            text-decoration: none;
    }

 nav li:hover,
 nav li.router-link-active,
 nav li.router-link-exact-active {
   color: indianred;
   cursor: pointer;
   border: 0;
   text-decoration: none;
 }
</style>
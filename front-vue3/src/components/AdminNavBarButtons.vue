<template>

    <form form class="d-flex ms-left">
        <li><router-link class="link enrico" :to="{name : 'Home'}">Home</router-link></li>
        <li><router-link class="link enrico" :to="{name : 'Objects'}">Objects</router-link></li>
        <li><router-link class="link enrico" :to="{name : ''}">About</router-link></li>
        <li><router-link class="link enrico" :to="{name : ''}">Contact</router-link></li>
        <!-- <div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
            <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Dropdown
            </a>
            <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li><a class="dropdown-item" href="#">Something else here</a></li>
            </ul>
            </li>
        </div> -->
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle enrico" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Add new
        </a>
           <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDropdown">
                <router-link class="link enrico dropdown-item" :to="{name : 'RegisterTrainer'}">Trainer</router-link>
                <router-link class="link  enrico dropdown-item" :to="{name : 'RegisterManager'}">Manager</router-link>
                <hr class="dropdown-divider">
                <router-link class="link enrico dropdown-item" :to="{name : 'AddSportObject'}">Sport Object</router-link>
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

.enrico {
  text-decoration: none;
  color: inherit;
}
</style>
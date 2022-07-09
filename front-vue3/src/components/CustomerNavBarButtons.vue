<template>

    <form form class="d-flex ms-left">
        <li><router-link class="link enrico" :to="{name : 'Home'}">Home</router-link></li>
        <li><router-link class="link enrico" :to="{name : 'Objects'}">Objects</router-link></li>
        <li><router-link class="link enrico" :to="{name : ''}">About</router-link></li>
        <li><router-link class="link enrico" :to="{name : ''}">Contact</router-link></li>
        <li><router-link class="link enrico" :to="{name : 'CustomerTrainings'}">My Trainings</router-link></li>
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
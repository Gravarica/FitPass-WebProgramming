<template>
<div class = "row">
  <div class = "bg-primary text-white rounded something">
    <p class = "display-3">Trenirajte</p>
    <p class = "text">u najboljim teretanama, fitnes studijima</p>
    <p class = "text">i sportskim centrima širom Srbije, sa samo</p>
    <p class = "text">jednom članarinom!</p>
    <p class = "display-6" style = "color:orangered">Već od 2.900 RSD.</p>
    <button>Registruj se</button>
  </div>
  </div>
<div class = "mini row">
  <nav>
          <ul class="nav justify-content-center">
            <li class="nav-item">
              <a class="link active" href="#">Beograd</a>
            </li>
            <li>
              <a class="link" href="#">Novi Sad</a>
            </li>
            <li class="nav-item">
              <a class="link" href="#">Nis</a>
            </li>
            <li class="nav-item">
              <a class="link" href="#">Subotica</a>
            </li>
            <li class="nav-item">
              <a class="link" href="#">Kragujevac</a>
            </li>
            <li class="nav-item">
              <a class="link" href="#">Cacak</a>
            </li>
          </ul>
        </nav>
</div>
<div class = "wrapper-content">
  <div class="containerz">
    <div class="view-sm isCenter">
        <SportObjects></SportObjects>
    </div>

  </div>
</div>
        
</template>

<script>
// @ is an alias to /src
import SportObjects from './SportObjects.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld,
    SportObjects
}
}
</script>

<style lang="scss" scoped>

  .view-sm {
  max-width: 100%;
  &.isCenter {
    margin-left: auto;
    margin-right: auto;
  }
  }

  .wrapper {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  margin: 0 auto;
}

.wrapper-content {
  display: flex;
  flex-direction: column;
  min-height: 70vh;
  margin-top: 80px; // if header is fixed do mt: headerHeight
}

.containerz {
  position: relative;
  flex: 0 0 auto;
  margin: 0 auto;
  width: 100%;
}

  .something{
    background-image: url("./../assets/gymguy1.jpg");
    background-size: cover;
    padding-left: 10cm;
    padding-top: 50px;
    padding-bottom: 50px;
  }

  .mini{
        border-bottom: 1px solid grey;
  }

  .text {
    font-size : 20px
  }

  button {
            cursor: pointer;
            border: 0;
            border-radius: 4px;
            font-weight: bold;
            margin: 0 0px;
            padding: 7px 0;
            box-shadow: 0 0 20px rgba(104, 85, 224, 0.2);
            transition: 0.4s;
            width: 300px;
            height: 50px;
            font-size: 20;
            color: white;
            font-family: Verdana, Geneva, Tahoma, sans-serif;
            background-color: orangered;
        }

  button:hover {
            box-shadow: 
    inset -3.5em 0 0 0 var(--hover),
    inset 3.5em 0 0 0 var(--hover); 
            color: orangered;
            background-color: white;
        }

  nav{
    background-color: white;
    z-index: 99;
    width: 100%;
    position: fixed;
    transition: 0.5s ease all;
    color: orangered;
        position:relative;
        display: flex;
        flex-direction: column;
        padding: 7px 0;
        transition: 0.5s ease all;
        margin: 0;

        ul,
        .link{
            font-weight: 520;
            color: grey;
            list-style: none;
            text-decoration: none;  
        }

        .link:hover{
          color: orangered;
        }

        li{
            text-transform: uppercase;
            padding: 5px;
            margin-top: 10px;
            margin-bottom: 10px;
            margin-left: 25px;     
            color: orangered;
        }
  }

</style>

<template>
      <img src = "./../assets/gymguy1.jpg" alt = "Dzimara" heigth = "300"/>
        <nav class = "navbar-default">
          <ul class="nav justify-content-center">
            <li class="nav-item">
              <a class="nav-link active" href="#">Beograd</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Novi Sad</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Nis</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Subotica</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Kragujevac</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Cacak</a>
            </li>
          </ul>
        </nav>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>

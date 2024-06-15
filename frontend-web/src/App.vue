<template>
  <NavBar></NavBar>
  <router-view/>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
import {defineComponent} from "vue";

export default defineComponent({
  components: {NavBar}
})

</script>

<style>
body {
  background-image: url("@/assets/img/somu.jpg");
  background-color: rgba(255, 255, 255, 0.3);
  background-size: cover;
}
</style>

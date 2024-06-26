<template>
  <NavBar></NavBar>
  <router-view/>
</template>

<script>
import NavBar from "@/components/NavBar.vue";
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
import {defineComponent} from "vue";
import $ from "jquery";


export default defineComponent({
  components: {NavBar},

  // 测试后端接口
  setup() {
    // 测试 LoginController
    $.ajax({
      url: "http://localhost:3000/user/account/token/",

      type: "post",

      data: {
        username: "perry",
        password: "0324",
      },

      success(resp) {
        console.log(resp);
      },

      error(resp) {
        console.log(resp);
      }
    });


    $.ajax({
      url: "http://localhost:3000/user/account/info/",
      type: "get",
      headers: {
        Authorization: "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ZTY5NzViZDM5ZjU0YmY3YWIxMmU5MGE2ZWIyMGMyNCIsInN1YiI6IjEiLCJpc3MiOiJzZyIsImlhdCI6MTcxOTQwOTk4NSwiZXhwIjoxNzIwNjE5NTg1fQ.YGE2-WA0aTCJEgqiPmXu39-L-tdt5CIeZCYpFeiKtZI"
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      },
    });
  }

})

</script>

<style>
body {
  background-image: url("@/assets/img/somu.jpg");
  background-color: rgba(255, 255, 255, 0.3);
  background-size: cover;
}
</style>

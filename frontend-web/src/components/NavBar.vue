<script setup>
// import { useRoute } from "vue-router";
// import { computed } from "vue";
//
// const route = useRoute();
// let routeName = computed(() => route.name)

</script>

<template>
  <nav class="navbar bg-dark navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
    <div class="container">
      <router-link class="navbar-brand" :to="{name: 'home'}">King of Bots</router-link>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
<!--            <router-link :class="routeName === 'pk_index' ? 'nav-link active' : 'nav-link'" :to="{name: 'pk_index'}">对战</router-link>-->
          </li>
          <li class="nav-item">
<!--            <router-link :class="routeName === 'record_index' ? 'nav-link active' : 'nav-link'" :to="{name: 'record_index'}">战绩</router-link>-->
          </li>
          <li class="nav-item">
<!--            <router-link :class="routeName === 'ranklist_index' ? 'nav-link active' : 'nav-link'" :to="{name: 'ranklist_index'}">排行榜</router-link>-->
          </li>
        </ul>

        <ul class="navbar-nav">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              ypy
            </a>
            <ul class="dropdown-menu">
<!--              <router-link class="dropdown-item" :to="{name: 'user_bot_index'}">我的 Bot</router-link>-->
              <li>
                <hr class="dropdown-divider">
              </li>

              <li><a class="dropdown-item" href="#">登入</a></li>

              <li><a class="dropdown-item" href="#">退出</a></li>
            </ul>
          </li>

          <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </ul>


      </div>
    </div>
  </nav>
</template>

<style scoped>

</style>
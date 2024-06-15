# Step 1: Start
- 使用 powershell 命令 `vue ui` 打开 vue3 图形化界面, 创建***前端项目***`front-web` (网页端)
- 安装如下组件:
  - 插件 -> `vue-router` & `vuex`
  - 依赖 -> `jQuery` & `Bootstrap` & `popperjs/core`
# Step 2: 配置 `App.vue`
- `App.vue` 是整个前端项目的根组件 (root component), 它扮演着组合和管理其他组件的角色, 不是单一的视图
- 搞一个导航栏, 这玩意任何views都需要
  - 在 `src/components` 目录中创建 `NavBar.vue`; `components` 目录通常用于存放可复用的小组件或者功能性组件, 例如: 
    - Button: 各种按钮组件，例如主按钮、次要按钮等。
    - Input: 输入框组件，例如文本输入框、密码输入框等。
    - Card: 卡片组件，用于展示某个实体的信息。
    - Modal: 弹窗组件，用于显示提示、确认框等信息。
    - Navbar: 导航栏组件，用于整个应用的顶部导航。
  - `NavBar` 模板:
    ```html
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
    ```
- 再把 `NavBar.vue` 组件加入到 `App.vue` 中, 并删去 vue3 的演示内容:
  ```html
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
  
  </style>
  ```
- 修改一下路由 `src/router/index.js`:
  ```javascript
  import { createRouter, createWebHashHistory } from 'vue-router'
  
  
  const routes = [
    {
      path: "/",
      name: "home",
    },
  ]
  
  const router = createRouter({
    history: createWebHashHistory(),
    routes
  })
  
  export default router
  ```
- 最后选一张美美的图片放入 `src/assets/img/` 中作为背景, (同时在 `assets/` 创建目录 `scripts/`, 日后要用)
  - 在 `App.vue` 的 `<style>` 标签中写入:
    ```html
    body {
    background-image: url("@/assets/img/somu.jpg");
    background-color: rgba(255, 255, 255, 0.3);
    background-size: cover;
    }
    ```
# Step 3: 配置其它 views; 实现导航栏功能
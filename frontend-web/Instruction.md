# 1. Start
使用 powershell 命令 `vue ui` 打开 vue3 图形化界面, 创建***前端项目***`front-web` (网页端)

安装如下组件:
- 插件 -> `vue-router` & `vuex`
- 依赖 -> `jQuery` & `Bootstrap` & `popperjs/core`

# 2. 配置 `App.vue`

`App.vue` 是整个前端项目的根组件 (root component), 它扮演着组合和管理其他组件的角色, 不是单一的视图

搞一个导航栏, 这玩意任何views都需要
- 在 `src/components` 目录中创建 `NavBar.vue`; `components` 目录通常用于存放可复用的小组件或者功能性组件, 例如: 
  - Button: 各种按钮组件，例如主按钮、次要按钮等。
  - Input: 输入框组件，例如文本输入框、密码输入框等。
  - Card: 卡片组件，用于展示某个实体的信息。
  - Modal: 弹窗组件，用于显示提示、确认框等信息。
  - Navbar: 导航栏组件，用于整个应用的顶部导航。
 - `NavBar.vue` 模板:
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
<!--            <router-link :class="routeName === 'pk' ? 'nav-link active' : 'nav-link'" :to="{name: 'pk'}">PK</router-link>-->
          </li>
          <li class="nav-item">
<!--            <router-link :class="routeName === 'record' ? 'nav-link active' : 'nav-link'" :to="{name: 'record'}">Record</router-link>-->
          </li>
          <li class="nav-item">
<!--            <router-link :class="routeName === 'ranking' ? 'nav-link active' : 'nav-link'" :to="{name: 'ranking'}">Ranking</router-link>-->
          </li>
        </ul>

        <ul class="navbar-nav">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              ypy
            </a>
            <ul class="dropdown-menu">
<!--              <router-link class="dropdown-item" :to="{name: 'user_bot'}">My Bots</router-link>-->
              <li>
                <hr class="dropdown-divider">
              </li>

              <li><a class="dropdown-item" href="#">Log in</a></li>

              <li><a class="dropdown-item" href="#">Log out</a></li>
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
最后选一张美美的图片放入 `src/assets/img/` 中作为背景, (同时在 `assets/` 创建目录 `scripts/`, 日后要用)
- 在 `App.vue` 的 `<style>` 标签中写入:
```html
body {
background-image: url("@/assets/img/somu.jpg");
background-color: rgba(255, 255, 255, 0.3);
background-size: cover;
}
  ```
# 3. 配置其它 views; 实现导航栏功能
在 `views/` 目录下创建目录和vue组件:
  - `pk/`: 对战页面 -> 创建 `PkIndex.vue` (Index 通常代表主入口)
  - `ranking/`: 排行榜 -> 创建 `RankingIndex.vue`
  - `record/`: 战绩 -> 创建 `RecordIndex.vue`
  - `user/bot/`: 我的 bot -> 创建 `UserBotIndex.vue`
  - `error/`: 错误页面 -> 创建 `ErrorIndex.vue`

编写以上views:
- 先写一个 `ContentField.vue` component (相当于背景板):
```html
<script setup>
</script>

<template>
  <div class="container content-field">
    <div class="card">
      <div class="card-body">
        <slot></slot>
      </div>
    </div>
  </div>
</template>

<style scoped>
div.container.content-field {
  margin-top: 30px;
}

.card {
  background-color: rgba(255, 255, 255, 0.63); /* 使用 rgba 设置颜色和透明度 */
}
</style>
```
- 再在各个 `views` 引入 `ContentField.vue`:
```javascript
<script setup>
import ContentField from "@/components/ContentField.vue";
</script>

<template>
  <ContentField>
    <h2>404 not found. The url you input doesn't exist.</h2>
  </ContentField>
</template>

<style scoped>

</style>
```

编写 `router/index.js`, 进而访问刚刚写的各个views
```javascript
import { createRouter, createWebHistory } from 'vue-router'

import ErrorIndex from "@/views/error/ErrorIndex.vue";
import PkIndex from "@/views/pk/PkIndex.vue";
import RankingIndex from "@/views/ranking/RankingIndex.vue";
import RecordIndex from "@/views/record/RecordIndex.vue";
import UserBotIndex from "@/views/user/bot/UserBotIndex.vue";

const routes = [
  {
    path: "/",
    name: "home",
  },

  {
    path: "/error/",
    name: "error",
    component: ErrorIndex,
  },

  {
    path: "/:catchAll(.*)",
    redirect: "error",
  },

  {
    path: "/pk/",
    name: "pk",
    component: PkIndex,
  },

  {
    path: "/ranking/",
    name: "ranking",
    component: RankingIndex,
  },

  {
    path: "/record/",
    name: "record",
    component: RecordIndex,
  },

  {
    path: "/user/bot/",
    name: "user_bot",
    component: UserBotIndex,
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
```
- 注意把 `createWebHashHistory` 改成 `createWebHistory`, 不然访问url时, 尾部会被加上 "#"
- 注意代码中是 `component` 不是 `components`

接下来修改 `NavBar.bue` 中的链接 (取消之前的注释), 使之可以访问以上urls

# 4. PK 页面的游戏编写
## 4.1. 编写游戏界面的 views 部分
需要编写两个 components: 
- `PlayGround.vue`: 这是针对任何游戏都可以复用的 component, 因为在 pk 界面可能还包含记分板等不同的东西. 它需要引用 `GameMap.vue`:
```html
<script setup>
import GameMap from "@/components/GameMap.vue";
</script>

<template>
  <div class="playground">
    <GameMap />
  </div>
</template>

<style scoped>
div.playground {
  width: 60vw;
  height: 70vh;
  margin: 40px auto;
}
</style>
```
- `GameMap.vue`: 这是针对 King of Bots 的地图 component, 它需要调用在 `src/assets/scripts/` 中的 `gameMap.js` 代码:
```html
<script setup>
// import { GameMap } from "@/assets/scripts/GameMap";
import { ref, onMounted } from "vue";

const parent = ref(null);
const canvas = ref(null);

onMounted(() => {
  // new GameMap(canvas.value.getContext('2d'), parent.value);
})
</script>

<template>
  <div ref="parent" class="GameMap">
    <canvas ref="canvas" tabindex="0">

    </canvas>
  </div>
</template>

<style scoped>
div.GameMap {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
```
接下来, 在 `views/pk/PK.vue` 中引入 `PlayGround.vue`:
```html
<script setup>
import ContentField from "@/components/ContentField.vue";
import PlayGround from "@/components/PlayGround.vue";
</script>

<template>
  <ContentField>
    <h2>Pk Page</h2>
    <PlayGround/>
  </ContentField>
</template>

<style scoped>

</style>
```

## 4.2. 编写游戏的 JS 代码
游戏的棋盘是一个 `canvas`, 位于 `<GameMap>` 中, 所以, 当前 pk 页面的结构是: ContentField > PlayGround > GameMap > canvas.

编写 `webGame.js`:
```javascript
const WEB_GAME_OBJS = [];  // 定义游戏对象类和对象管理数组

export class WebGame {
    constructor() {
        WEB_GAME_OBJS.push(this);
        this.timeDelta = 0;  // 用于存储每帧之间的时间间隔
        this.hasCalledStrated = false;  // 表示是否已经调用了 start 方法
    }

    // 定义游戏对象的生命周期方法
    start() {  // 只执行一次
        // 这个方法应该被子类重写以实现具体逻辑
    }

    update() {  // 每一帧执行一次
        // 这个方法应该被子类重写以实现具体逻辑
    }

    // 销毁游戏对象
    beforeDestroy() {  // 在游戏对象被销毁之前调用, 用于执行清理和释放资源的逻辑
        // 这个方法应该被子类重写以实现具体逻辑
    }

    destroy() {
        this.beforeDestroy();

        for (let i in WEB_GAME_OBJS) {
            const obj = WEB_GAME_OBJS[i];
            if (obj === this) {
                WEB_GAME_OBJS.splice(i);  // 从obj list中删除当前 obj
                break;
            }
        }
    }
}

// 主循环和动画帧更新
let lastTimeStamp;

// step(timeStamp): 这是一个递归调用的函数, 用于驱动游戏的主循环. 在每一帧更新时, 遍历 WEB_GAME_OBJS 数组, 依次调用游戏对象的 start() 或 update() 方法
const step = timeStamp => {
    for (let obj of WEB_GAME_OBJS) {
        if (!obj.hasCalledStrated) {
            obj.hasCalledStrated = true;
            obj.start();
        } else {
            obj.timeDelta = timeStamp - lastTimeStamp;
            obj.update();
        }
    }

    lastTimeStamp = timeStamp;
    requestAnimationFrame(step); // 请求下一帧动画
}
requestAnimationFrame(step);  // 开始游戏循环: requestAnimationFrame(step): 使用浏览器提供的 requestAnimationFrame 方法来请求下一帧动画, 确保游戏循环持续进行
```
编写 `gameMap.js`:

```javascript
import {WebGame} from "@/assets/scripts/WebGame";

export class GameMap extends WebGame {
  constructor(ctx, parent) {
    super();
    /*
    在 GameMap.vue 中:
    <script setup>
    import { GameMap } from "@/assets/scripts/GameMap";
    import { ref, onMounted } from "vue";

    const parent = ref(null);
    const canvas = ref(null);

    onMounted(() => {
      new GameMap(canvas.value.getContext('2d'), parent.value);  // onMounted(() => { ... }): Vue 3 的生命周期钩子, 在组件挂载后执行指定的逻辑. 创建 GameMap 实例, 传入 canvas 的 2D 上下文和父元素引用
    })
    </script>

    <template>
      <div ref="parent" class="GameMap">
        <canvas ref="canvas" tabindex="0">

        </canvas>
      </div>
    </template>

    <style scoped>
    div.GameMap {
      width: 100%;
      height: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    </style>

    ctx 是 canvas.getContext('2d') 方法返回的 2D 渲染上下文对象.
    canvas 是 HTML5 <canvas> 元素的 DOM 对象. 用于在网页上绘制图形.
    getContext('2d') 方法返回一个用于在画布上绘制 2D 图形的上下文对象.
    ctx 提供了一系列的绘图方法和属性, 例如 fillRect(), strokeRect(), drawImage() 等, 用于在 <canvas> 上进行绘制操作.

    parent 是一个 DOM 元素的引用, 通常用来表示包含 <canvas> 元素的父级元素. 在 GameMap.vue 中就是 <div ref="parent" class="GameMap">
    在 Vue 组件中，可以使用 ref 来获取到 DOM 元素的引用，从而在 JavaScript 中对其进行操作。
    在这个例子中，parent 用于计算和设置 <canvas> 元素的尺寸，以确保适应其父元素的大小。
    */
    this.ctx = ctx;
    this.parent = parent;
    this.unitLength = 0;  // 整个 canvas 的单位边长

    this.nRows = 15;  // nRows 个单位边长作为 canvas 的长
    this.nCols = 18;  // nCols 个单位边长作为 canvas 的宽
  }

  start() {
    super.start();
  }

  updateSize() {
    // canvas 的长和宽 (以 unitLength 为单位边长)
    this.unitLength = Math.min(this.parent.clientWidth / this.nCols, this.parent.clientHeight / this.nRows);
    this.ctx.canvas.width = this.unitLength * this.nCols;
    this.ctx.canvas.height = this.unitLength * this.nRows;
  }

  update() {
    super.update();
    this.updateSize();
    this.render();
  }

  render() {
    // 渲染
    this.ctx.fillStyle = "green";
    this.ctx.fillRect(0, 0, this.ctx.canvas.width, this.ctx.canvas.height);
  }
}
```

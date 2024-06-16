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

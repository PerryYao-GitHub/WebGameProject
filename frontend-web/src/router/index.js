import {createRouter, createWebHistory} from 'vue-router'

import ErrorIndex from "@/views/error/ErrorIndex.vue";
import PkIndex from "@/views/pk/PkIndex.vue";
import RankingIndex from "@/views/ranking/RankingIndex.vue";
import RecordIndex from "@/views/record/RecordIndex.vue";
import UserBotIndex from "@/views/user/bot/UserBotIndex.vue";
import UserAccountLogin from "@/views/user/account/UserAccountLogin.vue";
import UserAccountRegister from "@/views/user/account/UserAccountRegister.vue";
import store from "@/store";


const routes = [
    {
        path: "/",
        name: "home",
        meta: {
            requestAuth: false,
        },
    },

    {
        path: "/error/",
        name: "error",
        component: ErrorIndex,
        meta: {
            requestAuth: false,
        },
    },

    {
        path: "/:catchAll(.*)",
        redirect: "error",
    },

    {
        path: "/pk/",
        name: "pk",
        component: PkIndex,
        meta: {
            requestAuth: true,
        },
    },

    {
        path: "/ranking/",
        name: "ranking",
        component: RankingIndex,
        meta: {
            requestAuth: true,
        },
    },

    {
        path: "/record/",
        name: "record",
        component: RecordIndex,
        meta: {
            requestAuth: true,
        },
    },

    {
        path: "/user/bot/",
        name: "user_bot",
        component: UserBotIndex,
        meta: {
            requestAuth: true,
        },
    },

    {
        path: "/user/account/login/",
        name: "user_account_login",
        component: UserAccountLogin,
        meta: {
            requestAuth: false,
        },
    },

    {
        path: "/user/account/register/",
        name: "user_account_register",
        component: UserAccountRegister,
        meta: {
            requestAuth: false,
        },
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

// 未登录状态下被重定向到login页面
router.beforeEach((to, from, next) => {
    if (to.meta.requestAuth && !store.state.user.is_login) next({name: "user_account_login"})
    else next();
});

export default router

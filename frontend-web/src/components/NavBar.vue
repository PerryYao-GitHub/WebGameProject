<script>
import { useRoute } from 'vue-router'
import { computed } from 'vue'
import { useStore } from 'vuex';

export default {
    setup() {
        const store = useStore();
        const route = useRoute();
        let routeName = computed(() => route.name)

        const logout = () => {
          store.dispatch("logout");
        }

        return {
            routeName,
            logout
        }
    }
}
</script>


<template>
    <nav class="navbar bg-dark navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
        <div class="container">
            <router-link class="navbar-brand" :to="{name: 'home'}">King of Bots</router-link>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">

                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <router-link :class="routeName === 'pk' ? 'nav-link active' : 'nav-link'" :to="{name: 'pk'}">PK
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :class="routeName === 'record' ? 'nav-link active' : 'nav-link'"
                                     :to="{name: 'record'}">
                            Record
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :class="routeName === 'ranking' ? 'nav-link active' : 'nav-link'"
                                     :to="{name: 'ranking'}">
                            Ranking
                        </router-link>
                    </li>
                </ul>

                <ul class="navbar-nav" v-if="$store.state.user.is_login">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            {{ $store.state.user.username }}
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li>

                                <router-link class="dropdown-item" :to="{name: 'user_bot'}">My Bots</router-link>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#" @click="logout">Logout</a></li>
                        </ul>
                    </li>
                </ul>

                <ul class="navbar-nav" v-else>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{name: 'user_account_login' }" role="button">
                            Login
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" :to="{name: 'user_account_register'}" role="button">
                            Register
                        </router-link>
                    </li>
                </ul>

                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>


            </div>
        </div>
    </nav>
</template>

<style scoped>

</style>
<script>
import ContentField from "@/components/ContentField.vue";
import {useStore} from "vuex";
import {ref} from "vue";
import router from "@/router";

export default {
    components: {
        ContentField
    },
    setup() {
        const store = useStore();
        let username = ref('');
        let password = ref('');
        let errorMessage = ref('');
        // let showContent = ref(false);

        const jwtToken = localStorage.getItem("jwt_token");  // 看看local storage中有没有保存的token
        if (jwtToken) {
            store.commit("updateToken", jwtToken);
            store.dispatch("getInfo", {
                success() {
                    router.push({name: "home"});
                    // store.dispatch("updatePulling", false);
                },
                error() {
                    // showContent.value = true;
                    store.commit("updatePullingInfo", false);
                }
            })
        } else store.commit("updatePullingInfo", false);

        const login = () => {
            errorMessage.value = "";
            store.dispatch("login", {
                username: username.value,
                password: password.value,
                success() {
                    //console.log(resp);
                    store.dispatch("getInfo", {
                        success() {
                            // console.log(store.state.user);
                            router.push({name: 'home'});
                        }
                    })
                },
                error() {
                    errorMessage.value = "username or password is wrong"
                    //console.log(resp);
                }
            })
        }

        return {
            username,
            password,
            error_message: errorMessage,
            login,
        }
    }
}
</script>


<template>
    <ContentField v-if="!$store.state.user.isPullingInfo">
        <h2>User Account Login</h2>
        <div class="row justify-content-md-center">
            <div class="col-3">

                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="username">
                        <div class="form-text">We'll never share your email or phone number with anyone else.</div>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input v-model="password" type="password" class="form-control" id="password"
                               placeholder="password">
                        <div class="form-text">Password must contain 6 characters at least.</div>
                    </div>

                    <div class="error-message">{{ error_message }}</div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>

            </div>
        </div>
    </ContentField>
</template>

<style scoped>
button {
    width: 100%;
}

div.error-message {
    color: red;
}
</style>
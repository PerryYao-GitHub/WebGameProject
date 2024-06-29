<script>
import ContentField from "@/components/ContentField.vue";
import { ref } from "vue";
import router from "@/router";
import $ from 'jquery';

export default {
    components: {
        ContentField
    },

    setup() {
        let username = ref('');
        let password = ref('');
        let confirmedPassword = ref('');
        let errorMessage = ref('');

        const register = () => {
            // 前端验证用户名和密码的基本逻辑
            if (username.value.trim() === '') {
                errorMessage.value = "Username can not be empty";
                return;
            }

            if (username.value.length < 6 || username.value.length > 60) {
                errorMessage.value = "Username must be between 6 and 60 characters";
                return;
            }

            if (password.value.trim() === '' || confirmedPassword.value.trim() === '') {
                errorMessage.value = "Password can not be empty";
                return;
            }

            if (password.value.length > 60) {
                errorMessage.value = "Password must be at most 60 characters";
                return;
            }

            if (password.value !== confirmedPassword.value) {
                errorMessage.value = "Passwords do not match";
                return;
            }

            // 发送注册请求到后端
            $.ajax({
                url: "http://localhost:3000/user/account/register/",
                type: "post",
                data: {
                    username: username.value,
                    password: password.value,
                    confirmedPassword: confirmedPassword.value,
                },
                success(resp) {
                    // console.log(resp);
                    if (resp.msg === "success") router.push({ name: "user_account_login" });
                    else errorMessage.value = resp.msg;
                },
            });
        }

        return {
            username,
            password,
            confirmedPassword,
            errorMessage,
            register,
        }
    }

}
</script>

<template>
    <ContentField>
        <h2>User Account Register</h2>
        <div class="row justify-content-md-center">
            <div class="col-3">
                <form @submit.prevent="register">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input v-model="username" type="text" class="form-control" id="username"
                               placeholder="username, phone number or email address">
                        <div class="form-text">We'll never share your email or phone number with anyone else.</div>
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="password">
                        <div class="form-text">Password must contain 6 characters at least.</div>
                    </div>

                    <div class="mb-3">
                        <label for="confirmedPassword" class="form-label">Confirm Your Password</label>
                        <input v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword" placeholder="password">
                        <div class="form-text">Input your password again.</div>
                    </div>
                    <div class="error-message">{{ errorMessage }}</div>

                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="exampleCheck1">
                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                    </div>
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
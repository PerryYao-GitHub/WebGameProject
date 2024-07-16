<script>
// import $ from 'jquery';
// import {useStore} from "vuex";
import ContentField from "@/components/ContentField.vue";

export default {
    components: {
        ContentField,
    },

    setup: () => {
        // const store = useStore();
        // $.ajax({
        //     url: "http://localhost:3000/user/bot/add/",
        //     type: "post",
        //     data: {
        //         title: "tomori bot",
        //         description: "",
        //         content: "public class Main { public static void main(String[] args) {} }"
        //     },
        //     headers: {
        //         Authorization: "Bearer " + store.state.user.token,
        //     },
        //     success: (resp) => {
        //         console.log(resp);
        //     },
        //     error: (resp) => {
        //         console.log(resp);
        //     }
        // })

        // const store = useStore();
        // $.ajax({
        //     url: "http://localhost:3000/user/bot/remove/",
        //     type: "post",
        //     data: {
        //         botId: 1,
        //     },
        //     headers: {
        //         Authorization: "Bearer " + store.state.user.token,
        //     },
        //     success: (resp) => {
        //         console.log(resp)
        //     },
        // });

        // const store = useStore();
        // $.ajax({
        //     url: "http://localhost:3000/user/bot/update/",
        //     type: "post",
        //     data: {
        //         botId: 3,
        //         title: "rikki bot",
        //         description: "rikki dayo, rikki ~",
        //         content: "print(Hello World!)",
        //     },
        //     headers: {
        //         Authorization: "Bearer " + store.state.user.token,
        //     },
        //     success: (resp) => {
        //         console.log(resp)
        //     },
        // });

        // const store = useStore();
        // $.ajax({
        //     url: "http://localhost:3000/user/bot/get/",
        //     type: "get",
        //     headers: {
        //         Authorization: "Bearer " + store.state.user.token,
        //     },
        //     success: (resp) => {
        //         console.log(resp)
        //     },
        // });
    }
}

</script>

<template>
  <ContentField>
    <h2>My bots</h2>
  </ContentField>
</template>

<style scoped>

</style>
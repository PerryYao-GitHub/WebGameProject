<script>
import $ from 'jquery';
import {useStore} from "vuex";
import {ref, reactive} from "vue";
import {Modal} from "bootstrap/dist/js/bootstrap";
import ace from 'ace-builds'
import {VAceEditor} from "vue3-ace-editor";
import ContentField from "@/components/ContentField.vue";

export default {
    components: {
        ContentField,
        VAceEditor,
    },

    setup: () => {
        ace.config.set(
            "basePath",
            "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")

        const store = useStore();
        let bots = ref([]);
        const botReactive = reactive({
            title: "",
            description: "",
            content: "",
            errorMsg: "",
        })

        const refreshBots = () => {  // 提供api获取bots列表
            $.ajax({
                url: "http://localhost:3000/user/bot/get/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success: (resp) => {
                    // console.log(resp)
                    bots.value = resp;
                },
            });
        }
        refreshBots();

        const addBot = () => {
            botReactive.errorMsg = "";
            $.ajax({
                url: "http://localhost:3000/user/bot/add/",
                type: "post",
                data: {
                    title: botReactive.title,
                    description: botReactive.description,
                    content: botReactive.content,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success: (resp) => {
                    // console.log(resp);
                    if (resp.msg === "success") {
                        refreshBots();
                        // 将botAdded内容清空
                        botReactive.title = "";
                        botReactive.description = "";
                        botReactive.content = "";
                        Modal.getInstance("#add-bot-btn").hide();
                    } else botReactive.errorMsg = resp.msg;

                },
                error: (resp) => {
                    // console.log(resp);
                    botReactive.errorMsg = resp.msg;
                }
            })
        }

        const removeBot = (botId) => {

            $.ajax({
                url: "http://localhost:3000/user/bot/remove/",
                type: "post",
                data: {
                    botId: botId,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success: () => {
                    // console.log(resp);
                    refreshBots();
                },
            });
        }

        const updateBot = (bot) => {
            $.ajax({
                url: "http://localhost:3000/user/bot/update/",
                type: "post",
                data: {
                    botId: bot.id,
                    title: bot.title,
                    description: bot.description,
                    content: bot.content,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success: (resp) => {
                    // console.log(resp);
                    if (resp.msg === "success") {
                        refreshBots();
                        Modal.getInstance("#update-bot-btn-" + bot.id).hide();
                    } else bot.msg = resp.msg;

                },
                error: (resp) => {
                    // console.log(resp);
                    botReactive.errorMsg = resp.msg;
                }
            });
        }


        return {
            store,
            bots,
            botAdded: botReactive,
            addBot,
            removeBot,
            updateBot,
        }
    }
}

</script>

<template>
    <ContentField>
        <div class="container">
            <div class="row">
                <div class="col-4">
                    <div class="card">
                        <div class="card-body">
                            <img class="user-avatar" :src="store.state.user.profile" alt="user's photo">
                        </div>
                    </div>
                </div>

                <div class="col-8">
                    <div class="card">
                        <div class="card-header">
                            <span style="font-size: 130%">My Bots</span>
                            <button type="button" class="btn btn-success btn-sm float-end"
                                    data-bs-toggle="modal"
                                    data-bs-target="#add-bot-btn">+ Create Bot
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="add-bot-btn" tabindex="-1">
                                <div class="modal-dialog modal-xl">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Create Bot</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                    aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">

                                            <div class="mb-3">
                                                <label for="add-bot-title" class="form-label">bot title</label>
                                                <input v-model="botAdded.title" type="text" class="form-control"
                                                       id="add-bot-title" placeholder="bot title">
                                            </div>

                                            <div class="mb-3">
                                                <label for="add-bot-description" class="form-label">bot description</label>
                                                <textarea v-model="botAdded.description" class="form-control"
                                                          id="add-bot-description" rows="3"
                                                          placeholder="bot description"></textarea>
                                            </div>

                                            <div class="mb-3">
                                                <label for="add-bot-content" class="form-label">bot codes</label>
                                                <!--                                                <textarea v-model="botAdded.content" class="form-control"-->
                                                <!--                                                          id="add-bot-content" rows="3"-->
                                                <!--                                                          placeholder="your bot code"></textarea>-->
                                                <VAceEditor
                                                    v-model:value="botAdded.content"
                                                    @init="editorInit"
                                                    lang="c_cpp"
                                                    theme="textmate"
                                                    style="height: 300px"/>

                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <span class="error-msg" style="color: red">{{ botAdded.errorMsg }}</span>
                                            <button @click="addBot" type="button" class="btn btn-primary">Create
                                            </button>
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                Close
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="card-body">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>title</th>
                                    <th>create-time</th>
                                    <th>options</th>
                                </tr>
                                </thead>

                                <tbody>
                                <tr v-for="bot in bots" :key="bot.id">
                                    <td>{{ bot.title }}</td>
                                    <td>{{ bot.createTime }}</td>
                                    <td>
                                        <button type="button" class="btn btn-sm btn-success" style="margin-right: 5px"
                                                data-bs-toggle="modal"
                                                :data-bs-target="'#update-bot-btn-' + bot.id">Edit
                                        </button>
                                        <!-- Modal -->
                                        <div class="modal fade" :id="'update-bot-btn-' + bot.id" tabindex="-1">
                                            <div class="modal-dialog modal-xl">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h1 class="modal-title fs-5" id="exampleModalLabel">Edit
                                                            Bot</h1>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">

                                                        <div class="mb-3">
                                                            <label for="add-bot-title" class="form-label">bot title</label>
                                                            <input v-model="bot.title" type="text"
                                                                   class="form-control"
                                                                   id="add-bot-title" placeholder="bot title">
                                                        </div>

                                                        <div class="mb-3">
                                                            <label for="add-bot-description" class="form-label">bot description</label>
                                                            <textarea v-model="bot.description"
                                                                      class="form-control"
                                                                      id="add-bot-description" rows="3"
                                                                      placeholder="bot description"></textarea>
                                                        </div>

                                                        <div class="mb-3">
                                                            <label for="update-bot-content" class="form-label">bot codes</label>
                                                            <!--                                                            <textarea v-model="bot.content" class="form-control"-->
                                                            <!--                                                                      id="add-bot-content" rows="3"-->
                                                            <!--                                                                      placeholder="your bot code"></textarea>-->
                                                            <VAceEditor
                                                                v-model:value="bot.content"
                                                                @init="editorInit"
                                                                lang="c_cpp"
                                                                theme="textmate"
                                                                style="height: 300px"/>

                                                        </div>
                                                    </div>
                                                    <div class="modal-footer">
                                                        <span class="error-msg" style="color: red">{{
                                                                bot.msg
                                                            }}</span>
                                                        <button @click="updateBot(bot)" type="button"
                                                                class="btn btn-primary">
                                                            Save Change
                                                        </button>
                                                        <button type="button" class="btn btn-secondary"
                                                                data-bs-dismiss="modal">
                                                            Close
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <button @click="removeBot(bot.id)" type="button" class="btn btn-sm btn-danger">
                                            Delete
                                        </button>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </ContentField>
</template>

<style scoped>
.card {
    margin-top: 20px;
}

.user-avatar {
    width: 150px; /* 设置固定宽度 */
    height: 150px; /* 设置固定高度 */
    object-fit: cover; /* 确保图片完全填充 */
    border-radius: 70%; /* 可选：如果需要圆形头像，可以添加圆角 */
}
</style>
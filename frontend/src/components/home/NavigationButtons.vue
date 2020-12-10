<template>
    <table>
        <tr>
            <td>
                <a @click="logout">
                    <button>Выход</button>
                </a>
            </td>
            <td>
                <a @click="clearHistory">
                    <button>Очистить историю</button>
                </a>
            </td>
        </tr>
    </table>
</template>

<script>

    import router from '../../router';
    import axios from "axios";

    export default {
        name: "NavigationButtons",
        methods: {
            logout: function () {
                this.$store.commit("removeToken");
                this.$store.commit("setLogin", "");
                this.$store.commit("setPoints", []);
                router.push("auth");
            },
            clearHistory: function () {
                axios({
                    method: 'delete',
                    url: "" +
                        //"api/" +
                        "user/" + this.$store.state.login + "/points",
                    headers: {'Authorization': 'Bearer ' + this.$store.state.token}
                }).then(() => {
                    this.$store.commit("setPoints", []);
                }).catch(error => {
                        alert(error);
                    })
            }
        }
    }
</script>

<style scoped>
    button {
        color: white;
        background: #4a90e2;
        line-height: 40px;
        border-width: 0;
        font-size: 20px;
        border-radius: 3px;
    }

    form {
        margin: 0px;
    }
</style>
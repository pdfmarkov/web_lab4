<template>
    <div id="loginAndRegistration">
        <nav v-bind:class="active" v-on:click.prevent>
            <a href="#" class="login" v-on:click="makeActive('login')">Вход</a>
            <a href="#" class="registration" v-on:click="makeActive('registration')">Регистрация</a>
        </nav>

        <login-and-registration-form v-bind:form-name="active"/>
    </div>
</template>

<script>
    import loginAndRegistrationForm from "./LoginAndRegistrationForm";

    export default {
        name: "LoginAndRegistration",
        components: {
          loginAndRegistrationForm
        },
        data() {
            return {
                active: "login",
                color: "#5e5b64"
            }
        },
        methods: {
            makeActive: function (item) {
                this.active = item;
            }
        },
    }
</script>

<style scoped>
    #loginAndRegistration {
        text-align: center;
    }
    a{
        color: #389dc1;
    }

    a, a:visited {
        outline:none;
        color:#389dc1;
    }

    a:hover{
        text-decoration:none;
    }

    nav{
        display:inline-block;
        margin:40px auto 0;
        background-color:#5597b4;
        box-shadow:0 1px 1px #ccc;
        border-radius:2px;
    }

    nav a{
        display:inline-block;
        padding: 18px 30px;
        color:#fff !important;
        font-weight:bold;
        font-size:16px;
        text-decoration:none !important;
        line-height:1;
        text-transform: uppercase;
        background-color:transparent;

        -webkit-transition:background-color 0.25s;
        -moz-transition:background-color 0.25s;
        transition:background-color 0.25s;
    }

    nav a:first-child{
        border-radius:2px 0 0 2px;
    }

    nav a:last-child{
        border-radius:0 2px 2px 0;
    }

    nav.login .login,
    nav.registration .registration{
        background-color:#e35885;
    }
</style>
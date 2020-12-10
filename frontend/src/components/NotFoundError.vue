<template>
    <div id="content" align="center">
        <h1 id="404">404</h1>
        <h2>К сожалению, такой страницы нет</h2>
        <p>Вы можете проверить корректность url-адреса либо перейти на главную страницу<br/>
            <router-link to="/auth">Вернуться на главную страницу</router-link>
        </p>
    </div>
</template>

<script>
    export default {
        name: "NotFoundError"
    }
</script>

<style scoped>
    h1 {
        font-size: 8em;
        margin: 0;
        color: #2a48eb;
        text-transform: uppercase;
        text-shadow: 0px 0px 6px rgba(255,255,255,0.7);

    }
    h2 {
        font-size: 33px;
        text-transform: uppercase;
        margin: 0;
        letter-spacing: 3px;
    }
</style>
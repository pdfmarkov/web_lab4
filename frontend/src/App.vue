<template>
    <div id="app">
        <header-info/>
        <router-view/>
    </div>
</template>

<script>
    import HeaderInfo from "./components/BaseHeader";

    export default {
        name: 'app',
        components: {
            HeaderInfo
        },
        methods: {

        }
    }
</script>

<style>
@import url("assets/css/form.css");
@import url('https://fonts.googleapis.com/css2?family=Jost&display=swap');

* {
  box-sizing: border-box;
  font-family: "Jost", serif;
  font-weight: normal;
}

body{
  margin: 0;
  padding: 0;
}

</style>

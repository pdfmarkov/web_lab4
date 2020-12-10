<template>
    <div class="header-info">
        <div id="option"><h2>Вариант 10113</h2></div>
        <div id="fio"><h2>Марков Петр/Тарасенко Владислав, группа P3210</h2></div>
    </div>
</template>

<script>
    export default {
        name: "HeaderInfo"
    }
</script>

<style scoped>
    .header-info{
        color: #4a90e2;
        background-color: white;
        height: 75px;
        border-bottom-style: solid;
        border-width: medium;
    }

    .header-info div{
        height: 100%;
        margin: auto 0;
    }

    #option {
        float: left;
        padding-left: 30px;
    }

    #fio {
        float: right;
        padding-right: 30px;
    }
    body {
      background: linear-gradient(270deg, #fca3cc, #bce6eb);
      background-size: 400% 400%;

      -webkit-animation: Gradient 30s ease infinite;
      -moz-animation: Gradient 30s ease infinite;
      animation: Gradient 30s ease infinite;
    }

    @-webkit-keyframes Gradient {
      0%{background-position:0% 50%}
      50%{background-position:100% 50%}
      100%{background-position:0% 50%}
    }
    @-moz-keyframes Gradient {
      0%{background-position:0% 50%}
      50%{background-position:100% 50%}
      100%{background-position:0% 50%}
    }
    @keyframes Gradient {
      0%{background-position:0% 50%}
      50%{background-position:100% 50%}
      100%{background-position:0% 50%}
    }
</style>
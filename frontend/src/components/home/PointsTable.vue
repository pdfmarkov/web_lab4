<template>
    <table class="results">
        <tr class="header">
            <th>Координата X</th>
            <th>Координата Y</th>
            <th>Радиус</th>
            <th>Результат</th>
            <th>Время запроса</th>
        </tr>
        <tr v-for="point in points" v-bind:key="point.requestDate">
            <td>{{ point.xcoord }}</td>
            <td>{{ point.ycoord }}</td>
            <td>{{ point.radius }}</td>
            <td>{{ point.isHit }}</td>
            <td>{{ new Date(point.requestDate).format("mm/dd/yyyy hh:MM:ss TT") }}</td>
        </tr>
    </table>
</template>

<script>
    import "../../libs/dateformat";

    export default {
        name: "PointsTable",
        computed: {
            points() {
                return this.$store.state.points;
            }
        },
    }
</script>

<style scoped>
    .results{
        background: white;
        padding-bottom: 80px;
        color: #4a90e2;
        font-size: 20px;
        border: 1px solid;
        border-radius: 3px;
        border-collapse: collapse;
    }

    .results td:not(.header){
        text-align: center;
        padding-right: 10px;
    }

    th{
        border-top: 1px solid;
        border-bottom: 1px solid;
        padding: 21px 25px 22px 25px;
    }
</style>
<template>
    <table columns="2" width="100%">
        <tr>
            <td>
                <point-create-form @addPoint="createPoint" v-bind:error-message-value="errorMessageValue"
                                   v-bind:is-error-message-hidden="isErrorMessageHidden"/>
            </td>
            <td>
                <SvgGrafic @svgClick="createPoint" @nullRadius="radiusReportNull"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <PointsTable/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <NavigationButtons/>
            </td>
        </tr>
    </table>
</template>

<script>
    import PointCreateForm from "./PointCreateForm";
    import SvgGrafic from "./SvgGrafic";
    import PointsTable from "./PointsTable";
    import NavigationButtons from "./NavigationButtons";
    import axios from "axios";
    export default {
        name: "Home",
        components: {
            NavigationButtons,
            PointsTable,
            SvgGrafic,
            PointCreateForm
        },
        computed: {
            token: function () {
                return this.$store.state.token;
            }
        },
        data : function(){
            return {
                errorMessageValue : "",
                isErrorMessageHidden : true,
            }
        },
        mounted() {
          this.$store.commit("setCurrentPoint", {"x" : "", "y" : "", "radius" : ""});
        },
        methods: {
            createPoint : function () {
                axios({
                    method : 'post',
                    url : "" +
                        //"api/" +
                        "user/" + this.$store.state.login + "/points",
                    headers : {'Authorization': 'Bearer '+ this.$store.state.token},
                    data : this.$store.state.currentPoint
                }).then(response => {
                    let newPointUrl = response.headers.location;
                    axios({
                        method: 'get',
                        url : "" +
                            //"api/" +
                            "user/" + this.$store.state.login + "/points" + newPointUrl,
                        headers : {'Authorization': 'Bearer '+ this.$store.state.token}
                    }).then(response => {
                        this.$store.commit("addPoint", response.data);
                    }).catch(error => {
                        this.errorMessageValue = error.response.data;
                        this.isErrorMessageHidden = false;
                    });

                    this.$router.push("home");
                })
                    .catch(error => {
                        this.errorMessageValue = error.response.data;
                        this.isErrorMessageHidden = false;
                    });
            },
            radiusReportNull : function () {
                this.errorMessageValue = 'Радиус должен быть задан';
                this.isErrorMessageHidden = false;
            }
        }
    }
</script>

<style scoped>

</style>
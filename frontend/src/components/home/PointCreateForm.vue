<template>
    <form @submit.prevent="createPoint">
        <div align="center">
            <h3>Форма ввода данных</h3>
        </div>
        <div class="form-row">
            <input v-model="xValue" type="text" id="xValue" maxlength="10"
                   @input="xValue = validate(xValue, -5, 5); commitPoint();" required>
            <label for="xValue">Координата X</label>
        </div>
        <div class="form-row">
                <input v-model="yValue" type="text" id="yValue" maxlength="10"
                       @input="yValue = validate(yValue, -5, 5); commitPoint()" required>
            <label for="yValue">Координата Y</label>
        </div>
        <div class="form-row">
            <input v-model="radius" type="text" id="radius" maxlength="10"
                   @input="radius = validate(radius, -5, 5); commitPoint();" required>
            <label for="radius">Радиус</label>
        </div>

        <div class="error-row" align="left">
            <label :hidden="isErrorMessageHidden">{{ errorMessageValue }}</label>
        </div>

        <div class="submit-row">
                <input type="submit" value="Отправить">
        </div>
    </form>
</template>

<script>
    export default {
        name: "PointCreateForm",
        data: function(){
          return{
              xValue : "",
              yValue : "",
              radius : "",
          }
        },
        props : {
            errorMessageValue : String,
            isErrorMessageHidden : Boolean
        },
        methods :{
            validate : function (input, l, r) {
                input = input.replace(/[^0-9,.-]/g, '').replace(/,/, ".");
                if (isNaN(input) && input !== "-")
                    input = "";
                if (input > r)
                    input = r;
                if (input < l) {
                    input = l;
                }
                if (input.length > 10) {
                    input = input.slice(0, -1);
                }
                return input;
                /*if(input.id == "form:r-spinner_input")
                    drawPoints();*/
            },

            createPoint : function () {
                this.$emit("addPoint");
            },
            commitPoint : function () {
                this.$store.commit("setCurrentPoint", {"x" : this.xValue, "y" : this.yValue, "radius" : this.radius});
            }
        }
    }
</script>

<style scoped>
form{
    margin: 50px auto 30px;
}
</style>
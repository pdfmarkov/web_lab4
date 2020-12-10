<template>
    <svg width="300" height="300" @click="svgClick" ref="svg">
        <line x1="0" x2="300" y1="150" y2="150" stroke="black"></line>
        <line x1="150" x2="150" y1="0" y2="300" stroke="black"></line>
        <polyline points="156 10, 150 0, 144 10" stroke="black" fill="none"></polyline>
        <polyline points="290 144, 300 150, 290 156" stroke="black" fill="none"></polyline>

        <!--Длина положительной части оси = 1.2 * R  1.2 * R = 150 => R = 125
            Надпись R отстаёт на 4 px, R/2 на 8px
        -->

        <line x1="212.5" x2="212.5" y1="155" y2="145" stroke="black"></line>
        <line x1="275" x2="275" y1="155" y2="145" stroke="black"></line>

        <line x1="25" x2="25" y1="155" y2="145" stroke="black"></line>
        <line x1="87.5" x2="87.5" y1="155" y2="145" stroke="black"></line>

        <line x1="145" x2="155" y1="87.5" y2="87.5" stroke="black"></line>
        <line x1="145" x2="155" y1="25" y2="25" stroke="black"></line>

        <line x1="145" x2="155" y1="212.5" y2="212.5" stroke="black"></line>
        <line x1="145" x2="155" y1="275" y2="275" stroke="black"></line>

        <text x="204.5" y="140" class="R2"> {{radiusOnGrafic.radDiv2}}</text>
        <text x="271" y="140" class="R"> {{radiusOnGrafic.rad}}</text>

        <text x="19" y="140" class="mR">{{radiusOnGrafic.minRad}}</text>
        <text x="75.5" y="140" class="mR2">{{radiusOnGrafic.minRadDiv2}}</text>

        <text x="160" y="91.5" class="R2">{{radiusOnGrafic.radDiv2}}</text>
        <text x="160" y="29" class="R">{{radiusOnGrafic.rad}}</text>

        <text x="160" y="216.5" class="mR2">{{radiusOnGrafic.minRadDiv2}}</text>
        <text x="160" y="279" class="mR">{{radiusOnGrafic.minRad}}</text>

        <path d="M 25 150 A 125 125, 90, 0, 0, 150 275 L 150 150 Z"
              fill="orange" fill-opacity="0.7" stroke="#4a90e2"></path>

        <polygon points="150 150, 212.5 150, 150 275"
                 fill="purple" fill-opacity="0.7" stroke="#4a90e2"></polygon>

        <rect x="87.5" y="25" width="62.5" height="125" fill="yellow" fill-opacity="0.7" stroke="#4a90e2"></rect>

        <circle v-for="point in svgPoints" v-bind:key="point.requestDate" r="5" v-bind:cx="point.xcoord" v-bind:cy="point.ycoord"
                v-bind:fill="point.isHit === 'YES' ? 'green' : 'red'" stroke="#4a90e2"/>

    </svg>
</template>

<script>
    export default {
        name: "SvgGrafic",
        computed: {
            radiusOnGrafic : function () {
                if(this.$store.state.currentPoint.radius === ""){
                    return { "rad" : "R", "radDiv2" : "R/2", "minRad" : "-R", "minRadDiv2" : "-R/2"}
                } else{
                    return {
                        "rad" : parseFloat(this.$store.state.currentPoint.radius).toFixed(1),
                        "radDiv2" : (this.$store.state.currentPoint.radius / 2.0).toFixed(1),
                        "minRad" : ((-1.0) * this.$store.state.currentPoint.radius).toFixed(1),
                        "minRadDiv2" : ((-1.0) * this.$store.state.currentPoint.radius / 2.0).toFixed(1)
                    }
                }
            },
            svgPoints(){
                let points = JSON.parse(JSON.stringify(this.$store.state.points));
                points.forEach((value, index, array) => {
                    let radius = (this.$store.state.currentPoint.radius === "" ? parseFloat(value.radius) : this.$store.state.currentPoint.radius);
                    let x = parseFloat(value.xcoord) / radius * 125 + 150;
                    let y = 150 - parseFloat(value.ycoord) / radius * 125;

                    array[index].radius = radius;
                    array[index].xcoord = x;
                    array[index].ycoord = y;
                });
                return points;
            }
        },
        methods: {
            svgClick: function (ev) {

                if (this.$store.state.currentPoint.radius === "") {
                    this.$emit("nullRadius");
                    return;
                }

                let pos = this.getMousePosition(ev);
                this.$store.commit("setCurrentPointX", ((pos.x - 150) / 125 * this.$store.state.currentPoint.radius).toFixed(6));
                this.$store.commit("setCurrentPointY", ((150 - pos.y) / 125 * this.$store.state.currentPoint.radius).toFixed(6));
                this.$emit("svgClick");
            },
            getMousePosition: function (event) {
                let rect = this.$refs["svg"].getBoundingClientRect();
                return {
                    x: event.clientX - rect.left,
                    y: event.clientY - rect.top
                };
            }
        }
    }
</script>

<style scoped>

</style>
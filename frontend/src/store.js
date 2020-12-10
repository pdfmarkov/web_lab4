import Vuex from "vuex";
import Vue from 'vue'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        token : "",
        tokenDate : null,
        points : [],
        login : "",
        currentPoint : {"x" : "", "y" : "", "radius" : ""}
    },
    mutations: {
        setToken(state, tokenString) {
            state.token = tokenString;
            state.tokenDate = new Date().getTime() + 3600000;
        },
        removeToken(state){
          state.token = "";
          state.tokenDate = 0;
        },
        setPoints(state, pointsArray) {
            state.points = pointsArray;
        },
        addPoint(state, point){
            state.points.push(point);
        },
        setLogin(state, login){
            state.login = login;
        },
        setCurrentPoint(state, point){
            state.currentPoint = point;
        },
        setCurrentPointX(state, x){
            state.currentPoint.x = x;
        },
        setCurrentPointY(state, y){
            state.currentPoint.y = y;
        },
        setCurrentPointR(state, radius){
            state.currentPoint.radius = radius;
        },
    },
    plugins: [createPersistedState()]
});

export default store
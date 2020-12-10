import Vue from 'vue'
import App from './App.vue'
import Vuex from "vuex";
import VueRouter from "vue-router";
import router from "./router"
import store from "./store";

Vue.config.productionTip = false;

Vue.use(Vuex);
Vue.use(VueRouter);

new Vue({
    store,
    router,
    render: h => h(App),
}).$mount('#app');
import Vue from 'vue'
import Main from 'pages/Main.vue'
import router from 'router/router'
import Title from "./pages/Title.vue"

new Vue({
    el: '#app',
    router,
    render: h => h(Title)
});
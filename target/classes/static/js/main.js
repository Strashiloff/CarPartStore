import Vue from 'vue'
import Main from 'pages/Main.vue'
import router from 'router/router'

new Vue({
    el: '#app',
    router,
    render: a => a(Main)
});
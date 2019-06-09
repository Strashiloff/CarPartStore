import Vue from "vue";
import VueRouter from "vue-router"
import Main from 'pages/Main.vue'
import Login from 'pages/Login.vue'
import Test from  'pages/Test.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: Main},
    { path: '/test', component: Test},
    { path: '/login', component: Login},
]

export default new VueRouter({
    mode: 'history',
    routes
})

import Vue from "vue";
import VueRouter from "vue-router"
import Login from 'pages/Login.vue'
import Test from  'pages/Test.vue'
import Title from 'pages/Title.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: Title},
    { path: '/test', component: Test},
    { path: '/login', component: Login},
]

export default new VueRouter({
    mode: 'history',
    routes
})

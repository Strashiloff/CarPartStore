import Vue from "vue";
import VueRouter from "vue-router"
import Test from 'pages/Test.vue'
import Title from 'pages/Title.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/tit', component: Title},
    { path: '/test', component: Test},
]

export default new VueRouter({
    mode: 'history',
    routes
})

import Vue from "vue";
import VueRouter from "vue-router"
import Title from 'pages/Title.vue'
import AddUser from "pages/AddUser.vue"

Vue.use(VueRouter)

const routes = [
    { path: '/tit', component: Title},
    { path: '/registration', component: AddUser},
]

export default new VueRouter({
    mode: 'history',
    routes
})

import Vue from "vue";
import VueRouter from "vue-router"
import AddUser from "pages/admin/AddUser.vue"
import Admin from 'pages/admin/Admin.vue'
import Users from "pages/admin/Users.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/admin',
        component: Admin,
        children:[
            {
                path: 'registration',
                component: AddUser
            },
            {
                path: 'users',
                component: Users
            }
        ]
    },

]

export default new VueRouter({
    mode: 'history',
    routes
})

import Vue from "vue"
import VueRouter from "vue-router"
import AddUser from "pages/admin/AddUser.vue"
import Admin from 'pages/admin/Admin.vue'
import Users from "pages/admin/Users.vue"
import Test from "pages/Test.vue"
import Post from "pages/admin/Post.vue"
import Home from "pages/Home.vue"
import Stoke from 'pages/stock/Settings.vue'

Vue.use(VueRouter)

const routes = [
	{
		path: '/',
		component: Home,
	},
	{
		path: '/admin',
		component: Admin,
		children: [
			{
				path: 'registration',
				component: AddUser
			},
			{
				path: 'posts',
				component: Post
			},
			{
				path: 'users',
				component: Users
			}
		]
	},
	{
		path: '/tit',
		component: Test,
	},
	{
		path: '/stokes',
		component: Stoke,
	}
]

export default new VueRouter({
	mode: 'history',
	routes
})

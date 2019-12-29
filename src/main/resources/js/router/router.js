import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from 'pages/Home.vue'
import admin from './admin'
import stoke from './stoke'
import deliveries from './deliveries'
import orders from './orders'
import Account from 'pages/account/Account.vue'
import UserList from 'pages/account/UserList.vue'

Vue.use(VueRouter)

const routes = [
	{
		path: '*',
		name: 'home',
		component: Home,
	},
	{
		path: '/account',
		name: 'account',
		component: Account
	},
	{
		path: '/userlist',
		name: 'userlist',
		component: UserList
	},
	...admin,
	...stoke,
	...deliveries,
	...orders
]

export default new VueRouter({
	mode: 'history',
	routes
})

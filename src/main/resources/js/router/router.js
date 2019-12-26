import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from 'pages/Home.vue'
import admin from './admin'
import stoke from './stoke'
import deliveries from './deliveries'
import orders from './orders'

Vue.use(VueRouter)

const routes = [
	{
		path: '*',
		name: 'home',
		component: Home,
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

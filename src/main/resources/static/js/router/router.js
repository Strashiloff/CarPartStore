import Vue from "vue"
import VueRouter from "vue-router"
import Home from "pages/Home.vue"
import admin from "./admin"
import stoke from "./stoke"
import deliveries from './deliveries'

Vue.use(VueRouter)

const routes = [
	{
		path: '*',
		name: 'home',
		component: Home,
	},
	...admin,
	...stoke,
	...deliveries
]

export default new VueRouter({
	mode: 'history',
	routes
})

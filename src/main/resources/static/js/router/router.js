import Vue from "vue"
import VueRouter from "vue-router"
import Home from "pages/Home.vue"
import admin from "./admin"
import stoke from "./stoke"

Vue.use(VueRouter)

const routes = [
	{
		path: '*',
		name: 'home',
		component: Home,
	},
	...admin,
	...stoke
]

export default new VueRouter({
	mode: 'history',
	routes
})

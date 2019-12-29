import Vue from 'vue'

const buys = Vue.resource('/buys')
const deletebuy = Vue.resource('/buys/delete')

export default {
	allbuys: () => buys.get(),
	addbuy: buy => buys.save(buy),
	savebuy: buy => buys.update(buy),
	removebuy: buy => deletebuy.save(buy)
}
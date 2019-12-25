import Vue from 'vue'

const supplies = Vue.resource('/supply')
const supplyType = Vue.resource('/supply/delete')

export default {
	getAllSupplies: () => supplies.get(),
	addSypply: supply => supplies.save(supply),
	saveSypply: supply => supplies.update(supply),
	delSypply: supply => supplyType.save(supply)
}
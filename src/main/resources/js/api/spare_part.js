import Vue from 'vue'

const parts = Vue.resource('/part')
const deletePart = Vue.resource('/part/delete')

export default {
	getAllSpareParts: () => parts.get(),
	addSparePart: spare_part => parts.save(spare_part),
	saveSparePart: spare_part => parts.update(spare_part),
	delSparePart: spare_part => deletePart.save(spare_part)
}
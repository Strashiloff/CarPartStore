import Vue from 'vue'

const types = Vue.resource('/type')
const deleteType = Vue.resource('/type/delete')

export default {
	getAllTypes: () => types.get(),
	addType: type => types.save(type),
	saveType: type => types.update(type),
	delType: type => deleteType.save(type)
}
import Vue from 'vue'

const stokes = Vue.resource('/stoke')
const deleteStoke = Vue.resource('/stoke/delete')

export default {
	getAllStokes: () => stokes.get(),
	addStoke: stoke => stokes.save(stoke),
	saveStoke: stoke => stokes.update(stoke),
	delStoke: stoke => deleteStoke.save(stoke)
}
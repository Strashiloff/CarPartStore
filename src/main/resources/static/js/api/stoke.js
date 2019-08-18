import Vue from 'vue'

const stokes = Vue.resource('/stoke')

export default {
	getAllStokes: () => stokes.get(),
	saveStoke: stoke => stokes.save(stoke),
	delStoke: stoke => stokes.remove(stoke)
}
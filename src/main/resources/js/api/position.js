import Vue from 'vue'

const positions = Vue.resource('/position')
const deletePosition = Vue.resource('/position/delete')

export default {
	allPositions: () => positions.get(),
	addPosition: position => positions.save(position),
	savePosition: position => positions.update(position),
	removePosition: position => deletePosition.save(position)
}
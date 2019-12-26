import Vue from 'vue'

const defects = Vue.resource('/defect')
const deleteDefect = Vue.resource('/defect/delete')

export default {
	allDefects: () => defects.get(),
	addDefect: defect => defects.save(defect),
	saveDefect: defect => defects.update(defect),
	removeDefect: defect => deleteDefect.save(defect)
}
import Vue from 'vue'

const purveyors = Vue.resource('/purveyor')
const deletePurveyor = Vue.resource('/purveyor/delete')

export default {
	allPurveyors: () => purveyors.get(),
	addPurveyor: purveyor => purveyors.save(purveyor),
	savePurveyor: purveyor => purveyors.update(purveyor),
	removePurveyor: purveyor => deletePurveyor.save(purveyor)
}
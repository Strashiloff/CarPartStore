import purveyorApi from '../../api/purveyor'
import getIndex from './utils'

export const modulePurveyor = {
	namespaced: true,
	state: {
		purveyors: [],
	},
	actions: {
		async getPurveyorsAction({commit}) {
			const result = await purveyorApi.allPurveyors()
			const data = await result.json()
			commit('getPurveyorsMutation', data)
		},
		async addPurveyorAction({commit}, purveyor) {
			const result = await purveyorApi.addPurveyor(purveyor)
			const data = await result.json()
			if (data.ok) {
				commit('addPurveyorMutation', purveyor)
			}
		},
		async savePurveyorAction({commit}, purveyor) {
			const result = await purveyorApi.savePurveyor(purveyor)
			const data = await result.json()
			if (data.ok) commit('savePurveyorMutation', purveyor)
		},
		async removePurveyorAction({commit}, purveyor) {
			const result = await purveyorApi.removePurveyor(purveyor)
			const data = await result.json()
			if (data.ok) commit('removePurveyorMutation', purveyor)
		}
	},
	mutations: {
		getPurveyorsMutation(state, purveyors) {
			state.purveyors = purveyors
		},
		addPurveyorMutation(state, purveyor) {},
		savePurveyorMutation(state, purveyor) {
			let index = getIndex(state.purveyors, purveyor.id)
			state.purveyors.splice(index, 1, purveyor)
		},
		removePurveyorMutation(state, purveyor) {
			state.purveyors.splice(getIndex(state.purveyors, purveyor.id), 1)
		},
	},
	getters: {
		getAllPurveyors: state => state.purveyors,
		getPurveyorById: state => id => state.purveyors.find(elem => {
			return elem.id == id
		})
  }
}
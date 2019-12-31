import purveyorApi from '../../api/purveyor'
import getIndex from './utils'

export const modulePurveyor = {
	namespaced: true,
	state: {
		purveyors: [],
	},
	actions: {
		async getPurveyorsAction({ commit, dispatch }) {
			const result = await purveyorApi.allPurveyors()
			const data = await result.json()
			commit('getPurveyorsMutation', data)
		},
		async addPurveyorAction({ commit, dispatch }, purveyor) {
			const result = await purveyorApi.addPurveyor(purveyor)
			const data = await result.json()
			if (data.okey) {
				commit('addPurveyorMutation', purveyor)
			} else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось добавить поставщика, возможно такой поставщик уже есть в базе'
      }, { root: true })
		},
		async savePurveyorAction({ commit, dispatch }, purveyor) {
			const result = await purveyorApi.savePurveyor(purveyor)
			const data = await result.json()
			if (data.okey) commit('savePurveyorMutation', purveyor)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось сохрнанить поставщика'
      }, { root: true })
		},
		async removePurveyorAction({ commit, dispatch }, purveyor) {
			const result = await purveyorApi.removePurveyor(purveyor)
			const data = await result.json()
			if (data.okey) commit('removePurveyorMutation', purveyor)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось удалить поставщика, возможно поставщик указан в поставке'
      }, { root: true })
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
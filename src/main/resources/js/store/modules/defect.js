import defectApi from '../../api/defect'
import getIndex from './utils'

export const moduleDefect = {
	namespaced: true,
	state: {
		defects: [],
		defectsView: []
	},
	actions: {
		async getDefectsAction({commit}) {
			const result = await defectApi.allDefects()
			const data = await result.json()
			commit('getDefectsMutation', data)
		},
		async addDefectAction({commit}, defect) {
			const result = await defectApi.addDefect(defect)
			const data = await result.json()
			if (data.ok) {
				commit('addDefectMutation', defect)
			}
		},
		async saveDefectAction({commit}, defect) {
			const result = await defectApi.saveDefect(defect)
			const data = await result.json()
			if (data.ok) commit('saveDefectMutation', defect)
		},
		async removeDefectAction({commit}, defect) {
			const result = await defectApi.removeDefect(defect)
			const data = await result.json()
			if (data.ok) commit('removeDefectMutation', defect)
		}
	},
	mutations: {
		getDefectsMutation(state, data) {
			state.defects = data
		},
		addDefectMutation(state, defect) {},
		saveDefectMutation(state, defect) {
			let index = getIndex(state.defects, defect.id)
			state.defects.splice(index, 1, defect)
		},
		removeDefectMutation(state, defect) {
			state.defects.splice(getIndex(state.defects, defect.id), 1)
		},
	},
	getters: {
		getAllDefects: state => state.defects,
		getDefectById: state => id => state.defects.find(elem => {
			return elem.id == id
		})
  }
}
import defectApi from '../../api/defect'
import getIndex from './utils'

export const moduleDefect = {
	namespaced: true,
	state: {
		defects: [],
		defectsView: []
	},
	actions: {
		async getDefectsAction({ commit, dispatch }) {
			const result = await defectApi.allDefects()
			const data = await result.json()
			commit('getDefectsMutation', data)
		},
		async addDefectAction({ commit, dispatch }, defect) {
			const result = await defectApi.addDefect(defect)
			const data = await result.json()
			if (data.okey) {
				commit('addDefectMutation', defect)
			} else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка добавления позиции с дефектом'
      }, { root: true })
		},
		async saveDefectAction({ commit, dispatch }, defect) {
			const result = await defectApi.saveDefect(defect)
			const data = await result.json()
			if (data.okey) commit('saveDefectMutation', defect)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка сохранения покупателя'
      }, { root: true })
		},
		async removeDefectAction({ commit, dispatch }, defect) {
			const result = await defectApi.removeDefect(defect)
			const data = await result.json()
			if (data.okey) commit('removeDefectMutation', defect)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка удаления информации о дефекте'
      }, { root: true })
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
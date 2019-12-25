import sparePartApi from '../../api/spare_part'
import getIndex from './utils'

export const moduleSpareParts = {
	namespaced: true,
	state: {
		spareParts: [],
		edit: {}
	},
	actions: {
		async getAllSparePartsAction ({ commit }) {
			const result = await sparePartApi.getAllSpareParts()
			const data = await result.json()
			commit('getAllSparePartMutation', data)
		},
		async addSparePartsAction ({ commit, state }, part) {
			const result = await sparePartApi.addSparePart(part)
			const data = await result.json()
			if (data.ok) {
				commit('addSparePartsMutation', part)
			}
		},
		async deleteSparePartsAction ({ commit, state }, part) {
			let sp = getIndex(state.spareParts, part.id)
			const result = await sparePartApi.delSparePart(state.spareParts[sp])
			const data = await result.json()
			if (data.ok) {
				commit('deleteSparePartsMutation', part)
			}
		},
		async saveSparePartsAction ({ commit }, part) {
			const result = await sparePartApi.saveSparePart(part)
			const data = await result.json()
			if (data.ok) {
				commit('saveSparePartsMutation', part)
			}
		},
		setEditSparePartAction ({ commit }, part) { commit('setEditSparePartMutation', part)}
	},
	mutations: {
		getAllSparePartMutation (state, data) { state.spareParts = data },
		addSparePartsMutation (state, data) {},
		saveSparePartsMutation  (state, part) {
			let index = getIndex(state.spareParts, part.id)
			state.spareParts.splice(index, 1, part)
		},
		deleteSparePartsMutation (state, part) {
      let index = getIndex(state.spareParts, part.id)
			state.spareParts.splice(index, 1)
		},
		setEditMutation (state, part) {
			state.edit = part
		}
	},
	getters: {
		getAllSpareParts: state => state.spareParts,
		getSparePartById: state => id => state.spareParts.find(elem => {
			return elem.id == id
		}),
		// getStokeById: state => id => state.stokes
		getEdit: state => state.edit
	}
}
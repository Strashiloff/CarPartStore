import supplyApi from '../../api/supply'
import getIndex from './utils'

export const moduleSupply = {
	namespaced: true,
	state: {
		supplies: [],
	},
	actions: {
		async getSuppliesAction({ commit, dispatch }) {
			const result = await supplyApi.getAllSupplies()
			const data = await result.json()
			commit('getSuppliesMutation', data)
		},
		async addSupplyAction({ commit, dispatch }, supply) {
			const result = await supplyApi.addSypply(supply)
			const data = await result.json()
			if (data.okey) {
				commit('addSupplyMutation', supply)
			} else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось сохранить поставку'
      }, { root: true })
		},
		async saveSupplyAction({ commit, dispatch }, supply) {
			const result = await supplyApi.saveSypply(supply)
			const data = await result.json()
			if (data.okey) commit('saveSupplyMutation', supply)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось сохранить поставку'
      }, { root: true })
		},
		async removeSupplyAction({ commit, dispatch }, supply) {
			const result = await supplyApi.delSypply(supply)
			const data = await result.json()
			if (data.okey) commit('removeSupplyMutation', supply)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось удалить поставку'
      }, { root: true })
		}
	},
	mutations: {
		getSuppliesMutation(state, supplies) {
			state.supplies = supplies
		},
		addSupplyMutation(state, supply) {},
		saveSupplyMutation(state, supply) {
			let index = getIndex(state.supplies, supply.id)
			state.supplies.splice(index, 1, supply)
		},
		removeSupplyMutation(state, supply) {
			state.supplies.splice(getIndex(state.supplies, supply.id), 1)
		},
	},
	getters: {
		getAllSupplies: state => state.supplies,
		getSupplyById: state => id => state.supplies.find(elem => {
			return elem.id == id
		})
  }
}
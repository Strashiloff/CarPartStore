import supplyApi from '../../api/supply'
import getIndex from './utils'

export const moduleSupply = {
	namespaced: true,
	state: {
		supplies: [],
	},
	actions: {
		async getSuppliesAction({commit}) {
			const result = await supplyApi.getAllSupplies()
			const data = await result.json()
			commit('getSuppliesMutation', data)
		},
		async addSupplyAction({commit}, supply) {
			const result = await supplyApi.addSypply(supply)
			const data = await result.json()
			if (data.ok) {
				commit('addSupplyMutation', supply)
			}
		},
		async saveSupplyAction({commit}, supply) {
			const result = await supplyApi.saveSypply(supply)
			const data = await result.json()
			if (data.ok) commit('saveSupplyMutation', supply)
		},
		async removeSupplyAction({commit}, supply) {
			const result = await supplyApi.delSypply(supply)
			const data = await result.json()
			if (data.ok) commit('removeSupplyMutation', supply)
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
import contractApi from '../../api/contract'
import getIndex from './utils'

export const moduleContract = {
	namespaced: true,
	state: {
		contracts: [],
	},
	actions: {
		async getContractsAction({commit}) {
			const result = await contractApi.allContracts()
			const data = await result.json()
			commit('getContractsMutation', data)
		},
		async addContractAction({commit}, paylaod) {
			const result = await contractApi.addContract(paylaod.contract)
			const data = await result.json()
			if (data.ok) {
				commit('addContractMutation', paylaod)
			}
		},
		async saveContractAction({commit}, contract) {
			const result = await contractApi.saveContract(contract)
			const data = await result.json()
			if (data.ok) commit('saveContractMutation', contract)
		},
		async removeContractAction({commit}, contract) {
			const result = await contractApi.removeContract(contract)
			const data = await result.json()
			if (data.ok) commit('removeContractMutation', contract)
		}
	},
	mutations: {
		getContractsMutation(state, contracts) {
			state.contracts = contracts
		},
		addContractMutation(state, contract) {},
		saveContractMutation(state, contract) {
			let index = getIndex(state.contracts, contract.id)
			state.contracts.splice(index, 1, contract)
		},
		removeContractMutation(state, contract) {
			state.contracts.splice(getIndex(state.contracts, contract.id), 1)
		},
	},
	getters: {
		getAllContracts: state => state.contracts,
		getContractById: state => id => state.contracts.find(elem => {
			return elem.id == id
		})
  }
}
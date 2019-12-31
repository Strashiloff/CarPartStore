import contractApi from '../../api/contract'
import getIndex from './utils'

export const moduleContract = {
	namespaced: true,
	state: {
		contracts: [],
	},
	actions: {
		async getContractsAction({ commit, dispatch }) {
			const result = await contractApi.allContracts()
			const data = await result.json()
			commit('getContractsMutation', data)
		},
		async addContractAction({ commit, dispatch }, paylaod) {
			const result = await contractApi.addContract(paylaod.contract)
			const data = await result.json()
			if (data.okey) {
				commit('addContractMutation', paylaod)
			} else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка создания контракта'
      }, { root: true })
		},
		async saveContractAction({ commit, dispatch }, contract) {
			const result = await contractApi.saveContract(contract)
			const data = await result.json()
			if (data.okey) commit('saveContractMutation', contract)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка сохранения контракта'
      }, { root: true })
		},
		async removeContractAction({ commit, dispatch }, contract) {
			const result = await contractApi.removeContract(contract)
			const data = await result.json()
			if (data.okey) commit('removeContractMutation', contract)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка удаления контракта'
      }, { root: true })
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
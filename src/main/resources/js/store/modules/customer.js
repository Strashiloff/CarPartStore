import customerApi from '../../api/customer'
import getIndex from './utils'

export const moduleCustomer = {
	namespaced: true,
	state: {
		customers: [],
	},
	actions: {
		async getCustomersAction({ commit, dispatch }) {
			const result = await customerApi.allCustomers()
			const data = await result.json()
			commit('getCustomersMutation', data)
		},
		async addCustomerAction({ commit, dispatch }, customer) {
			const result = await customerApi.addCustomer(customer)
			const data = await result.json()
			if (data.okey) {
				commit('addCustomerMutation', customer)
			} else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка добавления покупателя, возможно такой уже существует'
      }, { root: true })
		},
		async saveCustomerAction({ commit, dispatch }, customer) {
			const result = await customerApi.saveCustomer(customer)
			const data = await result.json()
			if (data.okey) commit('saveCustomerMutation', customer)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка сохранения покупателя'
      }, { root: true })
		},
		async removeCustomerAction({ commit, dispatch }, customer) {
			const result = await customerApi.removeCustomer(customer)
			const data = await result.json()
			if (data.okey) commit('removeCustomerMutation', customer)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка удаления покупателя'
      }, { root: true })
		}
	},
	mutations: {
		getCustomersMutation(state, customers) {
			state.customers = customers
		},
		addCustomerMutation(state, customer) {},
		saveCustomerMutation(state, customer) {
			let index = getIndex(state.customers, customer.id)
			state.customers.splice(index, 1, customer)
		},
		removeCustomerMutation(state, customer) {
			state.customers.splice(getIndex(state.customers, customer.id), 1)
		},
	},
	getters: {
		getAllCustomers: state => state.customers,
		getCustomerById: state => id => state.customers.find(elem => {
			return elem.id == id
		})
  }
}
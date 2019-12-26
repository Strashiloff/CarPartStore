import customerApi from '../../api/customer'
import getIndex from './utils'

export const moduleCustomer = {
	namespaced: true,
	state: {
		customers: [],
	},
	actions: {
		async getCustomersAction({commit}) {
			const result = await customerApi.allCustomers()
			const data = await result.json()
			commit('getCustomersMutation', data)
		},
		async addCustomerAction({commit}, customer) {
			const result = await customerApi.addCustomer(customer)
			const data = await result.json()
			if (data.ok) {
				commit('addCustomerMutation', customer)
			}
		},
		async saveCustomerAction({commit}, customer) {
			const result = await customerApi.saveCustomer(customer)
			const data = await result.json()
			if (data.ok) commit('saveCustomerMutation', customer)
		},
		async removeCustomerAction({commit}, customer) {
			const result = await customerApi.removeCustomer(customer)
			const data = await result.json()
			if (data.ok) commit('removeCustomerMutation', customer)
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
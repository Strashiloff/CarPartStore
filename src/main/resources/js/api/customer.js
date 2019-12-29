import Vue from 'vue'

const customers = Vue.resource('/customers')
const deleteCustomer = Vue.resource('/customers/delete')

export default {
	allCustomers: () => customers.get(),
	addCustomer: customer => customers.save(customer),
	saveCustomer: customer => customers.update(customer),
	removeCustomer: customer => deleteCustomer.save(customer)
}
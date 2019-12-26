import Vue from 'vue'

const contracts = Vue.resource('/contract')
const deleteContract = Vue.resource('/contract/delete')

export default {
	allContracts: () => contracts.get(),
	addContract: contract => contracts.save(contract),
	saveContract: contract => contracts.update(contract),
	removeContract: contract => deleteContract.save(contract)
}
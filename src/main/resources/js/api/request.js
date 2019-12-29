import Vue from 'vue'

const requests = Vue.resource('/request')
const deleteRequest = Vue.resource('/request/delete')

export default {
	allRequests: () => requests.get(),
	addRequest: request => requests.save(request),
	saveRequest: request => requests.update(request),
	removeRequest: request => deleteRequest.save(request)
}
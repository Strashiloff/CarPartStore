import requestApi from '../../api/request'
import getIndex from './utils'

export const moduleRequest = {
	namespaced: true,
	state: {
		requests: [],
		requestsView: []
	},
	actions: {
		async getRequestsAction({commit, rootGetters}) {
			const result = await requestApi.allRequests()
			const data = await result.json()
			commit('getRequestsMutation', {
				requests: data,
				rootGetters: rootGetters
			})
		},
		async addRequestAction({commit}, request) {
			const result = await requestApi.addRequest(request)
			const data = await result.json()
			if (data.ok) {
				commit('addRequestMutation', request)
			}
		},
		async saveRequestAction({commit}, request) {
			const result = await requestApi.saveRequest(request)
			const data = await result.json()
			if (data.ok) commit('saveRequestMutation', request)
		},
		async removeRequestAction({commit}, request) {
			const result = await requestApi.removeRequest(request)
			const data = await result.json()
			if (data.ok) commit('removeRequestMutation', request)
		}
	},
	mutations: {
		getRequestsMutation(state, data) {
			state.requests = data.requests
			// requests.forEach(elem => {
			// 	state.requestsView.push({
			// 		id: id,

			// 	})
			// })
		},
		addRequestMutation(state, request) {},
		saveRequestMutation(state, request) {
			let index = getIndex(state.requests, request.id)
			state.requests.splice(index, 1, request)
		},
		removeRequestMutation(state, request) {
			state.requests.splice(getIndex(state.requests, request.id), 1)
		},
	},
	getters: {
		getAllRequests: state => state.requests,
		getRequestById: state => id => state.requests.find(elem => {
			return elem.id == id
		})
  }
}
import buyApi from '../../api/buy'
import getIndex from './utils'

export const moduleBuy = {
	namespaced: true,
	state: {
		buys: [],
	},
	actions: {
		async getBuysAction({commit}) {
			const result = await buyApi.allbuys()
			const data = await result.json()
			commit('getBuysMutation', data)
		},
		async addBuyAction({commit}, buy) {
			const result = await buyApi.addbuy(buy)
			const data = await result.json()
			if (data.ok) {
				commit('addBuyMutation', buy)
			}
		},
		async saveBuyAction({commit}, buy) {
			const result = await buyApi.savebuy(buy)
			const data = await result.json()
			if (data.ok) commit('saveBuyMutation', buy)
		},
		async removeBuyAction({commit}, buy) {
			const result = await buyApi.removebuy(buy)
			const data = await result.json()
			if (data.ok) commit('removeBuyMutation', buy)
		}
	},
	mutations: {
		getBuysMutation(state, buys) {
			state.buys = buys
		},
		addBuyMutation(state, buy) {},
		saveBuyMutation(state, buy) {
			let index = getIndex(state.buys, buy.id)
			state.buys.splice(index, 1, buy)
		},
		removeBuyMutation(state, buy) {
			state.buys.splice(getIndex(state.buys, buy.id), 1)
		},
	},
	getters: {
		getAllBuys: state => state.buys,
		getBuyById: state => id => state.buys.find(elem => {
			return elem.id == id
		}) 
  }
}
import buyApi from '../../api/buy'
import getIndex from './utils'

export const moduleBuy = {
	namespaced: true,
	state: {
		buys: [],
	},
	actions: {
		async getBuysAction({ commit, dispatch }) {
			const result = await buyApi.allbuys()
			const data = await result.json()
			commit('getBuysMutation', data)
		},
		async addBuyAction({ commit, dispatch }, buy) {
			const result = await buyApi.addbuy(buy)
			const data = await result.json()
			if (data.okey) {
				commit('addBuyMutation', buy)
			} else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось оформить покупку, возможно она уже оформлена'
      }, { root: true })
		},
		async saveBuyAction({ commit, dispatch }, buy) {
			const result = await buyApi.savebuy(buy)
			const data = await result.json()
			if (data.okey) commit('saveBuyMutation', buy)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось изменить покупку'
      }, { root: true })
		},
		async removeBuyAction({ commit, dispatch }, buy) {
			const result = await buyApi.removebuy(buy)
			const data = await result.json()
			if (data.okey) commit('removeBuyMutation', buy) 
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось удалить покупка, возможно в ней были дефектные запчасти'
      }, { root: true })
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
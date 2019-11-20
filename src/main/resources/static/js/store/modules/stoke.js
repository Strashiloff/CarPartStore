import stokeApi from '../../api/stoke'

export const moduleStoke = {
	namespaced: true,
	state: {
		stokes: [],
	},
	actions: {
		async getAllStokeAction ({ commit }) {
			const result = await stokeApi.getAllStokes()
			const data = await result.json()
			commit('getAllStokeMutation', data)
		},
		async deleteStokeAction ({ commit, state }, stoke) {
			let st = state.stokes.indexOf(stoke)
			const result = await stokeApi.delStoke(state.stokes[st])
			const data = await result.json()
			console.log(result)
			if (data.ok) {
				commit('deleteStokeMutation', stoke)
			}
		}
	},
	mutations: {
		getAllStokeMutation (state, data) { state.stokes = data },
		deleteStokeMutation (state, stoke) {
			state.stokes.splice(state.stokes.indexOf(stoke), 1)
		},
	},
	getters: {
		getAllStoke: state => state.stokes
	}
}
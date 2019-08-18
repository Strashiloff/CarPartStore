import stokeApi from '../../api/stoke'

export const moduleStoke = {
	namespaced: false,
	state: {
		stokes: [],
	},
	actions: {
		async getAllStokeAction({commit}) {
			const result = await stokeApi.getAllStokes()
			const data = await result.json()
			commit('getAllStokeMutation', data)
		}
	},
	mutations: {
		getAllStokeMutation(state, data) { state.stokes = data },
	},
	getters: {
		getAllStoke: state => state.stokes
	}
}
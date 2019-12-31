import stokeApi from '../../api/stoke'
import getIndex from './utils'

export const moduleStoke = {
	namespaced: true,
	state: {
		stokes: [],
		edit: {
			location: '',
			count: 0,
			id: -1
		}
	},
	actions: {
		async getAllStokeAction ({ commit, dispatch }) {
			const result = await stokeApi.getAllStokes()
			const data = await result.json()
			commit('getAllStokeMutation', data)
		},
		async addStokeAction ({ commit, state }, stoke) {
			const result = await stokeApi.addStoke(stoke)
			const data = await result.json()
			if (data.okey) {
				commit('addStokeMutation', stoke)
			} else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось добавить склад'
      }, { root: true })
		},
		async deleteStokeAction ({ commit, state }, stoke) {
			let st = state.stokes.indexOf(stoke)
			const result = await stokeApi.delStoke(state.stokes[st])
			const data = await result.json()
			if (data.okey) {
				commit('deleteStokeMutation', stoke)
			} else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось удалить склад'
      }, { root: true })
		},
		async saveStokeAction ({ commit, dispatch }, stoke) {
			const result = await stokeApi.saveStoke(stoke)
			const data = await result.json()
			if (data.okey) {
				commit('saveStokeMutation', stoke)
			} else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось сохранить склад'
      }, { root: true })
		},
		setEditAction ({ commit, dispatch }, stoke) { commit('setEditMutation', stoke)}
	},
	mutations: {
		getAllStokeMutation (state, data) { state.stokes = data },
		addStokeMutation (state, data) {
			// state.stokes.push(data)
		},
		saveStokeMutation (state, stoke) {
			let index = getIndex(state.stokes, stoke.id)
			state.stokes.splice(index, 1, stoke)
		},
		deleteStokeMutation (state, stoke) {
			let index = getIndex(state.stokes, stoke.id)
			state.stokes.splice(index, 1)
		},
		setEditMutation (state, stoke) {
			state.edit = stoke
		}
	},
	getters: {
		getAllStoke: state => state.stokes.sort((a, b) => {
			return a.id - b.id
		}),
		getStokeById: state => id => state.stokes.find(elem => {
			return elem.id == id
		}),
		getEdit: state => state.edit
	}
}
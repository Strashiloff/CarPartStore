import getIndex from '../modules/utils'
import typesApi from '../../api/type'

export const moduleTypes = {
	namespaced: true,
	state: {
    types: []
	},
	actions: {
		async setAllTypesAction ({ commit, dispatch }) {
      let result = await typesApi.getAllTypes()
      let data = await result.json()
      commit('setAllTypesMutation', data)
    },
    async addTypeAction ({ commit, dispatch }, type) {
      let result = await typesApi.addType(type)
      let data = await result.json()
      if (data.okey) {
        commit('addTypeMutation', type)
      } else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось добавить тип'
      }, { root: true })
    },
    async saveTypeAction ({ commit, dispatch }, type) {
      let result = await typesApi.saveType(type)
      let data = await result.json()
      if (data.okey) {
        commit('saveTypeMutation', type)
      } else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалость сохранить тип'
      }, { root: true })
    },
    async deleteTypeAction ({ commit, dispatch }, type) {
      let result = await typesApi.delType(type)
      let data = await result.json()
      if (data.okey) {
        commit('deleteTypeMutation', type)
      } else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалость удалить тип'
      }, { root: true })
    }
	},
	mutations: {
		setAllTypesMutation (state, data) {
      state.types = data
    },
    addTypeMutation (state, data) {},
    saveTypeMutation (state, data) {
      let index = getIndex(state.types, data.id)
      state.types.splice(index, 1, data)
    },
    deleteTypeMutation (state, data) {
      let index = getIndex(state.types, data.id)
      state.types.splice(index, 1)
    },
	},
	getters: {
    getAllTypes: state => state.types,
    getTypeById: state => id => state.types[getIndex(state.types, id)]
	}
}
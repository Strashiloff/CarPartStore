import getIndex from '../modules/utils'
import typesApi from '../../api/type'

export const moduleTypes = {
	namespaced: true,
	state: {
    types: []
	},
	actions: {
		async setAllTypesAction ({ commit }) {
      let result = await typesApi.getAllTypes()
      let data = await result.json()
      commit('setAllTypesMutation', data)
    },
    async addTypeAction ({ commit }, type) {
      let result = await typesApi.addType(type)
      let data = await result.json()
      if (data.ok) {
        commit('addTypeMutation', type)
      }
    },
    async saveTypeAction ({ commit }, type) {
      let result = await typesApi.saveType(type)
      let data = await result.json()
      if (data.ok) {
        commit('saveTypeMutation', type)
      }
    },
    async deleteTypeAction ({ commit }, type) {
      let result = await typesApi.delType(type)
      let data = await result.json()
      if (data.ok) {
        commit('deleteTypeMutation', type)
      }
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
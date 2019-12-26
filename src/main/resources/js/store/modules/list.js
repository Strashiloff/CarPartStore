import getIndex from '../modules/utils'
import listsApi from '../../api/list'
import Vue from 'vue'

export const moduleLists = {
	namespaced: true,
	state: {
    lists: [],
    listsCollection: {}
	},
	actions: {
		async setAllListsAction ({ commit }) {
      let result = await listsApi.getAllLists()
      let data = await result.json()
      commit('setAllListsMutation', data)
    },
    async addListAction ({ commit }, list) {
      let result = await listsApi.addList(list)
      let data = await result.json()
      if (data.ok) {
        commit('addListMutation', list)
      }
    },
    async saveListAction ({ commit }, list) {
      let result = await listsApi.saveList(list)
      let data = await result.json()
      if (data.ok) {
        commit('saveListMutation', list)
      }
    },
    async deleteListAction ({ commit }, list) {
      let result = await listsApi.delList(list)
      let data = await result.json()
      if (data.ok) {
        commit('deleteListMutation', list)
      }
    }
	},
	mutations: {
		setAllListsMutation (state, data) {
      state.lists = data
      state.listsCollection = {}
      data.forEach(list => {
        if (state.listsCollection[list.id_stoke]) {
          let array = state.listsCollection[list.id_stoke]
          state.listsCollection[list.id_stoke].push(list)
        } else {
          Vue.set(state.listsCollection, list.id_stoke, [])
          state.listsCollection[list.id_stoke] = new Array()
          state.listsCollection[list.id_stoke].push(list)
        }
      })
    },
    addListMutation (state, data) {},
    saveListMutation (state, data) {
      let index = getIndex(state.lists, data.id)
      state.lists.splice(index, 1, data)
    },
    deleteListMutation (state, data) {
      let index = getIndex(state.lists, data.id)
      state.lists.splice(index, 1)
    },
	},
	getters: {
    getAllLists: state => state.lists,
    getListById: state => id => state.lists[getIndex(state.lists, id)]
	}
}
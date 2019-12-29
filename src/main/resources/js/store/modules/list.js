import getIndex from '../modules/utils'
import listsApi from '../../api/list'
import Vue from 'vue'

export const moduleLists = {
	namespaced: true,
	state: {
    lists: [],
    listsCollection: {},
		edit: {}
	},
	actions: {
		async setAllListsAction ({ commit }) {
      let result = await listsApi.allLists()
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
    },
		setEditItem ({ commit }, position) {
      commit('setEditPositionMutation', position)
    }
	},
	mutations: {
		setAllListsMutation (state, data) {
      state.lists = data
      state.listsCollection = {}
      data.forEach(list => {
        if (state.listsCollection[list.id_request]) {
          let array = state.listsCollection[list.id_request]
          state.listsCollection[list.id_request].push(list)
        } else {
          Vue.set(state.listsCollection, list.id_request, [])
          state.listsCollection[list.id_request] = new Array()
          state.listsCollection[list.id_request].push(list)
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
    setEditPositionMutation (state, data) {
      state.edit = data
    }
	},
	getters: {
    getAllLists: state => state.lists,
    getListById: state => id => state.lists[getIndex(state.lists, id)],
    getListByRequesttId: state => id => {
      return state.listsCollection[id] && Array.from(state.listsCollection[id]).sort((a, b) => { 
        return a.number - b.number 
      }) || []
    },
    getEditList: state => state.edit
	}
}
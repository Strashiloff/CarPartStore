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
		async setAllListsAction ({ commit, dispatch }) {
      let result = await listsApi.allLists()
      let data = await result.json()
      commit('setAllListsMutation', data)
    },
    async addListAction ({ commit, dispatch }, list) {
      let result = await listsApi.addList(list)
      let data = await result.json()
      if (data.okey) {
        commit('addListMutation', list)
      } else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось добавить позицию, возможно запчасть уже есть в заявке'
      }, { root: true })
    },
    async saveListAction ({ commit, dispatch }, list) {
      let result = await listsApi.saveList(list)
      let data = await result.json()
      if (data.okey) {
        commit('saveListMutation', list)
      } else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось изменить позицию'
      }, { root: true })
    },
    async deleteListAction ({ commit, dispatch }, list) {
      let result = await listsApi.delList(list)
      let data = await result.json()
      if (data.okey) {
        commit('deleteListMutation', list)
      } else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось удалить позицию, возможно она содержится в покупке'
      }, { root: true })
    },
		setEditItem ({ commit, dispatch }, position) {
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
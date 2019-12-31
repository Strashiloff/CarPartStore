import sectionApi from '../../api/section'
import getIndex from './utils'
import Vue from 'vue'

export const moduleSection = {
	namespaced: true,
	state: {
    sections: [],
    sectionList: {},
    edit: {
			amount: 0,
      capacity: 0,
      id_stoke: -1,
      id: -1,
      number: 0
		}
	},
	actions: {
		async setAllSectionsAction ({ commit, dispatch }) {
      let result = await sectionApi.getAllSections()
      let data = await result.json()
      commit('setAllSectionsMutation', data)
    },
    async addSectionAction ({ commit, dispatch }, section) {
      let result = await sectionApi.addSection(section)
      let data = await result.json()
      if (data.okey) {
        commit('addSectionMutation', section)
      } else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось добавить полку'
      }, { root: true })
    },
    async saveSectionAction ({ commit, dispatch }, section) {
      let result = await sectionApi.saveSection(section)
      let data = await result.json()
      if (data.okey) {
        commit('saveSectionMutation', section)
      } else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось сохранить полку'
      }, { root: true })
    },
    async deleteSectionAction ({ commit, dispatch }, section) {
      let result = await sectionApi.delSection(section)
      let data = await result.json()
      if (data.okey) {
        commit('deleteSectionMutation', section)
      } else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось удалить полку, возможно на ней лежат запчасти'
      }, { root: true })
    },
    setEditSectionAction ({ commit, dispatch }, section) { commit('setEditSectionMutation', section)}
	},
	mutations: {
		setAllSectionsMutation (state, data) {
      state.sections = data
      state.sectionList = {}
      data.forEach(section => {
        if (state.sectionList[section.id_stoke]) {
          let array = state.sectionList[section.id_stoke]
          state.sectionList[section.id_stoke].push(section)
        } else {
          Vue.set(state.sectionList, section.id_stoke, [])
          state.sectionList[section.id_stoke] = new Array()
          state.sectionList[section.id_stoke].push(section)
        }
      })
    },
    addSectionMutation (state, data) {},
    saveSectionMutation (state, data) {
      let index = getIndex(state.sections, data.id)
      state.sections.splice(index, 1, data)
      index = getIndex(state.sectionList[data.id_stoke], data.id)
      state.sectionList[data.id_stoke].splice(index, 1, data)
    },
    deleteSectionMutation (state, data) {
      let index = state.sections.indexOf(data)
      state.sections.splice(index, 1)
      index = state.sectionList[data.id_stoke].indexOf(data)
      state.sectionList[data.id_stoke].splice(index, 1)
    },
    setEditSectionMutation (state, section) {
			state.edit = section
		}
	},
	getters: {
    getAllSections: state => state.sections,
    getSectionByStokeId: state => id => {
      return state.sectionList[id] && Array.from(state.sectionList[id]).sort((a, b) => { 
        return a.number - b.number 
      }) || []
    },
    getSectionById: state => id => state.sections.length && state.sections[getIndex(state.sections, id)] || null,
    getEditSection: state => state.edit
	}
}
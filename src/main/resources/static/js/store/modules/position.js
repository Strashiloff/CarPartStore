import getIndex from '../modules/utils'
import positionsApi from '../../api/position'
import Vue from 'vue'

export const modulePositions = {
	namespaced: true,
	state: {
    positions: [],
    positionsCollection: {},
    edit: {}
	},
	actions: {
		async setAllPositionsAction ({ commit }) {
      let result = await positionsApi.allPositions()
      let data = await result.json()
      commit('setAllPositionsMutation', data)
    },
    async addPositionAction ({ commit }, position) {
      let result = await positionsApi.addPosition(position)
      let data = await result.json()
      if (data.ok) {
        commit('addPositionMutation', position)
      }
    },
    async savePositionAction ({ commit }, position) {
      let result = await positionsApi.savePosition(position)
      let data = await result.json()
      if (data.ok) {
        commit('savePositionMutation', position)
      }
    },
    async deletePositionAction ({ commit }, position) {
      let result = await positionsApi.delPosition(position)
      let data = await result.json()
      if (data.ok) {
        commit('deletePositionMutation', position)
      }
    },
    setEditItem ({ commit }, position) {
      commit('setEditPositionMutation', position)
    }
	},
	mutations: {
		setAllPositionsMutation (state, data) {
      state.positions = data
      state.positionsCollection = {}
      data.forEach(position => {
        if (state.positionsCollection[position.id_supply]) {
          state.positionsCollection[position.id_supply].push(position)
        } else {
          Vue.set(state.positionsCollection, position.id_supply, [])
          state.positionsCollection[position.id_supply] = new Array()
          state.positionsCollection[position.id_supply].push(position)
        }
      })
    },
    addPositionMutation (state, data) {},
    savePositionMutation (state, data) {
      let index = getIndex(state.positions, data.id)
      state.positions.splice(index, 1, data)
      index = getIndex(state.positionsCollection[data.id_supply], data.id)
      state.positionsCollection[data.id_supply].splice(index, 1, data)
    },
    deletePositionMutation (state, data) {
      let index = getIndex(state.positions, data.id)
      state.positions.splice(index, 1)
      index = getIndex(state.sectionList[data.id_supply], data.id)
      state.positionsCollection[data.id_supply].splice(index, 1)
    },
    setEditPositionMutation (state, data) {
      state.edit = data
    }
	},
	getters: {
    getAllPositions: state => state.positions,
    getPositionById: state => id => state.positions.length && state.positions[getIndex(state.positions, id)],
    getPositionBySupplyId: state => id => {
      return state.positionsCollection[id] && Array.from(state.positionsCollection[id]).sort((a, b) => { 
        return a.number - b.number 
      }) || []
    },
    getEditItem: state => state.edit
	}
}
import getIndex from '../modules/utils'
import positionsApi from '../../api/position'
import Vue from 'vue'

export const modulePositions = {
	namespaced: true,
	state: {
    positions: [],
    positionsCollection: {},
    positionSparePart: {},
    edit: {}
	},
	actions: {
		async setAllPositionsAction ({ commit, dispatch }) {
      let result = await positionsApi.allPositions()
      let data = await result.json()
      commit('setAllPositionsMutation', data)
    },
    async addPositionAction ({ commit, dispatch }, position) {
      const result = await positionsApi.addPosition(position)
      const data = await result.json()
      if (data.okey) {
        commit('addPositionMutation', position)
      } else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось добавление позиции, возможно запчасть уже есть в поставки'
      }, { root: true })
    },
    async savePositionAction ({ commit, dispatch }, position) {
      const result = await positionsApi.savePosition(position)
      const data = await result.json()
      if (data.okey) {
        commit('savePositionMutation', position)
      } else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось изменить позицию'
      }, { root: true })
    },
    async deletePositionAction ({ commit, dispatch }, position) {
      const result = await positionsApi.removePosition(position)
      const data = await result.json()
      if (data.okey) {
        commit('deletePositionMutation', position)
      } else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Не удалось удалить позицию, возможно она содержится в заявках'
      }, { root: true })
    },
    setEditItem ({ commit, dispatch }, position) {
      commit('setEditPositionMutation', position)
    }
	},
	mutations: {
		setAllPositionsMutation (state, data) {
      state.positions = data
      state.positionsCollection = {}
      state.positionSparePart = {}
      data.forEach(position => {
        if (state.positionSparePart[position.id_spare_part]) {
          state.positionSparePart[position.id_spare_part].push(position)
        } else {
          Vue.set(state.positionSparePart, position.id_spare_part, [])
          state.positionSparePart[position.id_spare_part] = new Array()
          state.positionSparePart[position.id_spare_part].push(position)
        }
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
      index = getIndex(state.positionsCollection[data.id_supply], data.id)
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
    getPositionBySparePartId: state => id => {
      return state.positionSparePart[id] && Array.from(state.positionSparePart[id]).sort((a, b) => { 
        return a.number - b.number 
      }) || []
    },
    getEditItem: state => state.edit
	}
}
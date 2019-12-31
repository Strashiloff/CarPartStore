import categoriesApi from '../../api/category'
import getIndex from './utils'

export const moduleCategory = {
	namespaced: true,
	state: {
		categories: [],
	},
	actions: {
		async getCategoriesAction({ commit, dispatch }) {
			const result = await categoriesApi.allCategories()
			const data = await result.json()
			commit('getCategoriesMutation', data)
		},
		async addCategoryAction({ commit, dispatch }, category) {
			const result = await categoriesApi.addCategory(category)
			const data = await result.json()
			if (data.okey) {
				commit('addCategoryMutation', category)
			} else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка добавления категории'
      }, { root: true })
		},
		async saveCategoryAction({ commit, dispatch }, category) {
			const result = await categoriesApi.saveCategory(category)
			const data = await result.json()
			if (data.okey) commit('saveCategoryMutation', category)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка сохранения категории'
      }, { root: true })
		},
		async removeCategoryAction({ commit, dispatch }, category) {
			const result = await categoriesApi.removeCategory(category)
			const data = await result.json()
			if (data.okey) commit('removeCategoryMutation', category)
			else dispatch('app/setSnackbar', {
        snackbar: true,
        text: 'Ошибка удаления категории, возможно есть поставщик в данной категории'
      }, { root: true })
		}
	},
	mutations: {
		getCategoriesMutation(state, categories) {
			state.categories = categories
		},
		addCategoryMutation(state, category) {},
		saveCategoryMutation(state, category) {
			let index = getIndex(state.categories, category.id)
			state.categories.splice(index, 1, category)
		},
		removeCategoryMutation(state, category) {
			state.categories.splice(getIndex(state.categories, category.id), 1)
		},
	},
	getters: {
    getAllCategories: state => state.categories,
    getCategoryById: state => id => state.categories.find(elem => {
			return elem.id == id
		})
  }
}
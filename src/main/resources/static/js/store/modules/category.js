import categoriesApi from '../../api/category'
import getIndex from './utils'

export const moduleCategory = {
	namespaced: true,
	state: {
		categories: [],
	},
	actions: {
		async getCategoriesAction({commit}) {
			const result = await categoriesApi.allCategories()
			const data = await result.json()
			commit('getCategoriesMutation', data)
		},
		async addCategoryAction({commit}, category) {
			const result = await categoriesApi.addCategory(category)
			const data = await result.json()
			if (data.ok) {
				commit('addCategoryMutation', category)
			}
		},
		async saveCategoryAction({commit}, category) {
			const result = await categoriesApi.saveCategory(category)
			const data = await result.json()
			if (data.ok) commit('saveCategoryMutation', category)
		},
		async removeCategoryAction({commit}, category) {
			const result = await categoriesApi.removeCategory(category)
			const data = await result.json()
			if (data.ok) commit('removeCategoryMutation', category)
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
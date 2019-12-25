import Vue from 'vue'

const categories = Vue.resource('/category')
const deleteCategory = Vue.resource('/category/delete')

export default {
	allCategories: () => categories.get(),
	addCategory: category => categories.save(category),
	saveCategory: category => categories.update(category),
	removeCategory: category => deleteCategory.save(category)
}
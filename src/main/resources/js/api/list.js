import Vue from 'vue'

const lists = Vue.resource('/list')
const deleteList = Vue.resource('/list/delete')

export default {
	allLists: () => lists.get(),
	addList: list => lists.save(list),
	saveList: list => lists.update(list),
	removeList: list => deleteList.save(list)
}
import { eventBus } from "../../main"
import usersApi from "../../api/users"
import getIndex from './utils'

// function getIndex(list, id) {
// 	for (let i = 0; i < list.length; i++) {
// 		if (list[i].id === id) return i;
// 	}
// 	return -1;
// }

export const moduleUsers = {
	namespaced: true,
	state: {
		users: [],
		errorAddUser: {
			check: false,
			error: ''
		}
	},
	actions: {
		async getUsersAction({ commit, dispatch }) {
			const result = await usersApi.allUsers()
			const data = await result.json()
			commit('getUsersMutation', data)
		},
		async addUserAction({ commit, dispatch }, user) {
			const result = await usersApi.addUser(user)
			const data = await result.json()
			commit('addUserMutation', data)
		},
		async removeUserAction({ commit, dispatch }, user) {
			const result = await usersApi.deleteUser(user.id)
			if (result.ok) {
				commit('removeUserMutation', user)
			}
		},
		async errorAction({ commit, dispatch }, error) {
			commit('errorMutation', error)
		},
		async updateUserAction({ commit, dispatch }, user) {
			const result = await usersApi.updateUser(user)
			if (result.ok) commit('updateUserMutation', user)
		},
	},
	mutations: {
		getUsersMutation(state, users) {
			state.users = users
		},

		addUserMutation(state, user) {
			if (user.id !== -1) {
				state.users.push(user)
				state.errorAddUser = {
					check: false,
					error: 'Пользоваетель добавлен'
				}
				eventBus.$emit('error', {
					check: false,
					error: 'Пользоваетель добавлен'
				})
			} else {
				eventBus.$emit('error', {
					check: true,
					error: 'Пользователь с логином ' + user.username + ' уже существует'
				})
				state.errorAddUser = {
					check: true,
					error: 'Пользователь с логином ' + user.username + ' уже существует'
				} 
			}
		},
		errorMutation(state, error) {
			state.errorAddUser = error
		},
		updateUserMutation(state, user) {
			let index = getIndex(state.users, user.id)
			state.users.splice(index, 1, user)
		},
		removeUserMutation(state, user) {
			state.users.splice(state.users.indexOf(user), 1)
		}
	},
	getters: {
		getErrorAddUser: state => state.errorAddUser,
		getUsers: state => state.users,
		getUserById: state => id => state.users.find(elem => {
			return elem.id == id
		})
	}
}
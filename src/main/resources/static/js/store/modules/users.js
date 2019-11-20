import {eventBus} from "../../main";
import usersApi from "../../api/users";

function getIndex(list, id) {
	for (let i = 0; i < list.length; i++) {
		if (list[i].id === id) return i;
	}
	return -1;
}

export const moduleUsers = {
	namespaced: true,
	state: {
		users: [],
		errorAddUser: []
	},
	actions: {
		async getUsersAction({commit}) {
			const result = await usersApi.allUsers()
			const data = await result.json()
			commit('getUsersMutation', data)
		},
		async addUserAction({commit}, user) {
			const result = await usersApi.addUser(user)
			const data = await result.json()
			commit('addUserMutation', data, user)
		},
		async removeUserAction({commit}, user) {
			const result = await usersApi.deleteUser(user.id)
			if (result.ok) {
				commit('removeUserMutation', user)
			}
		},
		async errorAction({commit}, error) {
			commit('errorMutation', error)
		},
		async updateUserAction({commit}, user) {
			const result = await usersApi.updateUser(user)
			if (result.ok) commit('updateUserMutation', user)
		},
	},
	mutations: {
		getUsersMutation(state, users) {
			state.users = users
		},

		addUserMutation(state, error, user) {
			state.errorAddUser = error
			if (!error.check) state.users.push(user)
			eventBus.$emit('error', error)
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
		getUsers: state => state.users
	}
}
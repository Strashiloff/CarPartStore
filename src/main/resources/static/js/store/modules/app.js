import usersApi from "../../api/users";

export const moduleApp = {
	namespaced: false,
	state: {
		roles: ['ADMIN', 'USER'],
		profile: [],
		admin: false
	},
	actions: {
		async getCurrentUserAction({commit}) {
			const result = await usersApi.currentUser()
			const data = await result.json()
			commit('getCurrentUserMutation', data)
		},
	},
	mutations: {
		getCurrentUserMutation(state, user) {
			state.profile = user
			if (user.roles.includes('ADMIN')) state.admin = true
		},
	},
	getters: {
		getCurrentUser: state => state.profile,
		getIsAdmin: state => state.admin,
		getRoles: state => state.roles
	}
}
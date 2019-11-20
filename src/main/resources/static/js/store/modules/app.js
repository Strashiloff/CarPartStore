import usersApi from "../../api/users";

export const moduleApp = {
	namespaced: true,
	state: {
		roles: ['ADMIN', 'USER'],
		profile: [],
		admin: false,
		theme: true
	},
	actions: {
		async getCurrentUserAction({commit}) {
			const result = await usersApi.currentUser()
			const data = await result.json()
			commit('getCurrentUserMutation', data)
		},
		setTheme: ({ commit }, payload) => commit('setThemeMutation', payload)
	},
	mutations: {
		getCurrentUserMutation(state, user) {
			state.profile = user
//			this.$user.set({ role: user})
			if (user.roles.includes('ADMIN')) state.admin = true
		},
		setThemeMutation: (state, payload) => { state.theme = payload}
  },
  getters: {
    getCurrentUser: state => state.profile,
    getIsAdmin: state => state.admin,
		getRoles: state => state.roles,
		getTheme: state => state.theme
  }
}

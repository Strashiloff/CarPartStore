import usersApi from "../../api/users";

export const moduleApp = {
	namespaced: true,
	state: {
		roles: ['GENERAL_ADMIN','ADMIN', 'USER'],
		profile: [],
		admin: false,
		general: false,
		theme: true,
		drawer: false,
		editItem: {}
	},
	actions: {
		async getCurrentUserAction({commit}) {
			const result = await usersApi.currentUser()
			const data = await result.json()
			commit('getCurrentUserMutation', data)
		},
		setTheme: ({ commit }, payload) => commit('setThemeMutation', payload),
		setDrawer: ({ commit }, payload) => commit('setDrawerMutation'),
		setEditItem: ({ commit }, payload) => commit('setEditItemMutation', payload),
	},
	mutations: {
		getCurrentUserMutation(state, user) {
			state.profile = user
//			this.$user.set({ role: user})
			if (user.roles.includes('ADMIN')) state.admin = true
			if (user.roles.includes('GENERAL_ADMIN')) {
				state.general = true
				state.admin = true
			}
		},
		setThemeMutation: (state, payload) => { state.theme = payload },
		setDrawerMutation: (state) => { state.drawer = ! state.drawer },
		setEditItemMutation: (state, payload) => { state.editItem = payload },
  },
  getters: {
		getCurrentUser: state => state.profile,
		getUserNameShort: state => { 
			return state.profile.name && state.profile.name[0] + state.profile.surname[0]
		},
		getUserNameFull: state => state.profile.name + ' ' + state.profile.surname,
		getIsAdmin: state => state.admin,
		getIsGeneral: state => state.general,
		getRoles: state => state.roles,
		getTheme: state => state.theme,
		getDrawer: state => state.drawer,
		getEditItem: state => state.editItem
  }
}

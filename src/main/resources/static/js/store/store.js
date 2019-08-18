import Vue from 'vue'
import Vuex from 'vuex'

import { moduleApp } from './modules/app'
import { moduleUsers } from './modules/users'
import { modulePost } from './modules/post'
import { moduleStoke } from './modules/stoke'

Vue.use(Vuex)

export default new Vuex.Store({
	modules: {
		app: moduleApp,
		users: moduleUsers,
		posts: modulePost,
		stoke: moduleStoke
	}
})
import Vue from 'vue'
import Vuex from 'vuex'

//modules
import { moduleApp } from './modules/app'
import { moduleUsers } from './modules/users'
import { modulePost } from './modules/post'
import { moduleStoke } from './modules/stoke'
import { moduleSection } from './modules/section'
import { moduleTypes } from './modules/type'
import { moduleSpareParts } from './modules/spare_part'
import { moduleCountry } from './modules/country'
import { moduleCategory } from './modules/category'
import { modulePurveyor } from './modules/purveyor'
import { moduleSupply } from './modules/supply'
import { modulePositions } from './modules/position'
import { moduleContract } from './modules/contract'


//plugins
import rolesPlugin from './plugins/rolesPlugin.js'
import logger from './plugins/index.js'
import updatePlugin from './plugins/updateData.js'

Vue.use(Vuex)

export default new Vuex.Store({
	modules: {
		app: moduleApp,
		users: moduleUsers,
		posts: modulePost,
		stoke: moduleStoke,
		section: moduleSection,
		type: moduleTypes,
		sparePart: moduleSpareParts,
		country: moduleCountry,
		category: moduleCategory,
		purveyor: modulePurveyor,
		supply: moduleSupply,
		position: modulePositions,
		contract: moduleContract
	},
	plugins: [
		rolesPlugin(),
		logger(),
		updatePlugin()
	]
})
import Vue from 'vue'
import router from 'router/router'
import Title from './pages/Title.vue'
import '@babel/polyfill'
import 'api/recource'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'vue-search-select/dist/VueSearchSelect.css'
import VueValidate from 'vee-validate'
import store from 'store/store'
import VueRouterUsersRoles from 'vue-router-user-roles'
import moment from 'moment'

Vue.use(Vuetify)
Vue.use(VueValidate)
Vue.use(VueRouterUsersRoles, { router })
Vue.http.headers.common['X-CSRF-TOKEN'] = token
export const eventBus = new Vue()
global['store']=store
global['moment']=moment


window.vm = new Vue({
	el: '#app',
	router,
	store,
	render: h => h(Title)
});
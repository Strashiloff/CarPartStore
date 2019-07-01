import Vue from 'vue'
import router from 'router/router'
import Title from './pages/Title.vue'
import '@babel/polyfill'
import 'api/recource'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import VueValidate from 'vee-validate'
import store from 'store/store'
Vue.use(Vuetify)
Vue.use(VueValidate)
Vue.http.headers.common['X-CSRF-TOKEN'] = token
export const eventBus = new Vue()

new Vue({
    el: '#app',
    router,
    store,
    render: h => h(Title)
});
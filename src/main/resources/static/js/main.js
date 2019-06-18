import Vue from 'vue'
import router from 'router/router'
import Title from './pages/Title.vue'
import VueResources from 'vue-resource'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import VueValidate from 'vee-validate'

Vue.use(VueResources)
Vue.use(Vuetify)
Vue.use(VueValidate)
Vue.http.headers.common['X-CSRF-TOKEN'] = token

new Vue({
    el: '#app',
    router,
    render: h => h(Title)
});
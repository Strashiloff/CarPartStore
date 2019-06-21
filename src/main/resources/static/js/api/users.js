import Vue from 'vue'

const users = Vue.resource('/user')
const registration = Vue.resource('/registration')
const currUser = Vue.resource('/user/current')

export default {
    allusers: () => users.get(),
    currentUser: () => currUser.get(),
    addUser: user => registration.save(user)
}
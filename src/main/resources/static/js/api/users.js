import Vue from 'vue'

const users = Vue.resource('/user/{id}')
const registration = Vue.resource('/registration')
const currUser = Vue.resource('/user/current')

export default {
    allUsers: () => users.get(),
    currentUser: () => currUser.get(),
    addUser: user => registration.save(user),
    deleteUser: id => users.remove({id: id}),
    updateUser: user => users.update({id: user.id}, user)
}
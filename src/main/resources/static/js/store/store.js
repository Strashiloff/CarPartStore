import Vue from 'vue'
import Vuex from 'vuex'
import usersApi from 'api/users'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        roles: ['ADMIN','USER'],
        users:[],
        profile: [],
        admin: false,
        errorAddUser: []
    },
    getters:{

    },
    mutations:{
        getUsersMutation(state, users){
            state.users = users
        },
        getCurrentUserMutation(state, user){
            state.profile = user
            if(user.roles.includes('ADMIN')) state.admin = true
        },
        addUserMutation(state, error){
            state.errorAddUser = error
        }
    },
    actions:{
        async getUsersAction({commit}){
            const result = await usersApi.allusers()
            const data = await result.json()
            commit('getUsersMutation', data)
        },
        async getCurrentUserAction({commit}){
            const result = await usersApi.currentUser()
            const data = await result.json()
            commit('getCurrentUserMutation', data)
        },
        async addUserAction({commit}, user){
            const result = await usersApi.addUser(user)
            const data = await result.json()
            commit('addUserMutation', data)
        }
    }
})
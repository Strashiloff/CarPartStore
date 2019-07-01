import Vue from 'vue'
import Vuex from 'vuex'
import usersApi from 'api/users'
import postsApi from 'api/post'
import {eventBus} from "main";


Vue.use(Vuex)

function getIndex(list, id) {
    for (var i=0; i<list.length; i++){
        if (list[i].id === id) return i;
    }
    return -1;
}

export default new Vuex.Store({
    state: {
        roles: ['ADMIN','USER'],
        posts: [],
        users:[],
        profile: [],
        admin: true,
        errorAddUser: []
    },
    getters:{

    },
    mutations:{
        getUsersMutation(state, users){
            state.users = users
        },
        getPostsMutation(state, posts){
            state.posts = posts
        },
        addPostMutation(state, post){
            state.posts = [
              ...state.posts,
              post
            ]
        },
        removePostAction(state, post){
            state.posts.splice(state.posts.indexOf(post), 1)
        },
        getCurrentUserMutation(state, user){
            state.profile = user
            if(user.roles.includes('ADMIN')) state.admin = true
        },
        addUserMutation(state, error){
            state.errorAddUser = error
            eventBus.$emit('error', error)
        },
        errorMutation(state, error){
            state.errorAddUser = error
        },
        updateUserMutation(state, user){
            var index = getIndex(state.users, user.id)
            state.users.splice(index,1,user)
        },
        removeUserMutation(state, user){
            state.users.splice(state.users.indexOf(user),1)
        }
    },
    actions:{
        async getUsersAction({commit}){
            const result = await usersApi.allUsers()
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
        },
        async removeUserAction({commit}, user){
            const result = await usersApi.deleteUser(user.id)
            if(result.ok) {
                commit('removeUserMutation', user)
            }
        },
        async errorAction({commit}, error){
            commit('errorMutation', error)
        },
        async updateUserAction({commit}, user){
            const result = await usersApi.updateUser(user)
            if(result.ok) commit('updateUserMutation', user)
        },
        async getPostsAction({commit}){
            const result = await postsApi.allPosts()
            const data = await result.json()
            commit('getPostsMutation', data)
        },
        async addPostAction({commit}, post){
            const result = await postsApi.addPost(post)
            if(result != null){
                const data = await result.json()
                commit('addPostMutation', data)
            }
        },
        async removePostAction({commit}, post){
            const result = await postsApi.removePost(post.id)
            if(result.ok) commit('addPostMutation', post)

        }
    }
})
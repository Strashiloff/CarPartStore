import postsApi from "../../api/post"
import getIndex from './utils'

export const modulePost = {
	namespaced: true,
	state: {
		posts: [],
	},
	actions: {
		async getPostsAction({ commit, dispatch }) {
			const result = await postsApi.allPosts()
			const data = await result.json()
			commit('getPostsMutation', data)
		},
		async addPostAction({ commit, dispatch }, post) {
			const result = await postsApi.addPost(post)
			if (result != null) {
				const data = await result.json()
				commit('addPostMutation', data)
			}
		},
		async savePostAction({ commit, dispatch }, post) {
			const result = await postsApi.savePost(post)
			if (result.ok) {
				const data = await result.json()
				commit('savePostMutation', data)
			}
		},
		async removePostAction({ commit, dispatch }, post) {
			const result = await postsApi.removePost(post.id)
			if (result.ok) commit('removePostMutation', post)
		}
	},
	mutations: {
		getPostsMutation(state, posts) {
			state.posts = posts
		},
		addPostMutation(state, post) {
			state.posts = [
				...state.posts,
				post
			]
		},
		savePostMutation(state, post) {
			let index = getIndex(state.posts, post.id)
			state.posts.splice(index, 1, post)
		},
		removePostMutation(state, post) {
			state.posts.splice(state.posts.indexOf(post), 1)
		},
	},
	getters: {
		getAllPosts: state => state.posts
	}
}
import postsApi from "../../api/post"

export const modulePost = {
	namespaced: true,
	state: {
		posts: [],
	},
	actions: {
		async getPostsAction({commit}) {
			const result = await postsApi.allPosts()
			const data = await result.json()
			commit('getPostsMutation', data)
		},
		async addPostAction({commit}, post) {
			const result = await postsApi.addPost(post)
			if (result != null) {
				const data = await result.json()
				commit('addPostMutation', data)
			}
		},
		async removePostAction({commit}, post) {
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
		removePostMutation(state, post) {
			state.posts.splice(state.posts.indexOf(post), 1)
		},
	},
	getters: {
		getAllPosts: state => state.posts
	}
}
import Vue from 'vue'

const posts = Vue.resource('/post/{id}')

export default {
	allPosts: () => posts.get(),
	addPost: post => posts.save(post),
	savePost: post => posts.update({id: post.id}, post),
	removePost: id => posts.remove({id: id})
}
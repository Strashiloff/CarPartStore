import Vue from 'vue'

const posts = Vue.resource('/post/{id}')

export default {
    allPosts: () => posts.get(),
    addPost: post => posts.save(post),
    removePost: id => post.remove({id: id})
}
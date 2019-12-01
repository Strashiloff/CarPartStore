<template>
  <v-container grid-list-md>
    <v-layout align-space-around column wrap reverse>
      <v-flex>
        <v-card>
          <v-card-title class="display-1">
            Должности
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-text-field
                v-model="search"
                append-icon="search"
                lable="Search"
                single-line
                hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
              :headers="headers"
              :items="getAllPosts"
              :search="search"
          >
            <template v-slot:items="props">
              <td class="text-xs-left title"><i>{{props.item.id}}</i></td>
              <td class="text-xs-left title">{{props.item.post}}</td>
              <td class="text-xs-center title">
                <v-btn icon @click="editPost(props.item)">
                  <v-icon color="orange">edit</v-icon>
                </v-btn>
                <v-btn @click="removePost(props.item)" :disabled="dialog" icon>
                  <v-icon color="error">delete</v-icon>
                </v-btn>
              </td>
            </template>
            <template v-slot:no-results>
              <v-alert :value="true" color="error" icon="warning"></v-alert>
            </template>
          </v-data-table>
          <asc-dialog
            :dialog="dialog"
            :text="text"
            @dialog="remove($event)"
          >
          </asc-dialog>
          <v-card-text style="color: red">If the position is tied to a user, it will not be deleted.</v-card-text>
        </v-card>
      </v-flex>
      <v-flex>
        <v-card class="pa-1">
          <v-card-title class="display-1">Добавление должности</v-card-title>
          <v-card-text>
            <v-text-field
              label="Название"
              v-model="newPost.post"
              :rules="[rules.fill]"
            ></v-text-field>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="indigo" @click="addPost">Добавить</v-btn>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
	import AscDialog from 'components/AskDialog.vue'
	import { mapActions, mapGetters } from 'vuex'
	import { eventBus } from 'main';

	export default {
		name: "Post",
		components: {
			AscDialog
		},
		data() {
			return {
				headers: [
					{text: 'ID', class: 'title', value: 'id'},
          {text: 'Position', class: 'title', value: 'post'},
          {
						text: 'Редактировать',
						value: '',
            class: 'title',
            align: 'center',
						sortable: false
					}
				],
				search: '',
				text: 'You want to delete this position?',
				newPost: {post: ''},
				dialog: false,
				post: {post: ''},
				rules: {
					fill: v => v !== '' || 'Fill in the position',
				}
			}
		},
		methods: {
			...mapActions('posts', ['addPostAction', 'removePostAction']),
			editPost(post) {
				this.post = post
			},
			removePost(post) {
				this.post = post
				this.dialog = true
			},
			addPost() {
				if (this.newPost !== '' && this.getAllPosts.indexOf(this.newPost) === -1) {
					this.addPostAction(this.newPost)
					this.newPost = {post: ''}
				}
      },
      remove (ok) {
        this.dialog = false
				if (ok) {
					this.removePostAction(this.post)
				}
				this.post = {post: ''}
      }
		},
		computed: {
      ...mapGetters('posts', ['getAllPosts'])
		}
	}
</script>

<style scoped>

</style>
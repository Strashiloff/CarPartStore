<template>
  <v-card class="mt-2 ml-2 mr-2">
    <v-card-title class="display-1">
      Users
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
        :items="getUsers"
        :search="search"
    >
      <template v-slot:items="props">
        <td class="text-xs-left"><i>{{props.item.id}}</i></td>
        <td class="text-xs-left">{{props.item.username.toString()}}</td>
        <td class="text-xs-left">{{props.item.name}}</td>
        <td class="text-xs-left">{{props.item.surname}}</td>
        <td class="text-xs-left">{{props.item.position.post}}</td>
        <td class="text-xs-left"><i v-for="role in props.item.roles">{{role + ' '}}</i></td>
        <v-btn icon @click="editUser(props.item)" :disabled="dialog2">
          <v-icon>edit</v-icon>
        </v-btn>
        <v-btn @click="removeUser(props.item)" :disabled="dialog" icon>
          <v-icon>delete</v-icon>
        </v-btn>
      </template>
      <template v-slot:no-results>
        <v-alert :value="true" color="error" icon="warning"></v-alert>
      </template>
    </v-data-table>
    <v-dialog persistent v-model="dialog2" max-width="400">
      <v-card style="padding: 50px">
        <user-dialog :userFrom="user" :mode="true" :editMethod="updateUser"></user-dialog>
      </v-card>
    </v-dialog>
    <ask-dialog :dialog="dialog" :text="text"></ask-dialog>
  </v-card>
</template>

<script>
	import { mapActions, mapGetters } from 'vuex'
	import AskDialog from 'components/AskDialog.vue'
	import UserDialog from 'components/UserDialog.vue'
	import { eventBus } from 'main';

	export default {
		name: "Users",
		components: {AskDialog, UserDialog},
		data: function () {
			return {
				headers: [
					{text: 'ID', value: 'id'},
					{text: 'Login', value: 'username'},
					{text: 'Name', value: 'name'},
					{text: 'Surname', value: 'surname'},
					{text: 'Position', value: 'position.post'},
					{text: 'System role', value: 'roles'},
					{text: 'Edit', value: ''}
				],
				search: '',
				text: 'You want to delete this user?',
				dialog: false,
				dialog2: false,
				user: {
					username: '',
					name: '',
					surname: '',
					password: '',
					position: {post: ''},
					roles: ['USER']
				}
			}
		},
		computed: {
      ...mapGetters(['getUsers'])
		},
		watch: {
			search() {
				this.search.replace('@', '')
			}
		},
		methods: {
			...mapActions(['getUsersAction', 'removeUserAction', 'updateUserAction']),
			removeUser(data) {
				this.user = data
				this.dialog = true
			},
			editUser(data) {
				this.user = Object.assign({}, data);
				setTimeout(() => this.dialog2 = true, 100)
			},
			onKeydown(e) {
				if (e.keyCode === 27) {
					this.user = {
						username: '',
						name: '',
						surname: '',
						password: '',
						position: {post: ''},
						roles: ['USER']
					}
				}
			},
			updateUser(upUser) {
				eventBus.$emit('error', {check: false})
				this.updateUserAction(upUser)
			}
		},
		mounted() {
			eventBus.$on('dialog', (ok) => {
				this.dialog = false
				if (ok) {
					this.removeUserAction(this.user)
				}
				this.user = {
					username: '',
					name: '',
					surname: '',
					password: '',
					position: {post: ''},
					roles: ['USER']
				}
			})
			eventBus.$on('dialog2', (ok) => {
				this.dialog2 = false
				this.user = {
					username: '',
					name: '',
					surname: '',
					password: '',
					position: {post: ''},
					roles: ['USER']
				}
			})
		},
		created() {
			this.dialog = false
			this.dialog2 = false
		}
	}
</script>

<style scoped>

</style>
<template>
  <v-card class="mt-2 ml-2 mr-2">
    <v-card-title class="display-1">
      Пользователи
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="search"
          label="Поиск"
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
        <td class="text-xs-left subtitle-1"><i>{{props.item.id}}</i></td>
        <td class="text-xs-center title">{{props.item.username}}</td>
        <td class="text-xs-center title">{{props.item.name}}</td>
        <td class="text-xs-center title">{{props.item.surname}}</td>
        <td class="text-xs-center title">{{props.item.position.post}}</td>
        <td class="text-xs-center title"><i v-bind:key="role" v-for="role in props.item.roles">{{role + ' '}}</i></td>
        <td class="text-xs-center title">
					<v-btn icon @click="editUser(props.item)" :disabled="dialog2">
						<v-icon>edit</v-icon>
					</v-btn>
					<v-btn @click="removeUser(props.item)" :disabled="dialog" icon>
						<v-icon>delete</v-icon>
					</v-btn>
				</td>
      </template>
      <template v-slot:no-results>
        <v-alert :value="true" color="error" icon="warning">Такого пользователя нет</v-alert>
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
					{
						text: 'ID',
						class: 'title',
						value: 'id'
					},
					{
						text: 'Логин',
						value: 'username',
						class: 'title',
						align: 'center'
					},
					{
						text: 'Имя',
						value: 'name',
						class: 'title',
						align: 'center'
					},
					{
						text: 'Фамилия',
						value: 'surname',
						class: 'title',
						align: 'center'
					},
					{
						text: 'Должность',
						value: 'position.post',
						class: 'title',
						align: 'center'
					},
					{
						text: 'Системная роль',
						value: 'roles',
						class: 'title',
						align: 'center'
					},
					{
						text: 'Редактировать',
						value: '',
						align: 'center',
						class: 'title',
						sortable: false
					}
				],
				search: '',
				text: 'Вы точно хотите удалить этого пользователя?',
				dialog: false,
				dialog2: false,
				user: {
					username: '',
					name: '',
					surname: '',
					password: '',
					position: { post: '' },
					roles: ['USER']
				}
			}
		},
		computed: {
      ...mapGetters('users', ['getUsers'])
		},
		watch: {
			search() {
				this.search.replace('@', '')
			}
		},
		methods: {
			...mapActions('users', ['getUsersAction', 'removeUserAction', 'updateUserAction']),
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
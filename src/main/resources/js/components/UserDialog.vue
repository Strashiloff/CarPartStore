<template>
  <div>
    <v-text-field data-vv-name="name" :disabled="displayRoles" :rules="[rules.fill]" clearable v-model="userFrom.username" autofocus required label="Логин"
                  v-validate="'required|max:20'" :counter="20"></v-text-field>
    <label v-if="!profile" for="password">Пароль меньше 6 символов не сохраняется (Если не хотите изменять пароль, не заполняйте)</label>
		<v-text-field oninput="this.value = this.value.replace(' ', '')"
                  v-model="password"
                  :append-icon="show1 ? 'visibility' : 'visibility_off'"
                  :rules="[rules.min]"
                  :type="show1 ? 'text' : 'password'"
                  name="password"
                  label="Пароль"
                  hint="Не менее 6 символов"
                  counter
									:disabled="displayRoles"
                  @click:append="show1 = !show1"
    ></v-text-field>
    <v-text-field :rules="[rules.fill]" :disabled="displayRoles" clearable v-model="userFrom.name" :type="'text'" data-vv-name="name" name="name"
                  label="Имя"></v-text-field>
    <v-text-field :rules="[rules.fill]" :disabled="displayRoles" clearable v-model="userFrom.surname" :type="'text'" data-vv-name="surname"
                  name="surname" label="Фамилия"></v-text-field>
    <v-select v-if="getIsAdmin" v-model="roles = userFrom.roles" :disabled="displayRoles" :items="getRols" attach label="Системные роль:" multiple></v-select>
    <v-select :disabled="displayRoles" :search="search" v-model="post = userFrom.position" :items="getAllPosts" return-object
              item-text="post" label="Должность"></v-select>
    <v-alert height="70px" class="text-xs-center body-1 error" v-show="getErrorAddUser.check">
      {{getErrorAddUser.error}}
    </v-alert>
		<v-alert height="70px" v-show="!getErrorAddUser.check && getErrorAddUser.error !== ''" class="text-xs-center body-1 success">
			{{getErrorAddUser.error}}
		</v-alert>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn v-if="mode" @click="Canceled">Отмена</v-btn>
      <v-btn @click="changeUser" color="indigo">Сохранить</v-btn>
    </v-card-actions>
  </div>
</template>

<script>
	import { mapActions, mapGetters} from 'vuex'
	import { eventBus } from 'main';

	export default {
		props: ['userFrom', 'editMethod', 'mode', 'profile'],
		name: "UserDialog",
		data() {
			return {
				confPas: '',
				password: '',
				text: [],
				post: {},
				roles: [],
				show1: false,
				enter: false,
				search: '',
				rules: {
					min: v => v.length >= 6 || 'Min 6 characters',
					pas: v => v.toString() === this.password.toString() || 'Passwords don\'t match',
					fill: v => v !== null || 'Fill in the field',
				}
			}
		},
		watch: {
			confPas() {
				if (!this.getIsAdmin) this.enter = this.password !== this.confPas
			},
			password() {
				this.enter = this.userFrom.password.length < 6
			},
			roles (value) {
				if (value.length == 0) {
					this.roles.push('USER')
				}
			}
		},
		methods: {
			...mapActions('users', ['addUserAction', 'errorAction']),
			changeUser() {
				this.userFrom.password = this.password
				var user = this.userFrom
				user.position = this.post
				if (user.username !== '' && user.name !== '' && user.surname !== '') {
					user.username.replace(' ', '_')
					user.name.replace(' ', '_')
					user.surname.replace(' ', '_')
					this.editMethod(user)
				} else this.errorAction({check: true, error: 'Fill in all the fields!'})
			},
			Canceled() {
				this.confPas = '',
				this.password = '',
				eventBus.$emit('dialog2', false)
			}
		},
		computed: {
			...mapGetters('users', ['getErrorAddUser']),
			...mapGetters('app', ['getRoles', 'getIsAdmin', 'getIsGeneral', 'getCurrentUser']),
			...mapGetters('posts', ['getAllPosts']),
			getRols () {
				return this.getIsGeneral == false ? this.getRoles.filter(elem => elem != 'GENERAL_ADMIN') : this.getRoles
			},
			displayRoles () {
				let bl = !this.getIsGeneral && (this.getIsAdmin && !(this.userFrom.username == this.getCurrentUser.username) && !this.profile && !(this.userFrom.roles.includes('USER') && this.userFrom.roles.length == 1))
				return bl
			}
    },
		mounted() {
			eventBus.$on('error', (data) => {
				if (data.check === false) {
					eventBus.$emit('dialog2', false)
				} 
				setTimeout(() => this.errorAction({check: false, error: ''}), 3000)
			})
		}
	}
</script>

<style scoped>
  .error {
    border-radius: 5px;
    padding: 5px
  }
</style>
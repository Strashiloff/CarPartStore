<template>
  <div>
    <v-text-field data-vv-name="name" :rules="[rules.fill]" clearable v-model="userFrom.username" autofocus required label="Логин"
                  v-validate="'required|max:20'" :counter="20"></v-text-field>
    <v-text-field oninput="this.value = this.value.replace(' ', '')"
                  v-model="password = userFrom.password"
                  :append-icon="show1 ? 'visibility' : 'visibility_off'"
                  :rules="[rules.min]"
                  :type="show1 ? 'text' : 'password'"
                  name="password"
                  label="Пароль"
                  hint="Не менее 6 символов"
                  counter
                  @click:append="show1 = !show1"
    ></v-text-field>
    <v-text-field v-if="!getIsAdmin"
                  v-model="confPas"
                  :rules="[rules.pas, rules.fill]"
                  label="Подтверждение пароля"
    ></v-text-field>
    <v-text-field :rules="[rules.fill]" clearable v-model="userFrom.name" :type="'text'" data-vv-name="name" name="name"
                  label="Имя"></v-text-field>
    <v-text-field :rules="[rules.fill]" clearable v-model="userFrom.surname" :type="'text'" data-vv-name="surname"
                  name="password" label="Фамилия"></v-text-field>
    <v-select v-if="getIsAdmin" v-model="userFrom.roles" :items="getRoles" attach label="Системные роль:" multiple></v-select>
    <v-select v-if="getIsAdmin" :search="search" v-model="post = userFrom.position" :items="getAllPosts" return-object
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
      <v-btn @click="changeUser" :disabled="enter" color="indigo">Сохранить</v-btn>
    </v-card-actions>
  </div>
</template>

<script>
	import { mapActions, mapGetters} from 'vuex'
	import { eventBus } from 'main';

	export default {
		props: ['userFrom', 'editMethod', 'mode'],
		name: "UserDialog",
		data() {
			return {
				confPas: '',
				password: '',
				text: [],
				post: {},
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
			}
		},
		methods: {
			...mapActions('users', ['addUserAction', 'errorAction']),
			changeUser() {
				this.userFrom.password = this.password
				var user = this.userFrom
				user.position = this.post
				if (user.username !== '' && user.name !== '' && user.surname !== '' && user.password !== '') {
					user.username.replace(' ', '_')
					user.name.replace(' ', '_')
					user.surname.replace(' ', '_')
					this.editMethod(user)
				} else this.errorAction({check: true, error: 'Fill in all the fields!'})
			},
			Canceled() {
				eventBus.$emit('dialog2', false)
			}
		},
		computed: {
			...mapGetters('users', ['getErrorAddUser']),
			...mapGetters('app', ['getRoles', 'getIsAdmin']),
			...mapGetters('posts', ['getAllPosts'])
    },
		mounted() {
			eventBus.$on('error', (data) => {
				console.log(data)
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
<template>
  <div>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3 pt-5>
        <v-card style="padding: 20px; margin-top: 20px">
					<user-dialog :userFrom="user" :profile="true" :mode="false" :editMethod="registrationMethod" max-width="500"></user-dialog>
				</v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
	import { mapActions } from 'vuex'
	import UserDialog from 'components/UserDialog.vue'

	export default {
		name: "AddUser",
		components: {
			UserDialog
		},
		data() {
			return {
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

		methods: {
			...mapActions('users',['addUserAction']),
			registrationMethod(newUser) {
				this.addUserAction(newUser)
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
		computed: {}
	}
</script>

<style scoped>

</style>
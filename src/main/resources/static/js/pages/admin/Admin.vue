<template>
  <div v-if="getIsAdmin">
    <v-tabs centered>
      <v-tab to="/admin/registration">Регистрация</v-tab>
      <v-tab to="/admin/users">Пользователи</v-tab>
      <v-tab to="/admin/posts">Должности</v-tab>
    </v-tabs>
    <router-view></router-view>
  </div>
</template>

<script>
	import { mapGetters } from 'vuex'

	export default {
		name: "Admin",
		computed: mapGetters('app',['getIsAdmin']),
		created() {
			this.$router.push('/admin/users')
		}
	}
</script>

<style scoped>

</style>
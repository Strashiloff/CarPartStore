<template>
  <div>
    <v-tabs centered>
      <v-tab to="/admin/registration">Registration</v-tab>
      <v-tab to="/admin/users">Users</v-tab>
      <v-tab to="/admin/posts">Positions</v-tab>
    </v-tabs>
    <router-view></router-view>
  </div>
</template>

<script>
	import { mapGetters } from 'vuex'

	export default {
		name: "Admin",
		computed: mapGetters(['getIsAdmin']),
		beforeRouteUpdate(to, from, next) {
			if (this.getIsAdmin) {
				next();
			} else {
				next('/');
			}
		},
		created() {
			this.$router.push('/admin/users')
		}
	}
</script>

<style scoped>

</style>
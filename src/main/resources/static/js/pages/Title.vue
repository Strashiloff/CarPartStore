<template>
  <v-app dark>
    <v-toolbar app dark>
      <v-toolbar-side-icon @click.stop="drawer = !drawer" v-show="$router.currentRoute.path === '/'"></v-toolbar-side-icon>
      <v-toolbar-title class="display-1 hidden-xs-only">
        Store
      </v-toolbar-title>
      <v-toolbar-items>
        <v-btn class="ml-4" to="/" icon>
          <v-icon>home</v-icon>
        </v-btn>
        <v-btn v-if="getIsAdmin" flat to="/admin/">Admin</v-btn>
        <v-btn flat to="/tit">Test</v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-toolbar-title class="headline hidden-xs-only">{{getCurrentUser.name + ' ' + getCurrentUser.surname}}</v-toolbar-title>
      <v-btn icon href="/logout">
        <v-icon large>exit_to_app</v-icon>
      </v-btn>
    </v-toolbar>
    <v-content>
      <v-navigation-drawer
          v-model="drawer"
          absolute
          temporary
      ></v-navigation-drawer>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
	import { mapActions, mapGetters } from 'vuex'
	import AskDialog from "components/AskDialog.vue";

	export default {
    name: "Title",
		data() {
			return {
				drawer: null
			}
		},
		comments: {
			AskDialog
		},
		methods: {
			...mapActions(['getCurrentUserAction', 'getPostsAction', 'addPostAction', 'getUsersAction']),
		},
		computed: {
      ...mapGetters(['getCurrentUser', 'getIsAdmin'])
		},
		created() {
			// this.$store.dispatch('getCurrentUserAction'
			console.log(this.$router.currentRoute)
			this.getCurrentUserAction()
			this.getPostsAction()
			this.getUsersAction()
		}
	}
</script>

<style>
  html, body {
    margin: 0;
    padding: 0;
    height: 100vh;
  }

  .font {
    font-size: 15pt;
  }
</style>
<template>
  <v-app :dark="getTheme">
    <v-navigation-drawer
      v-model="drawer"
      absolute
      temporary
    >
      <v-list class="ma-2">
        <v-list-tile avatar>
          <v-list-tile-avatar color="teal" size="55" class="mr-2 headline">
            <span class="white--text ">{{ getUserNameShort }}</span>
          </v-list-tile-avatar>

          <v-list-tile-content>
            <v-list-tile-title class="title">{{ getUserNameFull }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>

      <v-list class="pt-0" dense>
        <v-divider></v-divider>

        <!-- <v-list-tile
          v-for="item in items"
          :key="item.title"
        >
          <v-list-tile-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-tile-action>

          <v-list-tile-content>
            <v-list-tile-title>{{ item.title }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile> -->
      </v-list>
    </v-navigation-drawer>
    <v-toolbar app :dark="getTheme">
      <!-- v-show="$router.currentRoute.path === '/'" -->
      <v-toolbar-side-icon @click.stop="drawer =! drawer" ></v-toolbar-side-icon>
      <v-toolbar-title @click="$router.currentRoute.path !== '/' ? $router.push('/') : null" class="display-2 hidden-xs-only">
        Store
      </v-toolbar-title>
      <v-toolbar-items>
        <v-btn class="ml-4" to="/" icon>
          <v-icon large>home</v-icon>
        </v-btn>
        <v-btn class="ml-2" v-if="getIsAdmin" flat to="/admin/">Панель админа</v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-toolbar-title class="headline hidden-xs-only">{{getUserNameFull}}</v-toolbar-title>
      <v-btn icon href="/logout">
        <v-icon large color="error">mdi-exit-to-app</v-icon>
      </v-btn>
    </v-toolbar>
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
	import { mapActions, mapGetters } from 'vuex'
  import AskDialog from "components/AskDialog.vue"
  import Navigation from './Navigation.vue'

	export default {
    name: "Title",
		data() {
			return {
				drawer: false
			}
		},
		components: {
      AskDialog,
      Navigation
		},
		methods: {
			...mapActions('app', ['getCurrentUserAction', 'setDrawer']),
      ...mapActions('posts', ['getPostsAction', 'addPostAction']),
      ...mapActions('users', ['getUsersAction']),
      ...mapActions('section', ['setAllSectionsAction']),
      ...mapActions('stoke', ['getAllStokeAction']),
      ...mapActions('type', ['setAllTypesAction']),
      ...mapActions('sparePart', ['getAllSparePartsAction']),
      drawerClick () {
        this.setDrawer()
      }
		},
		computed: {
      ...mapGetters('app', ['getCurrentUser', 'getIsAdmin',  'getTheme', 'getDrawer', 'getUserNameShort', 'getUserNameFull'])
    },
		created() {
			this.getCurrentUserAction()
			this.getPostsAction()
      this.getUsersAction()
      this.getAllStokeAction()
      this.setAllSectionsAction()
      this.getAllSparePartsAction()
      this.setAllTypesAction()
		}
	}
</script>

<style>
  html, body {
    margin: 0;
    padding: 0;
    height: 100vh;
  }

  ::-webkit-scrollbar {
    width: 12px;
  }
  
  ::-webkit-scrollbar-track {
    box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
    border-radius: 10px;
  }
  
  ::-webkit-scrollbar-thumb {
    border-radius: 10px;
    box-shadow: inset 0 0 6px rgba(0,0,0,0.5);
  }

  .font {
    font-size: 15pt;
  }
</style>
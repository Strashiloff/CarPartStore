<template>
  <v-app :dark="getTheme" style="overflow: hidden;">
    <v-navigation-drawer
      v-model="drawer"
      temporary
      fixed
    >
      <v-list class="ma-2">
        <v-list-tile avatar>
          <v-list-tile-avatar color="teal" size="55" class="mr-2 headline">
            <span
              class="white--text"
              style="font-family: Serif"
            >{{ getUserNameShort }}</span>
          </v-list-tile-avatar>

          <v-list-tile-content>
            <v-list-tile-title class="title">{{ getUserNameFull }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>

      <v-list>
        <v-list-tile to="/">
          <v-list-tile-action >
            <v-icon>home</v-icon>
          </v-list-tile-action>
          <v-list-tile-title>Home</v-list-tile-title>
        </v-list-tile>
        <v-list-tile to="/account">
          <v-list-tile-action >
            <v-icon>mdi-account</v-icon>
          </v-list-tile-action>
          <v-list-tile-title>Аккаунт</v-list-tile-title>
        </v-list-tile>
        <v-list-group
          prepend-icon="mdi-shield-account"
           v-if="getIsAdmin"
        >
          <template v-slot:activator>
            <v-list-tile>
              <v-list-tile-title>Панель Администратора</v-list-tile-title>
            </v-list-tile>
          </template>
          <v-list-tile
            v-for="(admin, i) in admins"
            :key="i"
            :to="admin[2]"
            class="pl-4"
          >
            <v-list-tile-action>
              <v-icon v-text="admin[1]"></v-icon>
            </v-list-tile-action>
            <v-list-tile-title v-text="admin[0]"></v-list-tile-title>
          </v-list-tile>
        </v-list-group>
        <v-list-group
          prepend-icon="mdi-tray-full"
        >
          <template v-slot:activator>
            <v-list-tile>
              <v-list-tile-title>Склад</v-list-tile-title>
            </v-list-tile>
          </template>
          <v-list-tile
            v-for="(stoke, i) in stokes"
            :key="i"
            :to="stoke[2]"
            class="pl-4"
          >
            <v-list-tile-action>
              <v-icon v-text="stoke[1]"></v-icon>
            </v-list-tile-action>
            <v-list-tile-title v-text="stoke[0]"></v-list-tile-title>
          </v-list-tile>
        </v-list-group>
        <v-list-group
          prepend-icon="mdi-truck"
        >
          <template v-slot:activator>
            <v-list-tile>
              <v-list-tile-title>Поставки</v-list-tile-title>
            </v-list-tile>
          </template>
          <v-list-tile
            v-for="(suppl, i) in supply"
            :key="i"
            :to="suppl[2]"
            class="pl-4"
          >
            <v-list-tile-action>
              <v-icon v-text="suppl[1]"></v-icon>
            </v-list-tile-action>
            <v-list-tile-title v-text="suppl[0]"></v-list-tile-title>
          </v-list-tile>
        </v-list-group>
        <v-list-group
          prepend-icon="mdi-clipboard-list"
        >
          <template v-slot:activator>
            <v-list-tile>
              <v-list-tile-title>Заказы</v-list-tile-title>
            </v-list-tile>
          </template>
          <v-list-tile
            v-for="(order, i) in orders"
            :key="i"
            :to="order[2]"
            class="pl-4"
          >
            <v-list-tile-action>
              <v-icon v-text="order[1]"></v-icon>
            </v-list-tile-action>
            <v-list-tile-title v-text="order[0]"></v-list-tile-title>
          </v-list-tile>
        </v-list-group>   
        <v-list-tile to="/userlist">
          <v-list-tile-action >
            <v-icon>mdi-account-supervisor</v-icon>
          </v-list-tile-action>
          <v-list-tile-title>Сотрудники</v-list-tile-title>
        </v-list-tile>  
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
        <v-btn class="ml-2 hidden-xs-only" v-if="getIsAdmin" flat to="/admin/">Панель админа</v-btn>
        <v-btn class="ml-2 hidden-xs-only" flat to="/userlist">Доска сотрудников</v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-menu 
        offset-y
        open-on-hover
        align-center
      >
        <template v-slot:activator="{ on }">
          <v-toolbar-title
            v-on="on"
            open-on-hover
            class="headline hidden-xs-only"
          >
            {{getUserNameFull}}
          </v-toolbar-title>
        </template>
        <v-list >
          <v-list-tile to="/account">
            <v-list-tile-title class="text-xs-center">Аккаунт</v-list-tile-title>
          </v-list-tile>
          <v-list-tile href="/logout">
            <v-list-tile-title class="text-xs-center">Выйти</v-list-tile-title>
          </v-list-tile>
        </v-list>
      </v-menu>
      <!-- <v-toolbar-title to="/"  class="headline hidden-xs-only">{{getUserNameFull}}</v-toolbar-title> -->
      <v-btn icon href="/logout">
        <v-icon large color="error">mdi-exit-to-app</v-icon>
      </v-btn>
    </v-toolbar>
    <v-content class="back-image">
      <router-view></router-view>
    </v-content>
    <v-footer
      height="auto"
    >
      <v-layout
        justify-center
        row
        wrap
      >
        <v-flex
          py-3
          text-xs-center
          xs12
        >
          &copy;2019 — <strong>Strashiloff LLC</strong>
        </v-flex>
      </v-layout>
    </v-footer>
    <v-btn
      v-scroll="onScroll"
      v-show="fab"
      fab
      fixed
      bottom
      right
      color="indigo"
      @click="toTop"
    >
      <v-icon>keyboard_arrow_up</v-icon>
    </v-btn>
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
        drawer: false,
        fab: false,
        admins: [
          ['Регистрация', 'mdi-account-plus', '/admin/registration'],
          ['Пользователи', 'people_outline', '/admin/users'],
          ['Должности', 'mdi-account-box', '/admin/posts']
        ],
        stokes: [
          ['Настройка', 'mdi-settings', '/stokes/settings'],
          ['Содержание', 'mdi-buffer', '/stokes/content'],
          ['База запчастей', 'mdi-dresser', '/stokes/sparepart']
        ],
        supply: [
          ['Список', 'mdi-clipboard-text', '/deliveries/supplies'],
          ['Страны/Типы', 'mdi-flag', '/deliveries/countries'],
          ['Поставщики', 'mdi-truck-fast', '/deliveries/purveyors']
        ],
        orders: [
          ['Заявки', 'mdi-clipboard-text', '/orders/requests'],
          ['Покупки', 'mdi-cart', '/orders/buys'],
          ['База клиентов', 'mdi-account-group', '/orders/customers'],
          ['Брак', 'mdi-alert-box', '/orders/defects']
        ]
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
      ...mapActions('country', ['getCountriesAction']),
      ...mapActions('category', ['getCategoriesAction']),
      ...mapActions('purveyor', ['getPurveyorsAction']),
      ...mapActions('supply', ['getSuppliesAction']),
      ...mapActions('position', ['setAllPositionsAction']),
      ...mapActions('contract', ['getContractsAction']),
      ...mapActions('customer', ['getCustomersAction']),
      ...mapActions('request', ['getRequestsAction']),
      ...mapActions('list', ['setAllListsAction']),
      ...mapActions('buy', ['getBuysAction']),
      ...mapActions('defect', ['getDefectsAction']),
      drawerClick () {
        this.setDrawer()
      },
      getAllData() {
        this.getCurrentUserAction()
        this.getPostsAction()
        this.getUsersAction()
        this.getAllStokeAction()
        this.setAllSectionsAction()
        this.getAllSparePartsAction()
        this.setAllTypesAction()
        this.getCountriesAction()
        this.getCategoriesAction()
        this.getPurveyorsAction()
        this.getSuppliesAction()
        this.setAllPositionsAction()
        this.getContractsAction()
        this.getCustomersAction()
        this.getRequestsAction()
        this.setAllListsAction()
        this.getBuysAction()
        this.getDefectsAction()
      },
      onScroll (e) {
        if (typeof window === 'undefined') return
        const top = window.pageYOffset ||   e.target.scrollTop || 0
        this.fab = top > 20
      },
      toTop () {
        this.$vuetify.goTo(0)
      }
		},
		computed: {
      ...mapGetters('app', ['getCurrentUser', 'getIsAdmin',  'getTheme', 'getDrawer', 'getUserNameShort', 'getUserNameFull'])
    },
		created() {
      this.getAllData()
      setInterval(() => {
        this.getAllData()
      }, 2 * 60 * 1000)
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

  .back-image {
    background: url('/img/back.jpg') repeat-y;
    background-size: 100vw 100vh
  }
</style>
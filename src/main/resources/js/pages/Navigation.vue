<template>
 <v-navigation-drawer
    v-model="drawer"
    absolute
    temporary
  >
    <v-list class="pa-1">
      <v-list-tile avatar>
        <v-list-tile-avatar color="teal" class="mr-2">
          <span class="white--text headline">{{getUserNameShort}}</span>
        </v-list-tile-avatar>

        <v-list-tile-content>
          <v-list-tile-title>{{getUserNameFull}}</v-list-tile-title>
        </v-list-tile-content>
      </v-list-tile>
    </v-list>

    <v-list>
      <v-list-tile>
        <v-list-tile-action>
          <v-icon>home</v-icon>
        </v-list-tile-action>
        <v-list-tile-title>Home</v-list-tile-title>
      </v-list-tile>

      <v-list-group
        prepend-icon="account_circle"
        value="true"
      >
        <template v-slot:activator>
          <v-list-tile>
            <v-list-tile-title>Users</v-list-tile-title>
          </v-list-tile>
        </template>
        <v-list-group
          no-action
          sub-group
          value="true"
        >
          <template v-slot:activator>
            <v-list-tile>
              <v-list-tile-title>Admin</v-list-tile-title>
            </v-list-tile>
          </template>

          <v-list-tile
            v-for="(admin, i) in admins"
            :key="i"
            @click=""
          >
            <v-list-tile-title v-text="admin[0]"></v-list-tile-title>
            <v-list-tile-action>
              <v-icon v-text="admin[1]"></v-icon>
            </v-list-tile-action>
          </v-list-tile>
        </v-list-group>

        <v-list-group
          sub-group
          no-action
        >
          <template v-slot:activator>
            <v-list-tile>
              <v-list-tile-title>Actions</v-list-tile-title>
            </v-list-tile>
          </template>
          <v-list-tile
            v-for="(crud, i) in cruds"
            :key="i"
            @click=""
          >
            <v-list-tile-title v-text="crud[0]"></v-list-tile-title>
            <v-list-tile-action>
              <v-icon v-text="crud[1]"></v-icon>
            </v-list-tile-action>
          </v-list-tile>
        </v-list-group>
      </v-list-group>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
export default {
  name: 'Navigation',
  // props: {
  //   drawer: {
  //     default: false
  //   }
  // },
  watch: {
    drawer () {
      console.log(this.drawer)
    }
  },
  data () {
    return {
      items: [
        { title: 'Home', icon: 'dashboard' },
        { title: 'About', icon: 'question_answer' },
      ],
    }
  },
  methods: {
    ...mapActions('app', ['getCurrentUserAction', 'setDrawer']),
    drawerClick () {
      this.setDrawer()
    }
  },
  computed: {
    ...mapGetters('app', ['getDrawer', 'getUserNameShort', 'getUserNameFull']),
    getDrawerSet: {
      get () { return this.getDrawer },
      set () { }
    }
  }
}
</script>
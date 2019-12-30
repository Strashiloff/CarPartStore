<template>
  <v-container grid-list-md text-xs-center>
    <v-layout align-space-around justify-center column>
      <v-flex xs6 sm>
        <v-card hover class="back-color mb-1" :key="index" v-for="(user, index) in getUsers">
            <v-card-title primary-title class="py-2">
            <v-avatar
              size="70"
              :color="user.color || 'blue'"
            >
              <img v-if="user.image" :src="user.image">
              <span
                v-else
                class="white--text headline"
                style="font-family: 'Times New Roman', Times, serif !important"
              >{{ user.name[0].toUpperCase()+user.surname[0].toUpperCase() }}</span>
            </v-avatar>
            <v-list class="back-color-list" two-line>
              <v-list-tile>
                <v-list-tile-content>
                  <v-list-tile-title class="headline" >{{user.name+' '+user.surname}}</v-list-tile-title>
                  <v-list-tile-sub-title class="title">{{user.position.post}}</v-list-tile-sub-title>
                </v-list-tile-content>
              </v-list-tile>
            </v-list>
          </v-card-title>
          <v-card-text class="text-xs-left pl-4 pb-3 pt-0" v-html="text(user)"></v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapGetters } from 'vuex';
export default {
  name: 'UserList',
  computed: {
    ...mapGetters('users', ['getUsers']),
    text () {
      return user => user.status && user.status.split('\n').join('<br>') || 'Нет описания'
    }
  }
}
</script>

<style scoped>
  .custom-width {
    height: fit-content;
  }
  .v-list__tile__title {
    padding: 0;
    height: 34px;
    /* font-family: sans-serif !important */
  }
  .v-list__tile__sub-title {
    height: 24px;
    padding: 2px 0;
  }

  .back-color {
    background: rgba(66, 66, 66, 0.9) !important
  }

  .back-color-list {
    background: rgba(66, 66, 66, 0) !important
  }
</style>
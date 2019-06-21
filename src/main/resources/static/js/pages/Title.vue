<template>
    <div>
        <v-app dark>
            <v-toolbar app dark>
                <v-toolbar-title class="display-1">
                    Store
                </v-toolbar-title>
                <v-toolbar-items class="hidden-sm-and-down">
                    <v-btn class="ml-4" to="/" icon><v-icon>home</v-icon></v-btn>
                    <v-btn v-if="admin" flat to="/admin">Admin</v-btn>
                    <v-btn flat to="/tit">Some</v-btn>
                    <v-btn flat >Another</v-btn>
                </v-toolbar-items>
                <v-spacer></v-spacer>
                <v-toolbar-title class="headline">{{profile.name + ' ' + profile.surname}}</v-toolbar-title>
                <v-btn icon href="/logout">
                    <v-icon large>exit_to_app</v-icon>
                </v-btn>
            </v-toolbar>
            <v-content>
                <router-view></router-view>
            </v-content>
        </v-app>
    </div>
</template>

<script>
    import NavBar from 'components/NavBar.vue'
    import {mapActions, mapState} from 'vuex'
    import AskDialog from "components/AskDialog.vue";

    export default {
        name: "Title",
        comments:{
            NavBar,
            AskDialog
        },
        data(){
          return{
              roles:[],
          }
        },
        methods:{
            ...mapActions(['getCurrentUserAction']),
        },
        computed: {
            ...mapState(['profile', 'admin']),
        },
        created() {
            // this.$store.dispatch('getCurrentUserAction')
            this.getCurrentUserAction()
        }
    }
</script>

<style>
    .font{
        font-size: 15pt;
    }
</style>
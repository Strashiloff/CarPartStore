<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
    <div>
        <v-app>
            <v-toolbar app dark>
                <v-toolbar-title class="display-1">Store</v-toolbar-title>
                <v-list-tile :to="'/registration'"></v-list-tile>
                <router-link to="/registration">Registration</router-link>
                <v-spacer></v-spacer>
                <v-toolbar-title class="headline">{{profile}}</v-toolbar-title>
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

    export default {
        name: "Title",
        comments:{
            NavBar
        },
        data(){
            return{
                profile: frontendData.profile
            }
        }
    }
</script>

<style>
    .font{
        font-size: 15pt;
    }
</style>
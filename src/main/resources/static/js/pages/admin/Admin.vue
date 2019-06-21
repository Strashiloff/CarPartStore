<template>
    <div>
        <v-tabs centered>
            <v-tab to="/admin/registration">Registration</v-tab>
            <v-tab to="/admin/users">Users</v-tab>
        </v-tabs>
        <router-view></router-view>
    </div>
</template>

<script>

    import {mapState} from 'vuex'

    export default {
        name: "Admin",
        data(){
            return{

            }
        },
        computed: mapState(['admin']),
        mounted() {
            if(this.admin)this.$router.replace('/admin/registration')
            else this.$router.replace('/')
        }
    }
</script>

<style scoped>

</style>
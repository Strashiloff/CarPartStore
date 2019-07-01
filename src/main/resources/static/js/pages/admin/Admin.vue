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
        computed: mapState(['admin']),
        beforeRouteUpdate(to, from, next){
            if(this.admin){
                next();
            }else{
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
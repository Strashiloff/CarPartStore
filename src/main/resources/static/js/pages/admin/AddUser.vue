<template>
    <div>
        <v-layout row>
            <v-flex xs12 sm6 offset-sm3 pt-5 style="border: 25px solid #373737; border-radius: 20px; padding: 30px; margin-top: 50px">
                <user-dialog :userFrom="user" :mode="false" :editMethod="registrationMethod"></user-dialog>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>
    import {mapActions, mapState} from 'vuex'
    import UserDialog from 'components/UserDialog.vue'

    export default {
        name: "AddUser",
        components:{
            UserDialog
        },
        data(){
            return{
                user: {
                    username: '',
                    name: '',
                    surname: '',
                    password: '',
                    roles:['USER']
                }
            }
        },

        methods:{
            ...mapActions(['addUserAction']),
            registrationMethod(newUser){
                this.addUserAction(newUser)
                this.user = {
                    username: '',
                    name: '',
                    surname: '',
                    password: '',
                    roles:['USER']
                }
            }
        },
        computed: mapState(['errorAddUser', 'roles', 'admin']),
    }
</script>

<style scoped>

</style>
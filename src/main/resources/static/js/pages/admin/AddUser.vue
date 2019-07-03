<template>
    <div>
        <v-layout row>
            <v-flex xs12 sm6 offset-sm3 pt-5 style="border: 20px solid #373737; border-radius: 20px; padding: 20px; margin-top: 20px">
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
                    post: { post: ''},
                    roles:['USER']
                }
            }
        },

        methods:{
            ...mapActions(['addUserAction']),
            registrationMethod(newUser){
                console.log(newUser);
                this.addUserAction(newUser)
                this.user = {
                    username: '',
                    name: '',
                    surname: '',
                    password: '',
                    post: { post: ''},
                    roles:['USER']
                }
            }
        },
        computed: mapState(['errorAddUser', 'roles', 'admin']),
    }
</script>

<style scoped>

</style>
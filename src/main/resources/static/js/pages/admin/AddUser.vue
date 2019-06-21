<template>
    <div>
        <v-layout row>
            <v-flex xs12 sm6 offset-sm3 pt-5 style="border: 25px solid #373737; border-radius: 20px; padding: 30px; margin-top: 50px">
            <v-text-field data-vv-name="name" clearable v-model="login" required label="Login" v-validate="'required|max:20'" :counter="20"></v-text-field>
            <v-text-field
                    v-model="password"
                    :append-icon="show1 ? 'visibility' : 'visibility_off'"
                    :rules="[rules.min]"
                    :type="show1 ? 'text' : 'password'"
                    name="password"
                    label="Password"
                    hint="At least 6 characters"
                    counter
                    @click:append="show1 = !show1"
            ></v-text-field>
            <v-text-field clearable v-model="name" :type="'text'" data-vv-name="name" name="password" label="Name"></v-text-field>
            <v-text-field clearable v-model="surname" :type="'text'" data-vv-name="surname" name="password" label="Surname"></v-text-field>
            <v-select v-model="select" :items="roles" attach label="User roles:" multiple></v-select>
            <v-alert class="text-xs-center title" style="border-radius: 5px" :value="errorAddUser.check">{{errorAddUser.error}}</v-alert>
            <v-btn type="button" @click="addUser">Submit</v-btn>
            </v-flex>
        </v-layout>

    </div>
</template>

<script>
    import {mapActions, mapState} from 'vuex'

    export default {
        name: "AddUser",
        data(){
            return{
                login:'',
                password:'',
                name:'',
                surname:'',
                select:['USER'],
                show1: false,
                rules: {
                    min: v => v.length >= 6 || 'Min 6 characters',
                }
            }
        },
        watch:{
            select: function () {
                if(this.select.length == 0){
                    this.select.push('USER')
                }
            }
        },
        methods:{
            ...mapActions(['addUserAction']),
            addUser(){
                if(this.login != '' &&  this.password.length > 5 && this.name != '' && this.surname != '') {
                    var user = {
                        username: this.login,
                        password: this.password,
                        name: this.name,
                        surname: this.surname,
                        roles: this.select
                    }
                    //this.addUserAction(user)
                    this.login = ''
                    this.password = ''
                    this.name = ''
                    this.surname = ''
                    this.select = ['USER']
                }
            }
        },
        computed: mapState(['errorAddUser', 'roles']),
        created(){

        }
    }
</script>

<style scoped>

</style>
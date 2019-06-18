<template>
    <div>
        <v-layout row>
            <v-flex xs12 sm6 offset-sm3 pt-5>
            <v-text-field data-vv-name="name" clearable v-model="login" required label="Login" v-validate="'required|max:20'" :counter="20"></v-text-field>
            <v-text-field
                    v-model="password"
                    :append-icon="show1 ? 'visibility' : 'visibility_off'"
                    :rules="[rules.required, rules.min]"
                    :type="show1 ? 'text' : 'password'"
                    name="password"
                    label="Password"
                    hint="At least 8 characters"
                    counter
                    @click:append="show1 = !show1"
            ></v-text-field>
            <v-text-field v-model="name" :type="'password'" data-vv-name="name" name="password" label="Name"></v-text-field>
            <v-text-field v-model="surname" :type="'password'" data-vv-name="surname" name="password" label="Surname"></v-text-field>
            <v-flex>
                <v-switch style="margin-top: 8px" height="0" v-model="select" label="Admin" value="ADMIN"></v-switch>
                <v-switch style="margin: 0" height="0" v-model="select" label="User" value="USER"></v-switch>
            </v-flex>
            <v-alert outline v-if="error != ''">{{error}}</v-alert>
            <div color="error" :value="true">{{error}}</div>
            <v-btn type="button" @click="addUser">Submit</v-btn>
            </v-flex>
        </v-layout>

    </div>
</template>

<script>
    export default {
        name: "AddUser",
        data(){
            return{
                login:'',
                password:'',
                retrypas:'',
                name:'',
                surname:'',
                select:['USER'],
                error: '',
                token: '',
                show1: false,
                rules: {
                    required: value => !!value || 'Required.',
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
            addUser(){
                var user = {
                    id: 0,
                    username: this.login,
                    password: this.password,
                    name: this.name,
                    surname: this.surname,
                    roles: this.select
                }
                this.$resource('/registration').save({}, user).then(result =>

                    result.json().then(data => {
                        this.error = data.error
                    })
                )
            }
        },
        created() {
        }
    }
</script>

<style scoped>

</style>
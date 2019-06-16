<template>
    <div>
        <v-list-tile-content>
            <input type="text" placeholder="Login" v-model="login">
            <input type="password" placeholder="Password" v-model="password">
            <input type="password" placeholder="Retry password" v-model="retrypas">
            <input type="text" placeholder="Name" v-model="name">
            <input type="text" placeholder="Surname" v-model="surname">
            <v-combobox v-model="select" :items="role" label="Select user role"></v-combobox>
            <button @click="addUser">Submit</button>

        </v-list-tile-content>
    </div>
</template>

<script>
    export default {
        name: "AddUser",
        data(){
            return{
                login:'login',
                password:'password',
                retrypas:'',
                name:'name',
                surname:'surname',
                role:[ 'USER', 'ADMIN'],
                select:'ADMIN',
                error: '',
                token: ''
            }
        },
        http: {

        },
        methods:{
            addUser(){
                var user = {
                    username: this.login,
                    password: this.password,
                    name: this.name,
                    surname: this.surname,
                    roles: this.select
                }
                this.$resource('/registration').save({}, user).then(response => {
                    console.log(response)
                })
            }
        },
        mounted() {
            console.log(token);
        }
    }
</script>

<style scoped>

</style>
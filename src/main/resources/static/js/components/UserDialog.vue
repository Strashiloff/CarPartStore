<template>
    <div>
        <v-text-field data-vv-name="name" :rules="[rules.fill]" clearable v-model="userFrom.username" required label="Login" v-validate="'required|max:20'" :counter="20"></v-text-field>
        <v-text-field
                v-model="password = userFrom.password"
                :append-icon="show1 ? 'visibility' : 'visibility_off'"
                :rules="[rules.min]"
                :type="show1 ? 'text' : 'password'"
                name="password"
                label="Password"
                hint="At least 6 characters"
                counter
                @click:append="show1 = !show1"
        ></v-text-field>
        <v-text-field v-if="!admin"
                v-model="confPas"
                :rules="[rules.pas, rules.fill]"
                label="Retry password"
        ></v-text-field>
        <v-text-field :rules="[rules.fill]" clearable v-model="userFrom.name" :type="'text'" data-vv-name="name" name="name" label="Name"></v-text-field>
        <v-text-field :rules="[rules.fill]" clearable v-model="userFrom.surname" :type="'text'" data-vv-name="surname" name="password" label="Surname"></v-text-field>
        <v-select v-if="admin" v-model="userFrom.roles" :items="roles" attach label="User roles:" multiple></v-select>
        <v-alert class="text-xs-center body-1" style="border-radius: 5px; padding: 5px" :value="errorAddUser.check">{{errorAddUser.error}}</v-alert>
        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn v-if="mode" @click="Canceled">Cancel</v-btn>
            <v-btn @click="changeUser" :disabled="enter">Submit</v-btn>
        </v-card-actions>
    </div>
</template>

<script>
    import {mapActions, mapState} from 'vuex'
    import {eventBus} from 'main';

    export default {
        props: ['userFrom', 'editMethod', 'mode'],
        name: "UserDialog",
        data(){
            return{
                confPas:'',
                password:'',
                show1: false,
                enter:false,
                rules: {
                    min: v => v.length >= 6 || 'Min 6 characters',
                    pas: v => v.toString() === this.password.toString() || 'Passwords don\'t match',
                    fill: v => v !== null || 'Fill in the field',
                }
            }
        },
        watch:{
            confPas(){
                if (!this.admin) this.enter = this.password !== this.confPas
            },
            password(){
                this.enter = this.userFrom.password.length < 6
            }
        },
        methods:{
            ...mapActions(['addUserAction', 'errorAction']),
            changeUser(){
                this.userFrom.password = this.password
                var user = this.userFrom
                console.log(user)
                if(user.username != null && user.name != null && user.surname != null && user.password != null){
                    this.editMethod(this.userFrom)
                }
                else this.errorAction({check:true, error:'Fill in all the fields!'})
            },
            Canceled(){
                eventBus.$emit('dialog2', false)
            }
        },
        computed: mapState(['errorAddUser', 'roles', 'admin']),
        mounted() {
            eventBus.$on('error', (data) =>{
                if(data.check === false) {
                    eventBus.$emit('dialog2', false)
                }
                else setTimeout(() => this.errorAction({check:false, error:''}), 3000)
            })
        }
    }
</script>

<style scoped>

</style>
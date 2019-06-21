<template>
    <v-card class="mt-2">
        <v-card-title class="display-1">
            Users
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-text-field
                v-model="search"
                append-icon="search"
                lable="Search"
                single-line
                hide-details
            ></v-text-field>
        </v-card-title>
        <v-data-table
            :headers="headers"
            :items="users"
            :search="search"
        >
            <template v-slot:items="props">
                <td class="text-xs-left"><i>{{props.item.id}}</i></td>
                <td class="text-xs-left">{{props.item.username}}</td>
                <td class="text-xs-left">{{props.item.name}}</td>
                <td class="text-xs-left">{{props.item.surname}}</td>
                <td class="text-xs-left" ><i v-for="role in props.item.roles">{{role + ' '}}</i></td>
                <v-btn icon>
                    <v-icon>edit</v-icon>
                </v-btn>
                <v-btn @click="test(props.item.id)" :disabled="dialog" icon>
                    <v-icon>delete</v-icon>
                </v-btn>
            </template>
            <template v-slot:no-results>
                <v-alert :value="true" color="error" icon="warning"></v-alert>
            </template>
        </v-data-table>
        <v-dialog v-model="dialog" max-width="290">
            <v-card>
                <v-card-title class="headline">You want to delete this user?</v-card-title>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn flat="flat" @click="dialog = false">
                        Disagree
                    </v-btn>
                    <v-btn flat="flat" @click="dialog = false">
                        Agree
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-card>
</template>

<script>
    import {mapActions, mapState} from 'vuex'

    export default {
        name: "Users",
        data: function () {
            return{
                headers:[
                    {text: 'ID', value: 'id'},
                    {text: 'Login', value: 'username'},
                    {text: 'Name', value: 'name'},
                    {text: 'Surname', value: 'surname'},
                    {text: 'System role', value: 'roles'},
                    {text: 'Edit', value: ''}
                ],
                search:'',
                text: 'You want to delete this user?',
                dialog: false
            }
        },
        computed: mapState(['users']),
        methods: {
            ...mapActions(['getUsersAction']),
            test(id){
                this.dialog = true
                console.log(this.dialog + '   ' + id)
            }
        },
        mounted(){

        },
        created() {
            this.getUsersAction()
        }
    }
</script>

<style scoped>

</style>
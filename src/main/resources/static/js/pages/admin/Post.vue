<template>
    <v-container grid-list-md >
        <v-layout align-space-around column wrap reverse>
            <v-flex>
                <v-card>
                    <v-card-title class="display-1">
                        Positions
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
                            :items="posts"
                            :search="search"
                    >
                        <template v-slot:items="props">
                            <td class="text-xs-left"><i>{{props.item.id}}</i></td>
                            <td class="text-xs-left">{{props.item.post}}</td>
                            <v-btn icon @click="">
                                <v-icon @click="editPost(props.item)">edit</v-icon>
                            </v-btn>
                            <v-btn @click="removePost(props.item)" :disabled="dialog" icon>
                                <v-icon>delete</v-icon>
                            </v-btn>
                        </template>
                        <template v-slot:no-results>
                            <v-alert :value="true" color="error" icon="warning"></v-alert>
                        </template>
                    </v-data-table>
                    <asc-dialog :dialog="dialog" :text="text"></asc-dialog>
                    <v-card-text style="color: red">If the position is tied to a user, it will not be deleted.</v-card-text>
                </v-card>

            </v-flex>
            <v-flex>
                <v-card class="pa-1">
                    <v-card-title class="display-1">Add position</v-card-title>
                    <v-card-text >
                        <v-text-field
                            label="Position"
                            v-model="newPost.post"
                            :rules="[rules.fill]"
                        ></v-text-field>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn @click="addPost">Add</v-btn>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import AscDialog from 'components/AskDialog.vue'
    import {mapActions, mapState} from 'vuex'
    import {eventBus} from 'main';

    export default {
        name: "Post",
        components:{
            AscDialog
        },
        data(){
            return{
                headers:[
                    {text: 'ID', value: 'id'},
                    {text: 'Position', value: 'post'},
                ],
                search:'',
                text: 'You want to delete this position?',
                newPost:{ post: ''},
                dialog: false,
                post: { post: ''},
                rules: {
                    fill: v => v !== '' || 'Fill in the position',
                }
            }
        },
        methods:{
            ...mapActions(['addPostAction', 'removePostAction']),
            editPost(post){
                this.post = post
            },
            removePost(post){
                this.post = post
                this.dialog = true
            },
            addPost(){
                if(this.newPost!=='' && this.posts.indexOf(this.newPost)===-1){
                    this.addPostAction(this.newPost)
                    this.newPost = { post: ''}
                }
            }
        },
        computed:{
            ...mapState(['posts'])
        },
        mounted() {
            eventBus.$on('dialog', (ok) =>{
                this.dialog = false
                if(ok){
                    this.removePostAction(this.post)
                }
                this.post = { post: ''}
            })
        }
    }
</script>

<style scoped>

</style>
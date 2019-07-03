<template>
    <v-dialog @keydown.prevent="onKeydown" persistent v-model="dialog" max-width="290">
        <v-card>
            <v-card-title class="headline">{{text}}</v-card-title>
            <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn flat="flat" @click="dialogClick(false)">
                    Disagree
                </v-btn>
                <v-btn flat="flat" @click="dialogClick(true)">
                    Agree
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-dialog>
</template>

<script>
    import {eventBus} from 'main';

    export default {
        name: "AskDialog",
        props:['dialog', 'text'],
        methods:{
            dialogClick(ok){
                eventBus.$emit('dialog', ok)
            }
        }
    }
</script>

<style scoped>

</style>
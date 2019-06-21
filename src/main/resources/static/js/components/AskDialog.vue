<template>
    <v-card>
        <v-card-title class="headline">{{text}}</v-card-title>
        <v-card-actions>
            <v-btn flat @click="dialog = false">Disagree</v-btn>
            <v-btn flat @click="dialog = true">Agree</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        name: "AskDialog",
        props:['dialog', 'text']
    }
</script>

<style scoped>

</style>
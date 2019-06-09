<template>
    <div>
        <h1>Hello world</h1>
        <router-link to="/login">Sign in</router-link>
        <router-link to="/test">Test</router-link>
        <router-view></router-view>
    </div>
</template>

<script>
    export default {
        name: 'Main',
    }
</script>

<style>

</style>
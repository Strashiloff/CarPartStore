<template>
  <div>
    <ul>
      <li v-for="stoke in getAllStoke">Stoke {{stoke.id}}</li>
    </ul>
  </div>
</template>

<script>
	import { mapActions, mapGetters } from 'vuex'
	export default {
		name: "Settings",
		methods: {
      ...mapActions(['getAllStokeAction'])
		},
		computed: {
      ...mapGetters(['getAllStoke'])
		},
		created() {
			this.getAllStokeAction()
		}
	}
</script>

<style scoped>

</style>
<template>
  <v-dialog @keydown.prevent="() => {}" persistent v-model="dialog" max-width="300">
    <v-card>
      <v-card-title class="headline" style="text-align: center">{{text}}</v-card-title>
      <v-card-actions class="justify-center">
        <v-btn color="success" @click="dialogClick(true)">
          Да
        </v-btn>
        <v-btn color="error" @click="dialogClick(false)">
          Нет
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
	import {eventBus} from 'main';

	export default {
		name: "AskDialog",
		props: ['dialog', 'text'],
		methods: {
			dialogClick(ok) {
				this.$emit('dialog', ok)
			}
		}
	}
</script>

<style scoped>

</style>
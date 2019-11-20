<template>
  <div>
    <TableView
      :actions="[]"
      :headers="headers"
      :array="getAllStoke"
			:title="'Склады'"
			@editObject="edit($event)"
			@deleteObject="delObj($event)"
    />
		<ask-dialog :dialog="dialog" :text="'Вы точно хотите удалить склад?'"></ask-dialog>
  </div>
</template>

<script>
	import TableView from "../../components/TableView.vue"
	import AskDialog from '../../components/AskDialog.vue'
	import { mapActions, mapGetters } from 'vuex'
	import { eventBus } from 'main'

	export default {
		name: "Settings",
    data () {
			return {
				headers: [
					{
						text: 'ID',
						value: 'id',
						class: 'title'
					},
					{
						text: 'Местоположение',
						value: 'location',
						class: 'title'
					},
					{
						text: 'Полки (шт)',
						value: 'count',
						class: 'title'
					},
					{
						text: '',
						value: 'buttons',
						sortable: false
					},
				],
				dialog: false,
				item: null
      }
    },
    components: {
			TableView,
			AskDialog
    },
		methods: {
			...mapActions('stoke', ['getAllStokeAction', 'deleteStokeAction']),
			edit (item) {
				this.item = item
				console.log('edit', item)
			},
			delObj (item) {
				this.item = item
				this.dialog = true
				console.log('delObj', item)
			},
		},
		mounted () {
			eventBus.$on('dialog', (ok) => {
				this.dialog = false
				if (ok) {
					this.deleteStokeAction(this.item)
				}
			})
		},
		computed: {
      ...mapGetters('stoke', ['getAllStoke'])
		},
		created() {
			this.getAllStokeAction()
		}
	}
</script>

<style scoped>

</style>
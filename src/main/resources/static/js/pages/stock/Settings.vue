<template>
  <div>
    <TableView
      :headers="headers"
      :array="getAllStoke"
			:title="'Склады'"
			:classS="'mt-2'"
			@addObject="add()"
			@editObject="edit($event)"
			@deleteObject="delObj($event)"
    />
		<EditAddStoke
			:dialog="dialogAdd"
			@submit="addItem($event)"
			@cancel="dialogAdd = false"
		/>
		<EditAddStoke
			:dialog="dialogEdit"
			@submit="editItem($event)"
			@cancel="cancel()"
		/>
		<ask-dialog
			:dialog="dialogDel"
			:text="'Вы точно хотите удалить склад?'"
			@dialog="deleteItem($event)"
		></ask-dialog>
  </div>
</template>

<script>
	import TableView from "../../components/TableView.vue"
	import AskDialog from '../../components/AskDialog.vue'
	import EditAddStoke from './EditAddStoke.vue'
	import { mapActions, mapGetters } from 'vuex'
	import { eventBus } from 'main'

	export default {
		name: "settings-stoke",
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
						text: 'Макс. кол-во полок (шт)',
						value: 'count',
						class: 'title'
					},
					{
						text: 'Редактировать',
						value: '',
						align: 'center',
						class: 'title',
						sortable: false
					}
				],
				dialogDel: false,
				dialogAdd: false,
				dialogEdit: false,
				item: { }
      }
    },
    components: {
			TableView,
			AskDialog,
			EditAddStoke
    },
		methods: {
			...mapActions('stoke', ['getAllStokeAction', 'deleteStokeAction', 'addStokeAction', 'setEditAction', 'saveStokeAction']),
			add () {
				this.setEditAction({
					location: '',
					count: 0,
					id: -1
				})
				this.dialogAdd = true
			},
			edit (item) {
				this.setEditAction(item)
				this.dialogEdit = true
				this.item = item
			},
			delObj (item) {
				this.item = item
				this.dialogDel = true
			},
			addItem (item) {
				this.dialogAdd = false
				this.addStokeAction(item)
			},
			editItem (item) {
				this.dialogEdit = false
				this.saveStokeAction(item)
			},
			deleteItem (ok) {
				this.dialogDel = false
				if (ok) {
					this.deleteStokeAction(this.item)
				}
			},
			cancel () {
				console.log('cancel')
				this.dialogEdit = false
				this.item = {
					location: '',
					count: 0,
					id: -1
				}
				this.setEditAction(this.item)
			}
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
<template>
  <div>
    <table-view
      :classS="'mt-2'"
      :title="'Список поставок'"
      :headers="headers"
      :array="getAllSupplies"
      :editMethods="view"
      :list="true"
      @addObject="add()"
      @editObject="edit($event)"
      @expandObject="expand($event)"
      @deleteObject="delObj($event)"
    />
    <asc-dialog
      :dialog="dialogDel"
      :text="'Вы точно хотите удалить поставку?'"
      @dialog="deleteItem($event)"
    />
    <add-supply
      v-if="dialogAdd"
      :dialog="dialogAdd"
      @submit="addItem($event)"
      @cancel="dialogAdd = false"
    />
    <add-supply
      :dialog="dialogEdit"
      @submit="editItem($event)"
      @cancel="dialogEdit = false"
    />
    <edit-supply
      :dialog="dialogExp"
      @cancel="dialogExp = false, setEditItem({})"
    />
  </div>
</template>

<script>
import TableView from '../../components/TableView.vue'
import AscDialog from '../../components/AskDialog.vue'
import EditSupply from './supply/EditSupply.vue'
import AddSupply from './supply/AddSupply.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Purveyor',
  data () {
    return {
      headers: [
        {
          text: 'ID',
          value: 'id',
          class: 'title'
        },
        {
          text: 'Контрак',
          value: 'contract',
          class: 'title'
        },
        {
          text: 'Поставщик',
          value: 'purveyor',
          class: 'title'
        },
        {
          text: 'Дата доставки',
          value: 'date',
          class: 'title'
        },
        {
          text: 'Налог',
          value: 'tax',
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
      dialogExp: false,
      item: { },
      name: ''
    }
  },
  components: {
    TableView,
    AscDialog,
    EditSupply,
    AddSupply
  },
  computed: {
    ...mapGetters('supply', ['getAllSupplies']),
    ...mapGetters('purveyor', ['getPurveyorById']),
    ...mapGetters('category', ['getCategoryById']),
    ...mapGetters('contract', ['getContractById'])
  },
  methods: {
    ...mapActions('app', ['setEditItem']),
    ...mapActions('supply', ['addSupplyAction', 'saveSupplyAction', 'removeSupplyAction']),
    add () {
      this.dialogAdd = true
    },
    edit (item) {
      this.setEditItem(item)
      this.dialogEdit = true
      this.item = item
    },
    expand (item) {
      this.setEditItem(item)
      this.item = item
      this.dialogExp = true
    },
    delObj (item) {
      this.item = item
      this.dialogDel = true
    },
    addItem (item) {
      this.dialogAdd = false
      this.addSupplyAction(item)
    },
    editItem (item) {
      this.setEditItem({})
      this.dialogEdit = false
      this.saveSupplyAction(item)
    },
    deleteItem (ok) {
      this.dialogDel = false
      if (ok) {
        this.removeSupplyAction(this.item)
      }
    },
    view (value, name, index) {
      if (name == 'id_contract') return this.getContractById(value) && this.getContractById(value).date || 'Нет'
      if (name == 'id_purveyor') return this.getPurveyorById(value) && this.getPurveyorById(value).name || null
      return value
    },
  }
}
</script>

<style scoped>

</style>
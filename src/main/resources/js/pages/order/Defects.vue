<template>
  <div>
    <table-view
      :classS="'mt-2'"
      :title="'Список дефектных деталей'"
      :headers="headers"
      :array="getAllDefects"
      :editMethods="view"
      @addObject="add()"
      @editObject="edit($event)"
      @deleteObject="delObj($event)"
    />
    <asc-dialog
      :dialog="dialogDel"
      :text="'Вы точно хотите удалить покупку?'"
      @dialog="deleteItem($event)"
    />
    <edit-add-defect
      v-if="dialogAdd"
      :dialog="dialogAdd"
      @submit="addItem($event)"
      @cancel="dialogAdd = false"
    />
    <edit-add-defect
      :dialog="dialogEdit"
      @submit="editItem($event)"
      @cancel="dialogEdit = false"
    />
  </div>
</template>

<script>
import TableView from '../../components/TableView.vue'
import AscDialog from '../../components/AskDialog.vue'
import EditAddDefect from './edit/EditAddDefect.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Defects',
  data () {
    return {
      headers: [
        {
          text: 'ID',
          value: 'id',
          class: 'title'
        },
        {
          text: 'Покупка',
          value: 'id_buy',
          class: 'title'
        },
        {
          text: 'Позиция',
          value: 'id_list',
          class: 'title'
        },
        {
          text: 'Дата возврата',
          value: 'date',
          class: 'title'
        },
        {
          text: 'Количество',
          value: 'amount',
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
    EditAddDefect
  },
  computed: {
    ...mapGetters('customer', ['getCustomerById']),
    ...mapGetters('defect', ['getAllDefects']),
    ...mapGetters('list', ['getListById']),
    ...mapGetters('position', ['getPositionById']),
    ...mapGetters('sparePart', ['getSparePartById']),
    initials () {
      return id => {
        let req = this.getListById(id)
        return req && req.id + ' ' + this.getPosition(req.id_position) || ''
      }
    },
    getPosition () {
      return id => {
        let pos = this.getPositionById(id)
        return pos && this.getSparePart(pos.id_spare_part) || 'Нет'
      }
    },
    getSparePart () {
      return id => {
        let sp = this.getSparePartById(id)
        return sp && sp.name && ' | Содержит: ' + sp.name + ' ' + sp.proportions || ''
      }
    }
  },
  methods: {
    ...mapActions('app', ['setEditItem']),
    ...mapActions('defect', ['addDefectAction', 'saveDefectAction', 'removeDefectAction']),
    add () {
      this.dialogAdd = true
    },
    edit (item) {
      this.setEditItem(item)
      this.dialogEdit = true
      this.item = item
    },
    delObj (item) {
      this.item = item
      this.dialogDel = true
    },
    addItem (item) {
      this.dialogAdd = false
      this.addDefectAction(item)
    },
    editItem (item) {
      this.setEditItem({})
      this.dialogEdit = false
      this.saveDefectAction(item)
    },
    deleteItem (ok) {
      this.dialogDel = false
      if (ok) {
        this.removeDefectAction(this.item)
      }
    },
    view (value, name, index) {
      if (name == 'id_list') return this.initials(value)
      return value
    },
  }
}
</script>

<style>

</style>
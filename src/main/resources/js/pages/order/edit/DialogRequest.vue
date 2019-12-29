<template>
  <v-dialog v-model="dialog" persistent fullscreen hide-overlay transition="dialog-bottom-transition">
    <v-card color="grey darken-2">
      <v-toolbar dark color="primary">
        <v-toolbar-title>Редактирование заявки</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-toolbar-items>
          <v-btn dark flat @click="$emit('cancel', null)">
            Выйти
          </v-btn>
        </v-toolbar-items>
      </v-toolbar>
      <table-view
        :title="'Позиции'"
        :array="getListByRequesttId(getEditItem.id)"
        :editMethods="view"
        :headers="headers"
        :classS="'mt-2'"
        @addObject="add()"
        @editObject="edit($event)"
        @deleteObject="delObj($event)"
      />
      <edit-add-list
        v-if="dialogAdd"
        :dialog="dialogAdd"
        @submit="addItem($event)"
        @cancel="dialogAdd = false"
      />
      <edit-add-list
        :dialog="dialogEdit"
        @submit="editItem($event)"
        @cancel="dialogEdit = false"
      />
      <asc-dialog
        :dialog="dialogDel"
        :text="'Вы точно хотите удалить позицию?'"
        @dialog="deleteItem($event)"
      />
    </v-card>
  </v-dialog>
</template>

<script>
import TableView from '../../../components/TableView.vue'
import AscDialog from '../../../components/AskDialog.vue'
import EditAddList from './EditAddList.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'DialogRequest',
  components: {
    TableView,
    AscDialog,
    EditAddList
  },
  props: {
    dialog: {
      default: false
    }
  },
  computed: {
    ...mapGetters('app', ['getEditItem']),
    ...mapGetters('list', ['getListByRequesttId']),
    ...mapGetters('request', ['getRequestById']),
    ...mapGetters('position', ['getPositionById']),
    ...mapGetters('sparePart', ['getSparePartById']),
  },
  methods: {
    ...mapActions('list', ['setEditItem']),
    ...mapActions('list', ['addListAction', 'saveListAction', 'deleteListAction']),
    view (value, name, index) {
      if (name == 'id_position') {
        let b = this.getPositionById(value)
        return (b && '(№' + b.id + ')  Деталь: ' + this.sparePart(b) || null)
      }
      return value
    },
    sparePart (item) {
      let spare_part = this.getSparePartById(item.id)
      return (spare_part && spare_part.name && spare_part.name + ' | ' + spare_part.proportions) || ''
    },
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
      this.setEditItem({})
      this.addListAction(item)
    },
    editItem (item) {
      this.dialogEdit = false
      this.setEditItem({})
      this.saveListAction(item)
    },
    deleteItem (ok) {
      this.dialogDel = false
      if (ok) {
        this.deleteListAction(this.item)
      }
    },
    save () {
      this.dialog1 = false
      let contract = {
        id: this.getIsContract || -1, 
        body: this.text,
        date: moment(this.date + ' ' + this.time).format('YYYY-MM-DD HH:mm:ss')
      }
      // this.getIsContract == false ? this.add(contract) : this.saveContractAction(contract)
    }
  },
  created () {
    // this.time = (this.getContractById(this.getIsContract) && this.getContractById(this.getIsContract).date.format('HH:mm')) || this.time
    // this.date = (this.getContractById(this.getIsContract) && this.getContractById(this.getIsContract).date.format('YYYY-MM-DD')) || this.date
  },
  data () {
    return {
      dialogAdd: false,
      dialogEdit: false,
      dialogDel: false,
      switch1: false,
      dialog1: false,
      part1: 'ООО "Store"',
      part2: '',
      text: '',
      item: {},
      menu: false,
      menu2: false,
      time: moment().format('HH:mm'),
      date: moment().format('YYYY-MM-DD'),
      headers: [
        {
          text: 'ID',
          value: 'id',
          class: 'title'
        },
        {
          text: 'Номер заявки',
          value: 'id_request',
          class: 'title'
        },
        {
          text: 'Номер позиции',
          value: 'id_position',
          class: 'title'
        },
        {
          text: 'Количество',
          value: 'amount',
          class: 'title'
        },
        {
          text: 'Цена за шт',
          value: 'price',
          class: 'title'
        },
        {
          text: 'Редактировать',
          value: '',
          align: 'center',
          class: 'title',
          sortable: false
        }
      ]
    }
  },
}
</script>

<style>

</style>
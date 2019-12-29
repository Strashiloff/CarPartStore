<template>
  <div>
    <table-view
      :classS="'mt-2'"
      :title="'Список покупателей'"
      :headers="headers"
      :array="getAllCustomers"
      :editMethods="view"
      @addObject="add()"
      @editObject="edit($event)"
      @deleteObject="delObj($event)"
    />
    <asc-dialog
      :dialog="dialogDel"
      :text="'Вы точно хотите удалить покупателя?'"
      @dialog="deleteItem($event)"
    />
    <edit-add-customer
      v-if="dialogAdd"
      :dialog="dialogAdd"
      @submit="addItem($event)"
      @cancel="dialogAdd = false"
    />
    <edit-add-customer
      :dialog="dialogEdit"
      @submit="editItem($event)"
      @cancel="dialogEdit = false"
    />
  </div>
</template>

<script>
import TableView from '../../components/TableView.vue'
import AscDialog from '../../components/AskDialog.vue'
import EditAddCustomer from './edit/EditAddCustomer.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Customers',
  data () {
    return {
      headers: [
        {
          text: 'ID',
          value: 'id',
          class: 'title'
        },
        {
          text: 'Фамилия',
          value: 'surname',
          class: 'title'
        },
        {
          text: 'Имя',
          value: 'name',
          class: 'title'
        },
        {
          text: 'Отчество',
          value: 'patronymic',
          class: 'title'
        },
        {
          text: 'Контактный номер',
          value: 'contact',
          class: 'title'
        },
        {
          text: 'Пол',
          value: 'sex',
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
    EditAddCustomer
  },
  computed: {
    ...mapGetters('customer', ['getAllCustomers']),
  },
  methods: {
    ...mapActions('app', ['setEditItem']),
    ...mapActions('customer', ['addCustomerAction', 'saveCustomerAction', 'removeCustomerAction']),
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
      this.addCustomerAction(item)
    },
    editItem (item) {
      this.setEditItem({})
      this.dialogEdit = false
      this.saveCustomerAction(item)
    },
    deleteItem (ok) {
      this.dialogDel = false
      if (ok) {
        this.removeCustomerAction(this.item)
      }
    },
    view (value, name, index) {
      if (name == 'sex') {
        if (!value) return 'Женщина'
        else return 'Мужчина'
      }
      return value
    },
  }
}
</script>

<style>

</style>
<template>
  <div>
    <table-view
      :classS="'mt-2'"
      :title="'Список заявок'"
      :headers="headers"
      :array="getAllRequests"
      :editMethods="view"
      :list="true"
      @addObject="add()"
      @editObject="edit($event)"
      @expandObject="expand($event)"
      @deleteObject="delObj($event)"
    />
    <asc-dialog
      :dialog="dialogDel"
      :text="'Вы точно хотите удалить заявку?'"
      @dialog="deleteItem($event)"
    />
    <edit-add-request
      v-if="dialogAdd"
      :dialog="dialogAdd"
      @submit="addItem($event)"
      @cancel="dialogAdd = false"
    />
    <edit-add-request
      :dialog="dialogEdit"
      @submit="editItem($event)"
      @cancel="dialogEdit = false"
    />
    <dialog-request
      :dialog="dialogExp"
      @cancel="dialogExp = false, setEditItem({})"
    />
    <!-- <edit-supply
      :dialog="dialogExp"
      @cancel="dialogExp = false, setEditItem({})"
    /> -->
  </div>
</template>

<script>
import TableView from '../../components/TableView.vue'
import AscDialog from '../../components/AskDialog.vue'
import EditAddRequest from './edit/EditAddRequest.vue'
import DialogRequest from './edit/DialogRequest.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Requests',
  data () {
    return {
      headers: [
        {
          text: 'ID',
          value: 'id',
          class: 'title'
        },
        {
          text: 'Продал',
          value: 'user',
          class: 'title'
        },
        {
          text: 'Покупатель',
          value: 'customer',
          class: 'title'
        },
        {
          text: 'Дата заявки',
          value: 'date',
          class: 'title'
        },
        {
          text: 'Крайний срок отгрузки',
          value: 'period',
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
    EditAddRequest,
    DialogRequest
  },
  computed: {
    ...mapGetters('users', ['getUserById']),
    ...mapGetters('request', ['getAllRequests']),
    ...mapGetters('customer', ['getCustomerById']),
    ...mapGetters('purveyor', ['getPurveyorById']),
    ...mapGetters('category', ['getCategoryById']),
    ...mapGetters('contract', ['getContractById']),
    initials () {
      return id => {
        let us = this.getUserById(id)
        return us && us.name && us.name + ' ' + us.surname || 'Нет'
      }
    },
    initialsCustomer () {
      return id => {
        let us = this.getCustomerById(id)
        return us && us.name && us.surname + ' ' + us.name + ' ' + us.patronymic || 'Нет'
      }
    }
  },
  methods: {
    ...mapActions('app', ['setEditItem']),
    ...mapActions('request', ['addRequestAction', 'saveRequestAction', 'removeRequestAction']),
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
      this.addRequestAction(item)
    },
    editItem (item) {
      this.setEditItem({})
      this.dialogEdit = false
      this.saveRequestAction(item)
    },
    deleteItem (ok) {
      this.dialogDel = false
      if (ok) {
        this.removeRequestAction(this.item)
      }
    },
    view (value, name, index) {
      if (name == 'id_user') return this.initials(value)
      if (name == 'id_customer') return this.initialsCustomer(value)
      return value
    },
  }
}
</script>

<style>

</style>
<template>
  <div>
    <table-view
      :classS="'mt-2'"
      :title="'Список покупок'"
      :headers="headers"
      :array="getAllBuys"
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
    <edit-add-buy
      v-if="dialogAdd"
      :dialog="dialogAdd"
      @submit="addItem($event)"
      @cancel="dialogAdd = false"
    />
    <edit-add-buy
      :dialog="dialogEdit"
      @submit="editItem($event)"
      @cancel="dialogEdit = false"
    />
  </div>
</template>

<script>
import TableView from '../../components/TableView.vue'
import AscDialog from '../../components/AskDialog.vue'
import EditAddBuy from './edit/EditAddBuy.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Buys',
  data () {
    return {
      headers: [
        {
          text: 'ID',
          value: 'id',
          class: 'title'
        },
        {
          text: 'Заяка',
          value: 'id_request',
          class: 'title'
        },
        {
          text: 'Завершенность',
          value: 'completed',
          class: 'title'
        },
        {
          text: 'Дата покупки',
          value: 'date',
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
    EditAddBuy
  },
  computed: {
    ...mapGetters('customer', ['getCustomerById']),
    ...mapGetters('buy', ['getAllBuys']),
    ...mapGetters('request', ['getRequestById']),
    initials () {
      return id => {
        let req = this.getRequestById(id)
        return req && req.id && '№ ' + req.id + ' | ' + req.date  +'  (' + this.gerUser(req.id_customer)+')' || ''
      }
    },
    gerUser () {
      return id => {
        let us = this.getCustomerById(id)
        return us && us.name && us.surname + ' ' + us.name + ' ' + us.patronymic || 'Нет'
      }
    },
    completed () {
      return value => {
        return value == true ? 'Куплено' : 'Отклонено'
      }
    }
  },
  methods: {
    ...mapActions('app', ['setEditItem']),
    ...mapActions('buy', ['addBuyAction', 'saveBuyAction', 'removeBuyAction']),
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
      this.addBuyAction(item)
    },
    editItem (item) {
      this.setEditItem({})
      this.dialogEdit = false
      this.saveBuyAction(item)
    },
    deleteItem (ok) {
      this.dialogDel = false
      if (ok) {
        this.removeBuyAction(this.item)
      }
    },
    view (value, name, index) {
      if (name == 'id_request') return this.initials(value)
      if (name == 'completed') return this.completed(value)
      return value
    },
  }
}
</script>

<style>

</style>
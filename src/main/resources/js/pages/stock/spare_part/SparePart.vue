<template>
  <v-layout align-space-around justify-center column>
    <v-flex class="mt-2 ml-2 mr-2 mb-0">
      <v-card >
        <v-card-title class="justify-end">
          <v-btn title="Управлениями типами деталей" @click="dialog = true" dark color="indigo">Управлениями типами</v-btn>
        </v-card-title>
      </v-card>
    </v-flex>
    <v-flex class="ma-0">
      <table-view
        :title="'Запчасти'"
        :classS="'mt-0'"
        :array="getAllSpareParts"
        :headers="headers"
        :editMethods="view"
        @addObject="add"
        @editObject="edit($event)"
        @deleteObject="delObj($event)"
      />
    </v-flex>
    <asc-dialog
      :dialog="dialogDel"
      :text="'Вы точно хотите удалить запчасть из базы?'"
      @dialog="deleteItem($event)"
    />
    <edit-add-spare-part
      v-if="dialogAdd"
      :dialog="dialogAdd"
      @submit="addItem($event)"
      @cancel="dialogAdd = false"
    />
    <edit-add-spare-part
      :dialog="dialogEdit"
      @submit="editItem($event)"
      @cancel="dialogEdit = false"
    />
    <TypeDialog
      :dialog="dialog"
      @cancel="dialog = false"
    />
  </v-layout>
</template>

<script>
import TableView from '../../../components/TableView.vue'
import TypeDialog from './TypeDialog.vue'
import AscDialog from '../../../components/AskDialog.vue'
import EditAddSparePart from './EditAddSparePart.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'SparePart',
  components: {
    TableView,
    TypeDialog,
    AscDialog,
    EditAddSparePart
  },
  data () {
    return {
      dialog: false,
      headers: [
        {
          text: 'ID',
          value: 'id',
          class: 'title'
        },
        {
          text: 'Тип',
          value: 'id_type',
          class: 'title'
        },
        {
          text: 'Номер склад.полка',
          value: 'id_section',
          class: 'title'
        },
        {
          text: 'Наименование',
          value: 'name',
          class: 'title'
        },
        {
          text: 'Цена (магазина)',
          value: 'price',
          class: 'title'
        },
        {
          text: 'Параметры',
          value: 'proportions',
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
  methods: {
    ...mapActions('app', ['setEditItem']),
    ...mapActions('sparePart', ['addSparePartsAction', 'deleteSparePartsAction', 'saveSparePartsAction']),
    view (value, name, index) {
      if (name == 'id_type') return (this.getTypeById(value) && this.getTypeById(value).name) || null
      if (name == 'id_section') return (this.getSectionById(value) && this.getSectionById(value).id_stoke+'.'+this.getSectionById(value).number) || 'Нет на складе'
      return value
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
      this.addSparePartsAction(item)
    },
    editItem (item) {
      this.dialogEdit = false
      this.saveSparePartsAction(item)
    },
    deleteItem (ok) {
      this.dialogDel = false
      if (ok) {
        this.deleteSparePartsAction(this.item)
      }
    }
  },
  computed: {
    ...mapGetters('sparePart', ['getAllSpareParts']),
    ...mapGetters('type', ['getTypeById']),
    ...mapGetters('section', ['getSectionById'])
  }
}
</script>

<style scoped>

</style>
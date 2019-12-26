<template>
  <div>
    <table-view
      :classS="'mt-2'"
      :title="'База поставщиков'"
      :headers="headers"
      :array="getAllPurveyors"
      :editMethods="view"
      @addObject="add()"
      @editObject="edit($event)"
      @deleteObject="delObj($event)"
    />
    <asc-dialog
      :dialog="dialogDel"
      :text="'Вы точно хотите удалить поставщика?'"
      @dialog="deleteItem($event)"
    />
    <edit-add-purveyor
      v-if="dialogAdd"
      :dialog="dialogAdd"
      @submit="addItem($event)"
      @cancel="dialogAdd = false"
    />
    <edit-add-purveyor
      :dialog="dialogEdit"
      @submit="editItem($event)"
      @cancel="dialogEdit = false"
    />
  </div>
</template>

<script>
import TableView from '../../components/TableView.vue'
import EditAddPurveyor from './EditAddPurveyor.vue'
import AscDialog from '../../components/AskDialog.vue'
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
          text: 'Тип',
          value: 'category',
          class: 'title'
        },
        {
          text: 'Страна',
          value: 'country',
          class: 'title'
        },
        {
          text: 'Гарантии',
          value: 'warranty',
          class: 'title'
        },
        {
          text: 'Название',
          value: 'name',
          class: 'title'
        },
        {
          text: 'Адресс',
          value: 'adress',
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
    AscDialog,
    EditAddPurveyor
  },
  computed: {
    ...mapGetters('purveyor', ['getAllPurveyors']),
    ...mapGetters('country', ['getCountryById']),
    ...mapGetters('category', ['getCategoryById'])
  },
  methods: {
    ...mapActions('app', ['setEditItem']),
    ...mapActions('purveyor', ['addPurveyorAction', 'savePurveyorAction', 'removePurveyorAction']),
    add () {
      this.setEditItem({
        id: -1,
        id_category: -1,
        id_country: -1,
        warranty: '',
        name: '',
        adress: ''
      })
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
      this.addPurveyorAction(item)
    },
    editItem (item) {
      this.dialogEdit = false
      this.savePurveyorAction(item)
    },
    deleteItem (ok) {
      this.dialogDel = false
      if (ok) {
        this.removePurveyorAction(this.item)
      }
    },
    view (value, name, index) {
      if (name == 'id_category') return (this.getCategoryById(value) && this.getCategoryById(value).name || null)
      if (name == 'id_country') return (this.getCountryById(value) && this.getCountryById(value).name || null)
      return value
    },
  }
}
</script>

<style scoped>

</style>
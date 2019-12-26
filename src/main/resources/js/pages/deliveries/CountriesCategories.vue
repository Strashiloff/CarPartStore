<template>
  <div>
    <v-layout class="mt-2 pl-1 pr-1 mb-2" align-start justify-center row>
      <v-flex style="width: 50%">
        <table-view
          :headers="headers"
          :array="getAllCountries"
          :title="'Страны'"
          @addObject="add(1)"
          @editObject="edit($event, 1)"
          @deleteObject="delObj($event, 1)"
        />
      </v-flex>
      <v-flex style="width: 50%">
        <table-view
          :headers="headers"
          :array="getAllCategories"
          :title="'Категории поставщиков'"
          @addObject="add(2)"
          @editObject="edit($event, 2)"
          @deleteObject="delObj($event, 2)"
        />
      </v-flex>
      <one-line-edit-add
        v-if="dialogAdd"
        :dialog="dialogAdd"
        @submit="addItem($event)"
        @cancel="dialogAdd = false"
      />
      <one-line-edit-add
        :dialog="dialogEdit"
        @submit="editItem($event)"
        @cancel="dialogEdit = false"
      />
      <asc-dialog
        :dialog="dialogDel"
        :text="'Вы точно хотите удалить ' + (type == 1 ? 'страну?' : 'тип поставщика?')"
        @dialog="deleteItem($event)"
      />
    </v-layout>
  </div>
</template>

<script>
import TableView from '../../components/TableView.vue'
import OneLineEditAdd from '../../components/OneLineEditAdd.vue'
import AscDialog from '../../components/AskDialog.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'Countries',
  data () {
    return {
      headers: [
        {
          text: 'ID',
          value: 'id',
          class: 'title'
        },
        {
          text: 'Название',
          value: 'name',
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
      dialogAdd: false,
      dialogEdit: false,
      dialogDel: false,
      type: 1
    }
  },
  components: {
    TableView,
    OneLineEditAdd,
    AscDialog
  },
  methods: {
    ...mapActions('app', ['setEditItem']),
    ...mapActions('country', ['addCountryAction', 'saveCountryAction', 'removeCountryAction']),
    ...mapActions('category', ['addCategoryAction', 'saveCategoryAction', 'removeCategoryAction']),
    add (type) {
      this.setEditItem({
        name: '',
        id: -1
      })
      this.type = type
      this.dialogAdd = true
    },
    edit (item, type) {
      this.setEditItem(item)
      this.type = type
      this.item = item
      this.dialogEdit = true
    },
    delObj (item, type) {
      this.item = item
      this.type = type
      this.dialogDel = true
    },
    addItem (item) {
      this.dialogAdd = false
      this.type == 1 ? this.addCountryAction(item) : this.addCategoryAction(item)
    },
    editItem (item) {
      this.dialogEdit = false
      this.type == 1 ? this.saveCountryAction(item) : this.saveCategoryAction(item)
    },
    deleteItem (ok) {
      this.dialogDel = false
      if (ok) {
        this.type == 1 ? this.removeCountryAction(this.item) : this.removeCategoryAction(this.item)
      }
    }
  },
  computed: {
    ...mapGetters('country', ['getAllCountries']),
    ...mapGetters('category', ['getAllCategories'])
  }
}
</script>

<style scoped>
</style>
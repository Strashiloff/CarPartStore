<template>
  <v-dialog v-model="dialog" persistent fullscreen hide-overlay transition="dialog-bottom-transition">
    <v-card>
      <v-toolbar dark color="primary">
        <v-btn icon dark @click="$emit('cancel', null)">
          <v-icon>close</v-icon>
        </v-btn>
        <v-toolbar-title>Управления типами</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-toolbar-items>
          <v-btn dark flat @click="$emit('cancel', null)">Выйти</v-btn>
        </v-toolbar-items>
      </v-toolbar>
      <div class="wrapper">
        <TableView
          :title="'Типы деталей'"
          :classS="'mt-5'"
          :array="getAllTypes"
          :headers="headers"
          @addObject="add()"
          @editObject="edit($event)"
          @deleteObject="delObj($event)"
        />
      </div>
    </v-card>
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
			:text="'Вы точно хотите удалить тип?'"
			@dialog="deleteItem($event)"
    />
  </v-dialog>
</template>

<script>
import TableView from '../../../components/TableView.vue'
import AscDialog from '../../../components/AskDialog.vue'
import OneLineEditAdd from '../../../components/OneLineEditAdd.vue'
import { mapGetters, mapActions } from 'vuex'

export default {
  name: 'TypeDialog',
  components: {
    TableView,
    AscDialog,
    OneLineEditAdd
  },
  props: {
    dialog: {
      default: false
    }
  },
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
      item: {}
    }
  },
  computed: {
    ...mapGetters('type', ['getAllTypes'])
  },
  methods: {
    ...mapActions('type', ['deleteTypeAction', 'saveTypeAction', 'addTypeAction']),
    ...mapActions('app', ['setEditItem']),
    add () {
      this.setEditItem({
        name: '',
        id: -1
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
      this.addTypeAction(item)
    },
    editItem (item) {
      this.dialogEdit = false
      this.saveTypeAction(item)
    },
    deleteItem (ok) {
      this.dialogDel = false
      if (ok) {
        this.deleteTypeAction(this.item)
      }
    }
  }
}
</script>

<style scoped>
  .wrapper {
    padding: 0 10% 10%; 
  }
</style>
<template>
  <v-card class="mt-2 ml-2 mr-2">
    <v-list class="title ma-2">
      <v-list-group
        v-for="stoke in getStoke"
        :key="stoke.id"
        v-model="model[stoke.id]"
        append-icon="arrow_drop_down"
        prepend-icon="mdi-crop-square"
        no-action
        class="ma-2 pa-1"
      >
        <template v-slot:activator>
          <v-list-tile-content class="pt-2 pb-2">
            <v-list-tile-title v-text="'Склад №' + stoke.id +' ('+stoke.location+')'"></v-list-tile-title>
            <v-list-tile-sub-title
              class="mt-2 mb-2"
            >
              Количество полок: {{ getSectionByStokeId(stoke.id) ? getSectionByStokeId(stoke.id).length : 0 }} &nbsp; | &nbsp; Макс. полок: {{stoke.count}}
            </v-list-tile-sub-title>
          </v-list-tile-content>
          <v-btn v-if="model[stoke.id]" @click.stop="add(stoke.id)" :dark="!(getSectionByStokeId(stoke.id).length === stoke.count)" :disabled="getSectionByStokeId(stoke.id).length === stoke.count" title="Добавить" color="indigo">
            {{ getSectionByStokeId(stoke.id).length !== stoke.count ? 'Добавить полку' : 'Склад заполнен' }}
          </v-btn>
        </template>

        <v-list-tile
          class="ma-1 headline"
          v-for="item in getSectionByStokeId(stoke.id)"
          :key="item.id"
        >
          <v-icon small align-center color="orange lighten-1">mdi-view-dashboard</v-icon>
          <v-list-tile-content>
            <table class="headline" >
              <tbody>
                <tr>
                  <td style="padding: 0 20px; color: #FFA726">Полка {{ item.id_stoke+'.'+item.number+'  ' }}</td>
                  <td style="padding: 0 20px">Занято мест: {{item.amount}}</td>
                  <td style="padding: 0 20px">Вместимость: {{item.capacity}}</td>
                </tr>
              </tbody>
            </table>
          </v-list-tile-content>
          <v-btn @click="edit(item)" icon title="Редактировать" >
            <v-icon color="orange">edit</v-icon>
          </v-btn>
          <v-btn @click="del(item)" title="Удалить" icon>
            <v-icon color="error">mdi-delete</v-icon>
          </v-btn>
        </v-list-tile>
      </v-list-group>
    </v-list>
    <EditAddSection
			:dialog="dialogAdd"
			@submit="addItem($event)"
			@cancel="dialogAdd = false"
		/>
		<EditAddSection
			:dialog="dialogEdit"
			@submit="editItem($event)"
			@cancel="dialogEdit = false"
		/>
    <ask-dialog
      :dialog="dialogDel"
      :text="'Вы точно хотите удалить полку?'"
      @dialog="deleteD($event)"
    ></ask-dialog>
  </v-card>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'
import EditAddSection from './section/EditAddSection.vue'
import AskDialog from '../../components/AskDialog.vue'
import { eventBus } from 'main'

export default {
  name: 'content-stoke',
  components: {
    AskDialog,
    EditAddSection
  },
  data () {
    return {
      dialogDel: false,
      dialogAdd: false,
      dialogEdit: false,
      model: {},
      item: {}
    }
  },
  computed: {
    ...mapGetters('stoke', ['getAllStoke']),
    ...mapGetters('section', ['getSectionByStokeId']),
    getStoke () {
      let array = Array.from(this.getAllStoke)
      return array
        .sort((a, b) => {
          return a.id - b.id
        })
    }
  },
  filters: {
    NumberSort (value) {
      value.filter((a, b) => {
        return a.number - b.number
      })
    }
  },
  methods: {
    ...mapActions('section', ['deleteSectionAction', 'setEditSectionAction', 'addSectionAction', 'saveSectionAction']),
    ...mapActions('stoke', ['saveStokeAction']),
    add (id) {
      this.setEditSectionAction({
        amount: 0,
        capacity: 0,
        id_stoke: id,
        id: -1,
        number: 0
      })
      this.dialogAdd = true
    },
    edit (item) {
      this.setEditSectionAction(item)
      this.dialogEdit = true
    },
    del (item) {
      this.item = item
      this.dialogDel = true
    },
    addItem (item) {
      this.dialogAdd = false
      this.addSectionAction(item)
    },
    editItem (item) {
      this.setEditSectionAction({})
      this.dialogEdit = false
      this.saveSectionAction(item)
    },
    deleteD (ok) {
      this.dialogAdd = true
      if (ok) {
        this.deleteSectionAction(this.item)
      }
    }
  }
}
</script>

<style scoped>
  .table-class {
    border-bottom: 0;
    border-top: 0
  }
</style>
<template>
  <v-dialog v-model="dialog" persistent fullscreen hide-overlay transition="dialog-bottom-transition">
    <v-card color="grey darken-2">
      <v-toolbar dark color="primary">
        <v-toolbar-title>Редактирование поставки</v-toolbar-title>
        <v-spacer></v-spacer>
        <v-toolbar-items>
          <v-btn dark flat @click="$emit('cancel', null)">
            Выйти
          </v-btn>
        </v-toolbar-items>
      </v-toolbar>
      <v-layout class="mx-2 mt-3">
        <v-flex>
          <v-card>
            <v-card-text>
              <v-dialog v-model="dialog1" persistent max-width="600px">
                <template v-slot:activator="{ on }">
                  <v-btn color="primary" dark v-on="on">{{ getIsContract != false ? 'Редактировать/Просмотреть договор' : 'Создать договор' }}</v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="headline">Договор</span>
                  </v-card-title>
                  <v-card-text>
                    <v-text-field readonly v-model="part1" required label="Сторона договора 1"
                      v-validate="'required|max:150'" :counter="150"></v-text-field>
                    <v-text-field readonly v-model="partP" required label="Сторона договора 2"
                      v-validate="'required|max:150'" :counter="150"></v-text-field>
                    <label class="ml-2" >Дата заключение</label>
                    <v-layout row>
                      <v-flex ml-1 order-lg2>
                        <v-menu
                          ref="menu2"
                          v-model="menu2"
                          :close-on-content-click="false"
                          :nudge-right="40"
                          :return-value.sync="timeP"
                          lazy
                          transition="scale-transition"
                          offset-y
                          full-width
                          max-width="290px"
                          min-width="290px"
                        >
                          <template v-slot:activator="{ on }">
                            <v-text-field
                              v-model="timeP"
                              label="Время"
                              prepend-icon="access_time"
                              readonly
                              v-on="on"
                            ></v-text-field>
                          </template>
                          <v-time-picker
                            v-if="menu2"
                            format="24hr"
                            v-model="timeP"
                            full-width
                            @click:minute="$refs.menu2.save(timeP)"
                          ></v-time-picker>
                        </v-menu>
                      </v-flex>
                      <v-flex  mr-1 xs6>
                        <v-menu
                          ref="menu1"
                          v-model="menu"
                          :close-on-content-click="false"
                          :nudge-right="40"
                          :return-value.sync="dateP"
                          lazy
                          transition="scale-transition"
                          offset-y
                          full-width
                          min-width="290px"
                        >
                          <template v-slot:activator="{ on }">
                            <v-text-field
                              v-model="dateP"
                              label="Дата"
                              prepend-icon="event"
                              readonly
                              v-on="on"
                            ></v-text-field>
                          </template>
                          <v-date-picker v-model="dateP" no-title scrollable>
                            <v-spacer></v-spacer>
                            <v-btn flat color="primary" @click="menu = false">Cancel</v-btn>
                            <v-btn flat color="primary" @click="$refs.menu1.save(dateP)">OK</v-btn>
                          </v-date-picker>
                        </v-menu>
                      </v-flex>
                    </v-layout>
                    <v-textarea
                      label="Тело"
                      rows="10"
                      no-resize
                      v-model="textP"
                    ></v-textarea>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" flat @click="dialog1 = false">Отмена</v-btn>
                    <v-btn color="blue darken-1" flat @click="save">Сохранить</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <span v-if="getIsContract != false" class="headline">Заключен: {{date + ' ' + time}}</span>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
      <table-view
        :title="'Позиции'"
        :array="getPosition"
        :editMethods="view"
        :headers="headers"
        :includesAttr="['id_supply']"
        @addObject="add()"
        @editObject="edit($event)"
        @deleteObject="delObj($event)"
      />
      <edit-add-position
        :dialog="dialogAdd"
        :item="getEditItem"
        @submit="addItem($event)"
        @cancel="dialogAdd = false"
      />
      <edit-add-position
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
import EditAddPosition from './EditAddPosition.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'AddSupply',
  components: {
    TableView,
    AscDialog,
    EditAddPosition
  },
  props: {
    dialog: {
      default: false
    }
  },
  watch: {
    getPruveyor (value) {
       this.part2 = value
    },
    getIsContract (value) {
      if (value != false) {
        let getg = this.getContractById(value)
        this.time = (getg && moment(getg.date).format('HH:mm')) || this.time
        this.date = (getg && moment(getg.date).format('YYYY-MM-DD')) || this.date
        this.text = (getg && getg.body) || this.text
      }
    }
  },
  computed: {
    ...mapGetters('app', ['getEditItem']),
    ...mapGetters('position', ['getPositionBySupplyId']),
    ...mapGetters('sparePart', ['getSparePartById']),
    ...mapGetters('contract', ['getContractById']),
    ...mapGetters('supply', ['getSupplyById']),
    ...mapGetters('purveyor', ['getPurveyorById']),
    getPosition () {
      return this.getPositionBySupplyId(this.getEditItem.id)
    },
    getIsContract () {
      let a = this.getSupplyById(this.getEditItem.id)
      return a && a.id_contract || false
    },
    getPruveyor () {
      let a = this.getPurveyorById(this.getEditItem.id_purveyor)
      return a && a.name
    },
    partP: {
      get() { return this.getPruveyor },
      set(value) { this.part2 = this.getPruveyor}
    },
    timeP: {
      get() { return this.time },
      set(value) { this.time = value }
    },
    dateP: {
      get() { return this.date },
      set(value) { this.date = value }
    },
    textP: {
      get() { return this.text },
      set(value) { this.text = value }
    }
  },
  methods: {
    ...mapActions('position', ['setEditItem']),
    ...mapActions('contract', ['addContractAction', 'saveContractAction']),
    ...mapActions('position', ['addPositionAction', 'savePositionAction', 'deletePositionMutation']),
    view (value, name, index) {
      if (name == 'id_spare_part') return (this.getSparePartById(value) && this.getSparePartById(value).name || null)
      return value
    },
    add (contract) {
      let supply = this.getSupplyById(this.getEditItem.id)
      supply.id_contract = contract.id
      this.addContractAction({
        contract: contract,
        supply: supply
      })
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
      this.addPositionAction(item)
    },
    editItem (item) {
      this.dialogEdit = false
      this.savePositionAction(item)
    },
    deleteItem (ok) {
      this.dialogDel = false
      if (ok) {
        this.deletePositionMutation(this.item)
      }
    },
    save () {
      this.dialog1 = false
      let contract = {
        id: this.getIsContract || -1, 
        body: this.text,
        date: moment(this.date + ' ' + this.time).format('YYYY-MM-DD HH:mm:ss')
      }
      this.getIsContract == false ? this.add(contract) : this.saveContractAction(contract)
    }
  },
  created () {
    this.time = (this.getContractById(this.getIsContract) && this.getContractById(this.getIsContract).date.format('HH:mm')) || this.time
    this.date = (this.getContractById(this.getIsContract) && this.getContractById(this.getIsContract).date.format('YYYY-MM-DD')) || this.date
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
          text: 'Деталь',
          value: 'spare_part',
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
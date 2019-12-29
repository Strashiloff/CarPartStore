<template>
  <v-dialog persistent max-width="60%" v-model="dialog">
    <v-card style="padding: 50px">
      <label class="ml-2 mb-2" >Выберите покупку (Добавьте # дляточного поиска по номеру)</label>
      <model-list-select
        name="model-list"
        class="select"
        :list="getBuys"
        v-model="id_buy"
        option-value="id"
        :custom-text="customer"
        placeholder="Выберите покупку"
      />
      <label class="ml-2 mb-2" >Позиция</label>
      <model-list-select
        :isDisabled="dis"
        name="model-list"
        class="select"
        :list="array(id_buy)"
        v-model="id_list"
        option-value="id"
        :custom-text="listF"
        placeholder="Выберите покупку"
      />
      <!--  -->
      <label class="ml-2" >Крайний срок отгрузки</label>
      <v-layout row>
        <v-flex ml-1 order-lg2>
          <v-menu
            ref="menu2"
            v-model="menu2"
            :close-on-content-click="false"
            :nudge-right="40"
            :return-value.sync="time"
            lazy
            transition="scale-transition"
            offset-y
            full-width
            max-width="290px"
            min-width="290px"
          >
            <template v-slot:activator="{ on }">
              <v-text-field
                v-model="time"
                label="Время"
                prepend-icon="access_time"
                readonly
                v-on="on"
              ></v-text-field>
            </template>
            <v-time-picker
              v-if="menu2"
              format="24hr"
              v-model="time"
              full-width
              @click:minute="$refs.menu2.save(time)"
            ></v-time-picker>
          </v-menu>
        </v-flex>
        <v-flex  mr-1 xs6>
          <v-menu
            ref="menu1"
            v-model="menu"
            :close-on-content-click="false"
            :nudge-right="40"
            :return-value.sync="date"
            lazy
            transition="scale-transition"
            offset-y
            full-width
            min-width="290px"
          >
            <template v-slot:activator="{ on }">
              <v-text-field
                v-model="date"
                label="Дата"
                prepend-icon="event"
                readonly
                v-on="on"
              ></v-text-field>
            </template>
            <v-date-picker v-model="date" no-title scrollable>
              <v-spacer></v-spacer>
              <v-btn flat color="primary" @click="menu = false">Cancel</v-btn>
              <v-btn flat color="primary" @click="$refs.menu1.save(date)">OK</v-btn>
            </v-date-picker>
          </v-menu>
        </v-flex>
      </v-layout>
      <v-text-field type="number" step="1" min="1" v-model="amount" required label="Налог"></v-text-field>
      <v-card-actions class="pt-5">
        <v-spacer></v-spacer>
        <v-btn @click="$emit('cancel', false)">Отмена</v-btn>
        <v-btn @click="add()">Сохранить</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import moment from 'moment'
import { mapGetters } from 'vuex'
import { ModelListSelect } from 'vue-search-select'

moment.locale('ru')
export default {
  name: 'EditAddDefect',
  data () {
    return {
      id_buy: -1,
      id_list: -1,
      amount: 1,
      id: -1,
      time: moment().format('HH:mm'),
      date: moment().format('YYYY-MM-DD'),
      id_request: -1,
      menu: false,
      menu2: false,
    }
  },
  props: {
    dialog: {
      default: false
    },
  },
  watch: {
    getEditItem (value) {
      this.id = value.id
      this.id_buy = value.id_buy
      this.id_list = value.id_list
      this.date = moment(value.date).format('YYYY-MM-DD')
      this.time = moment(value.date).format('HH:mm')
    }
  },
  components: {
    ModelListSelect
  },
  computed: {
    ...mapGetters('app', ['getEditItem', 'getCurrentUser']),
    ...mapGetters('purveyor', ['getAllPurveyors']),
    ...mapGetters('list', ['getListByRequesttId']),
    ...mapGetters('buy', ['getAllBuys', 'getBuyById']),
    ...mapGetters('request', ['getRequestById']),
    ...mapGetters('position', ['getPositionById']),
    ...mapGetters('sparePart', ['getSparePartById']),
    getBuys () {
      return this.getAllBuys.filter(elem => elem.completed == true) || []
    },
    dis () {
      return this.getBuyById(this.id_buy) == undefined
    },
    array () {
      return id => {
        let c = this.getBuyById(id)
        if (c != undefined) {
          let a = this.getRequestById(c.id_request)
          return this.getListByRequesttId(a.id)
        } 
        return []
      }
    }
  },
  methods: {
    add () {
      this.$emit('submit', {
        id_buy: this.id_buy,
        id_list: this.id_list,
        date: moment(this.date + ' ' + this.time).format('YYYY-MM-DD HH:mm:ss'),
        amount: this.amount,
        id: this.id,
      })
      this.id_buy = -1
      this.id_list = -1
      this.amount = 1
      this.date = moment().format('HH:mm'),
      this.time = moment().format('YYYY-MM-DD')
    },
    customer (value) {
      return value.id + '# | Дата: ' + value.date
    },
    listF (value) {
      let a = this.getPositionById(value.id_position)
      let sp = this.getSparePartById(a.id_spare_part)
      return value.id + ' | ' + sp.name + ' ' + sp.proportions + ' | price: '+ value.price
    }
    
  }
}
</script>

<style scoped>
  .select {
    margin: 2px 0 10px;
  }
</style>
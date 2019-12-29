<template>
  <v-dialog persistent max-width="60%" v-model="dialog">
    <v-card style="padding: 50px">
      <label class="ml-2 mb-2" >Покупатель</label>
      <model-list-select
        name="model-list"
        class="select"
        :list="getAllCustomers"
        v-model="id_customer"
        option-value="id"
        :custom-text="customer"
        placeholder="Выберите покупателя"
      />
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
      <!-- <v-text-field type="number" step="0.01" min="0.01" v-model="tax" required label="Налог"></v-text-field> -->
      <!-- <model-list-select
        name="model-list"
        class="select"
        :list="getAllPurveyors"
        v-model="id_purveyor"
        option-value="id"
        option-text="name"
        placeholder="Выберите поставшика"
      /> -->
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
  name: 'EditAddRequest',
  data () {
    return {
      id_user: -1,
      id_customer: -1,
      id: -1,
      time: moment().format('HH:mm'),
      date: moment().add(1, 'd').format('YYYY-MM-DD'),
      time1: moment().format('HH:mm'),
      date1: moment().format('YYYY-MM-DD'),
      menu: false,
      menu2: false,
      menu3: false,
      menu4: false
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
      this.id_user = value.id_user
      this.id_customer = value.id_customer
      this.date1 = moment(value.date).format('YYYY-MM-DD')
      this.time1 = moment(value.date).format('HH:mm')
      this.date = moment(value.period).format('YYYY-MM-DD')
      this.time = moment(value.period).format('HH:mm')
    }
  },
  components: {
    ModelListSelect
  },
  computed: {
    ...mapGetters('app', ['getEditItem', 'getCurrentUser']),
    ...mapGetters('purveyor', ['getAllPurveyors']),
    ...mapGetters('customer', ['getAllCustomers'])
  },
  methods: {
    add () {
      this.$emit('submit', {
        id_user: this.getCurrentUser.id,
        id_customer: this.id_customer,
        date: moment(this.date1 + ' ' + this.time1).format('YYYY-MM-DD HH:mm:ss'),
        period: moment(this.date + ' ' + this.time).format('YYYY-MM-DD HH:mm:ss'),
        id: this.id,
      })
      this.id = -1
      this.id_user = -1
      this.id_customer = -1
      this.date1 = moment().format('HH:mm'),
      this.time1 = moment().format('YYYY-MM-DD'),
      this.date = moment().format('HH:mm'),
      this.time = moment().format('YYYY-MM-DD')
    },
    customer (value) {
      return value && value.surname + ' ' + value.name + ' ' + value.patronymic || ''
    }
  }
}
</script>

<style scoped>
  .select {
    margin: 2px 0 10px;
  }
</style>
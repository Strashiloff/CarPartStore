<template>
  <v-dialog persistent max-width="60%" v-model="dialog">
    <v-card style="padding: 50px">
      <label class="ml-2 mb-2" >Заявка</label>
      <model-list-select
        name="model-list"
        class="select"
        :list="getAll"
        v-model="id_request"
        option-value="id"
        :custom-text="initials"
        placeholder="Выберите заявку"
      />
      <v-select
        v-model="completed"
        :items="status"
        item-text="text"
        item-value="completed"
        label="Статус"
      ></v-select>
      <label class="ml-2" >Дата покупки</label>
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
  name: 'EditAddBuy',
  data () {
    return {
      id_request: -1,
      completed: false,
      id: -1,
      time: moment().format('HH:mm'),
      date: moment().format('YYYY-MM-DD'),
      menu: false,
      menu2: false,
      status: [
        {
          completed: false,
          text: 'Отклонено'
        },
        {
          completed: true,
          text: 'Куплено'
        }
      ]
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
      this.id_request = value.id_request
      this.date = moment(value.date).format('YYYY-MM-DD')
      this.time = moment(value.date).format('HH:mm')
      this.completed = value.completed
    }
  },
  components: {
    ModelListSelect
  },
  computed: {
    ...mapGetters('app', ['getEditItem']),
    ...mapGetters('customer', ['getCustomerById']),
    ...mapGetters('request', ['getRequestById', 'getAllRequests']),
    ...mapGetters('buy', ['getAllBuys']),
    getAll () {
      return this.getAllRequests.filter(req => {
        return this.getAllBuys.find(buy => {
          return buy.id_request == req.id
        }) == undefined
      })
    }
  },
  methods: {
    add () {
      this.$emit('submit', {
        id_request: this.id_request,
        completed: this.completed,
        date: moment(this.date + ' ' + this.time).format('YYYY-MM-DD HH:mm:ss'),
        id: this.id,
      })
      this.id_request = -1
      this.completed = false
      this.id = -1
      this.time = moment().format('HH:mm')
      this.date = moment().format('YYYY-MM-DD')
    },
    initials (value) {
      let req = this.getRequestById(value.id)
      return req && req.id && req.id + ' | ' + req.date  +'  (' + this.gerUser(req.id_customer)+')' || ''
    },
    gerUser (id) {
      let us = this.getCustomerById(id)
      return us && us.name && us.surname + ' ' + us.name + ' ' + us.patronymic || 'Нет'
    },
  }
}
</script>

<style scoped>
  .select {
    margin: 2px 0 10px;
  }
</style>
<template>
  <v-dialog persistent max-width="60%" v-model="dialog">
    <v-card style="padding: 50px">
      <label class="ml-2" >Дата поставки</label>
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
      <v-text-field type="number" step="0.01" min="0.01" v-model="tax" required label="Налог"></v-text-field>
      <model-list-select
        name="model-list"
        class="select"
        :list="getAllPurveyors"
        v-model="id_purveyor"
        option-value="id"
        option-text="name"
        placeholder="Выберите поставшика"
      />
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
  name: 'AddSupply',
  data () {
    return {
      id_contract: null,
      id_purveyor: -1,
      tax: 0.01,
      id: -1,
      time: moment().format('HH:mm'),
      date: moment().format('YYYY-MM-DD'),
      menu: false,
      menu2: false
    }
  },
  props: {
    dialog: {
      default: false
    }
  },
  watch: {
    getEditItem (value) {
      this.id = value.id
      this.id_contract = value.id_contract
      this.id_purveyor = value.id_purveyor
      this.date = moment(value.date).format('YYYY-MM-DD')
      this.time = moment(value.date).format('HH:mm')
      this.tax = value.tax
    }
  },
  components: {
    ModelListSelect
  },
  computed: {
    ...mapGetters('app', ['getEditItem']),
    ...mapGetters('purveyor', ['getAllPurveyors']),
  },
  methods: {
    add () {
      this.$emit('submit', {
        id_contract: this.id_contract,
        id_purveyor: this.id_purveyor,
        date: moment(this.date + ' ' + this.time).format('YYYY-MM-DD HH:mm:ss'),
        tax: this.tax,
        id: this.id,
      })
      this.id_contract = null,
      this.id_spare_part = -1
      this.date = moment().format('HH:mm'),
      this.time = moment().format('YYYY-MM-DD'),
      this.tax = 0.01
      this.id = -1
    }
  }
}
</script>

<style scoped>
  .select {
    margin: 2px 0 10px;
  }
</style>
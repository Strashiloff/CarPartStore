<template>
  <v-dialog persistent max-width="60%" v-model="dialog">
    <v-card style="padding: 50px">
      <label class="ml-2 mb-2" >Выберите запчасть</label>
      <model-list-select
        name="model-list"
        class="select"
        :list="getAllSpareParts"
        v-model="id_spare_part"
        option-value="id"
        :custom-text="sparePart"
        placeholder="Выберите запчасть"
      />
      <label class="ml-2" >Позиция</label>
      <model-list-select
        :isDisabled="dis"
        name="model-list"
        class="select"
        :list="getPositionBySparePartId(id_spare_part)"
        v-model="id_position"
        option-value="id"
        :custom-text="position"
        placeholder="Выберите позицию"
      />
      <v-text-field type="number" step="1" min="1" :max="getMaxCount" v-model="amountP" label="Количество"
        :rules="[rules.max, rules.fill]"
      ></v-text-field>
      <v-text-field type="number" step="0.01" min="0.01" v-model="price" label="Цена"></v-text-field>
      <v-card-actions class="pt-5">
        <v-spacer></v-spacer>
        <v-btn @click="$emit('cancel', false)">Отмена</v-btn>
        <v-btn :disabled="dis" @click="add()">Сохранить</v-btn>
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
  name: 'EditAddList',
  data () {
    return {
      id_request: -1,
      id_position: -1,
      id_spare_part: -1,
      amount: 0,
      price: 0.0,
      id: -1,
      rules: {
        max: v => v <= this.getMaxCount || 'В поставке нет столько запчастей',
        pas: v => v.toString() === this.password.toString() || 'Passwords don\'t match',
        fill: v => v !== null || 'Заполните поле',
      }
    }
  },
  props: {
    dialog: {
      default: false
    },
  },
  watch: {
    getEditList (value) {
      this.id = value.id
      this.id_request = value.id_request
      this.id_position = value.id_position
      this.amount = value.amount
      this.price = value.price
      this.id_spare_part =  this.getSparePartById(this.getPositionById(value.id_position).id_spare_part).id
    },
  },
  components: {
    ModelListSelect
  },
  computed: {
    ...mapGetters('app', ['getEditItem']),
    ...mapGetters('list', ['getEditList']),
    ...mapGetters('position', ['getPositionBySparePartId', 'getPositionById']),
    ...mapGetters('sparePart', ['getAllSpareParts', 'getSparePartById']),
    ...mapGetters('supply', ['getSupplyById']),
    dis () {
      return this.getPositionBySparePartId(this.id_spare_part).length == 0
    },
    getMaxCount () {
      return this.getPositionById(this.id_position) && this.getPositionById(this.id_position).amount || 0
    },
    amountP: {
      get () { return this.amount },
      set (value) {
        let a = this.getPositionById(this.id_position).amount
        value <= a ? this.amount = value : this.amount = a
      }
    }
  },
  methods: {
    add () {
      this.$emit('submit', {
        id_request: this.getEditItem.id,
        id_position: this.id_position,
        amount: this.amount,
        price: this.price,
        id: this.id
      })
      this.id_request = -1
      this.id_position = -1
      this.amount = 0
      this.price = 0.0
      this.id = -1
    },
    sparePart (item) {
      let spare_part = this.getSparePartById(item.id)
      return (spare_part && spare_part.name && spare_part.name + ' | ' + spare_part.proportions) || ''
    },
    position (value) {
      let b = this.getSupplyById(value.id_supply)
      return 'Номер поставки: ' + b.id + '  |  Дата: ' + b.date
    }
  }
}
</script>

<style scoped>
  .select {
    margin: 2px 0 10px;
  }
</style>
<template>
  <v-dialog persistent max-width="80%" v-model="dialog">
    <v-card style="padding: 50px">
      <label for="model-list">Запчасть</label>
      <model-list-select
        name="model-list"
        class="select"
        :list="getAllSpareParts"
        v-model="id_spare_part"
        option-value="id"
        :custom-text="sparePart"
        placeholder="Выберите запчасть"
      />
      <v-text-field type="number" min="1" v-model="amount" required label="Количество"
        v-validate="'required|max:200'" :counter="200"></v-text-field>
      <v-text-field type="number" v-model="price" step="0.01" required label="Цена продавца"
        v-validate="'required|max:150'" min="0.01" :counter="150"></v-text-field>
      <v-card-actions class="pt-5">
        <v-spacer></v-spacer>
        <v-btn @click="$emit('cancel', false)">Отмена</v-btn>
        <v-btn @click="add()">Сохранить</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapGetters } from 'vuex'
import { ModelListSelect } from 'vue-search-select'

export default {
  name: 'EditAddPurveyor',
  data () {
    return {
      id_supply: -1,
      id_spare_part: -1,
      amount: 0,
      price: 0.01,
      id: -1
    }
  },
  props: {
    dialog: {
      default: false
    },
    item: null
  },
  watch: {
    getEditItem (value) {
      this.id = value.id
      this.id_supply = value.id_supply
      this.id_spare_part = value.id_spare_part
      this.amount = value.amount
      this.price = value.price
    },
  },
  components: {
    ModelListSelect
  },
  computed: {
    ...mapGetters('position', ['getEditItem']),
    ...mapGetters('sparePart', ['getAllSpareParts', 'getSparePartById']),
    ...mapGetters('category', ['getAllCategories'])
  },
  methods: {
    add () {
      this.$emit('submit', {
        id_supply: this.item.id,
        id_spare_part: this.id_spare_part,
        amount: this.amount,
        price: this.price,
        id: this.id
      })
      this.id = -1
      this.id_supply = -1
      this.id_spare_part = -1
      this.amount = 0
      this.price = 0.01
    },
    sparePart (item) {
      let spare_part = this.getSparePartById(item.id)
      return (spare_part && spare_part.name && spare_part.name + ' | ' + spare_part.proportions) || ''
    }
  }
}
</script>

<style scoped>
  .select {
    margin: 2px 0 10px;
  }
</style>
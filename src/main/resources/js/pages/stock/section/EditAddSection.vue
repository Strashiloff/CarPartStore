<template>
  <v-dialog persistent v-model="dialog" max-width="400">
    <v-card style="padding: 50px">
      <v-text-field type="number" min="0" max="50000" v-model="capacity" label="Макс количество деталей"></v-text-field>
      <v-text-field type="text" v-model="id_stoke" readonly label="Номер склада (Нельзя редактировать)"></v-text-field>
      <v-text-field type="text" v-model="number" readonly label="Номер полки (Нельзя редактировать)"></v-text-field>
      <!-- <v-text-field type="number" min="0" max="200" v-model="count" label="Макс количество полок"></v-text-field> -->
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn @click="$emit('cancel', false)">Отмена</v-btn>
        <v-btn @click="add()">Сохранить</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapGetters } from 'vuex'
import { get } from 'http'

export default {
  name: 'EditAddSection',
  props: {
    dialog: {
      default: false
    }
  },
  data () {
    return {
      amount: '',
      capacity: 0,
      id_stoke: -1,
      id: -1,
      number: 0
    }
  },
  methods: {
    add () {
      this.$emit('submit', {
        amount: this.amount,
        capacity: Number.parseInt(this.capacity),
        id_stoke: this.id_stoke,
        id: this.id,
        number: this.number
      })
      this.amount = 0
      this.capacity = 0
      this.id_stoke = -1
      this.id = -1
      this.number = 0
    },
    getNewNumber (id) {
      let number = 0
      this.getSectionByStokeId(id).forEach(section => {
        if (number < section.number) number = section.number 
      })
      number++
      return number
    }
  },
  watch: {
    getEditSection (value) {
      this.amount = value.amount
      this.capacity = value.capacity
      this.id_stoke = value.id_stoke
      this.id = value.id
      this.number = this.id == -1 ? this.getNewNumber(value.id_stoke) : value.number
  }
  },
  computed: {
    ...mapGetters('section', ['getSectionByStokeId', 'getEditSection']),
    ...mapGetters('stoke', ['getStokeById'])
  }
}
</script>

<style scoped>

</style>
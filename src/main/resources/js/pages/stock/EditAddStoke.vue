<template>
  <v-dialog persistent v-model="dialog" max-width="50%">
    <v-card style="padding: 50px">
      <v-text-field autofocus data-vv-name="name" clearable v-model="location" required label="Местоположение"
                v-validate="'required|max:200'" :counter="200"></v-text-field>
      <v-text-field @keydown="click($event)" type="number" :min="getSectionByStokeId(id).length" max="200" v-model="count" label="Макс количество полок"></v-text-field>
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
export default {
  name: 'edit-add-stoke',
  props: {
    dialog: {
      default: false
    }
  },
  data () {
    return {
      location: ' ',
      count: 0,
      id: -1
    }
  },
  methods: {
    click (e) {
      if (e.key == 'ArrowDown') {
        if (this.count - 1 < this.getSectionByStokeId(this.id).length) {
          e.preventDefault()
        }
      }
      if (this.getSectionByStokeId(this.id) && this.getSectionByStokeId(this.id).length > Math.floor(this.count / 10)) {
        this.count = this.getSectionByStokeId(this.id).length
        e.preventDefault()
      }
      if (e.key == 'Backspace' && Math.floor(this.count / 10) == 0) {
        if (this.id !== -1) this.count = this.getSectionByStokeId(this.id).length || 0
        else this.count = 0
        e.preventDefault()
      }
      if (['.', ',', 'e', 'E', '-', '+'].includes(e.key)) {
        e.preventDefault()
      }
      if (this.count == 0 && ['1', '2', '3', '4', '5', '6', '7', '8', '9'].includes(e.key)) {
        this.count = Number.parseInt(e.key)
        e.preventDefault()
      }
      if (this.count * 10 > 200 && ['1', '2', '3', '4', '5', '6', '7', '8', '9', '0'].includes(e.key)) {
        e.preventDefault()
      }
    },
    add () {
      this.$emit('submit', { location: this.location, count: Number.parseInt(this.count), id: this.id })
      this.location = ''
      this.count = 0
      this.id = -1
    }
  },
  watch: {
    getEdit (value) {
      this.location = value.location
      this.count = value.count
      this.id = value.id
    }
  },
  computed: {
    ...mapGetters('section', ['getSectionByStokeId']),
    ...mapGetters('stoke', ['getEdit'])
  }
}
</script>

<style lang="scss" scoped>

</style>
<template>
  <v-dialog persistent max-width="60%" v-model="dialog">
    <v-card style="padding: 50px">
      <v-select
        autofocus
        v-model="id_type"
        :items="getAllTypes"
        item-text="name"
        item-value="id"
        label="Тип детали"
      ></v-select>
      <v-select
        v-model="id_stoke"
        :items="getAllStoke"
        :item-text="text"
        item-value="id"
        label="Склад"
      ></v-select>
      <v-select
        v-model="id_section"
        :items="getSectionByStokeId(id_stoke)"
        :item-text="section_text"
        item-value="id"
        label="Полка"
      ></v-select>
      <v-text-field clearable v-model="name" required label="Наименование детали"
                v-validate="'required|max:200'" :counter="300"></v-text-field>
      <v-text-field clearable v-model="proportions" required label="Пропорции детали"
                v-validate="'required|max:200'" :counter="300"></v-text-field>
      <v-text-field type="number" :min="0" max="200" v-model="price" label="Цена (₽)"></v-text-field>
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
  name: 'EditAddSparePart',
  props: {
    dialog: {
      default: false
    }
  },
  data () {
    return {
      id_stoke: -1,
      id: -1,
      id_type: -1,
      id_section: -1,
      name: '',
      price: 0,
      proportions: ''
    }
  },
  watch: {
    getEditItem (value) {
      this.id = value.id,
      this.id_section = value.id_section,
      this.id_type = value.id_type,
      this.id_stoke = this.getSectionById(value.id_section).id_stoke
      this.name = value.name,
      this.price = value.price,
      this.proportions = value.proportions
    }
  },
  computed: {
    ...mapGetters('app', ['getEditItem']),
    ...mapGetters('type', ['getAllTypes']),
    ...mapGetters('section', ['getSectionByStokeId', 'getSectionById']),
    ...mapGetters('stoke', ['getAllStoke'])
  },
  methods: {
    text (value) {
      return '(' + value.id+ ') ' + value.location
    },
    section_text (value) {
      return 'Полка ' + value.number 
    },
    add () {
      this.$emit('submit', {
        id: this.id,
        id_type: this.id_type,
        id_section: this.id_section,
        name: this.name,
        price: this.price,
        proportions: this.proportions
      })
      this.id = -1,
      this.id_type = -1,
      this.id_section = -1,
      this.id_stoke = -1
      this.name = '',
      this.price = 0,
      this.proportions = ''
    }
  }
}
</script>

<style>

</style>
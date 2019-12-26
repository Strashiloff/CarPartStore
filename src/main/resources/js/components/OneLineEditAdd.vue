<template>
  <v-dialog persistent v-model="dialog" max-width="400">
    <v-card style="padding: 50px">
      <v-text-field autofocus data-vv-name="name" clearable v-model="name" required :label="label"
                v-validate="'required|max:200'" :counter="200"></v-text-field>
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
  name: 'OneLineEditAdd',
  props: {
    dialog: {
      default: false
    },
    label: {
      type: String,
      default: 'Название'
    }
  },
  data () {
    return {
      name: '',
      id: -1
    }
  },
  watch: {
    getEditItem (value) {
      this.name = value.name
      this.id = value.id
    }
  },
  computed: {
    ...mapGetters('app', ['getEditItem'])
  },
  methods: {
    add () {
      this.$emit('submit', { name: this.name, id: this.id })
      this.name = ''
      this.id = -1
    }
  }
}
</script>

<style>

</style>
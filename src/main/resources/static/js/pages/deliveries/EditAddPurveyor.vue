<template>
  <v-dialog persistent max-width="80%" v-model="dialog">
    <v-card style="padding: 50px">
      <v-select
        autofocus
        v-model="id_category"
        :items="getAllCategories"
        item-text="name"
        item-value="id"
        label="Тип поставщика"
      ></v-select>
      <label for="model-list">Страна</label>
      <model-list-select
        name="model-list"
        class="select"
        :list="getAllCountries"
        v-model="id_country"
        option-value="id"
        option-text="name"
        placeholder="Выберите страну"
      />
      <v-text-field clearable v-model="warranty" required label="Гарантии"
        v-validate="'required|max:200'" :counter="200"></v-text-field>
      <v-text-field clearable v-model="name" required label="Название поставщика"
        v-validate="'required|max:150'" :counter="150"></v-text-field>
      <v-text-field clearable v-model="adress" required label="Физический адрес"
        v-validate="'required|max:150'" :counter="150"></v-text-field>
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
      id: -1,
      id_category: -1,
      id_country: -1,
      warranty: '',
      name: '',
      adress: '',
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
      this.id_category = value.id_category
      this.id_country = value.id_country
      this.warranty = value.warranty
      this.name = value.name
      this.adress = value.adress
    },
  },
  components: {
    ModelListSelect
  },
  computed: {
    ...mapGetters('app', ['getEditItem']),
    ...mapGetters('country', ['getAllCountries', 'getCountryById']),
    ...mapGetters('category', ['getAllCategories'])
  },
  methods: {
    add () {
      this.$emit('submit', {
        id: this.id,
        id_category: this.id_category,
        id_country: this.id_country,
        warranty: this.warranty,
        name: this.name,
        adress: this.adress,
      })
      this.id = -1
      this.id_category = -1
      this.id_country = -1
      this.warranty = ''
      this.name = ''
      this.adress = ''
    }
  }
}
</script>

<style scoped>
  .select {
    margin: 2px 0 10px;
  }
</style>
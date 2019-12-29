<template>
  <v-dialog persistent max-width="40%" v-model="dialog">
    <v-card style="padding: 50px">
      <v-text-field clearable autofocus v-model="surname" required label="Фамилия"
        v-validate="'required|max:200'" :counter="200"></v-text-field>
      <v-text-field clearable v-model="name" required label="Имя"
        v-validate="'required|max:150'" :counter="150"></v-text-field>
      <v-text-field clearable v-model="patronymic" required label="Отчество"
        v-validate="'required|max:150'" :counter="150"></v-text-field>
      <v-select
        v-model="sex"
        :items="sexs"
        item-text="name"
        item-value="sex"
        label="Выберите пол"
      ></v-select>
      <v-text-field clearable v-model="contact" required label="Телефон"
        v-validate="'required|max:150'" 
        :counter="150"
        return-masked-value
        mask="+7(###)-###-##-##"
      ></v-text-field>
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
  name: 'EditAddCustomer',
  data () {
    return {
      id: -1,
      surname: '',
      name: '',
      patronymic: '',
      contact: '+7',
      sex: true,
      sexs: [
        {
          name: 'Мужчина',
          sex: true
        },
        {
          name: 'Женщина',
          sex: false
        }
      ]
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
      this.surname = value.surname
      this.name = value.name
      this.patronymic = value.patronymic
      this.contact = value.contact
      this.sex = value.sex
    },
  },
  components: {
    ModelListSelect
  },
  computed: {
    ...mapGetters('app', ['getEditItem']),
  },
  methods: {
    add () {
      this.$emit('submit', {
        id: this.id,
        surname: this.surname,
        name: this.name,
        patronymic: this.patronymic,
        contact: this.contact,
        sex: this.sex
      })
      this.id = -1
      this.surname = ''
      this.name = ''
      this.patronymic = ''
      this.contact = ''
      this.sex = true
    }
  }
}
</script>

<style scoped>
  .select {
    margin: 2px 0 10px;
  }
</style>
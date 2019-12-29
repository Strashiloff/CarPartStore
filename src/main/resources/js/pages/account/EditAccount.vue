<template>
  <v-dialog persistent v-model="dialog" max-width="50%">
    <v-card style="padding: 50px">
      <v-text-field data-vv-name="name"  :rules="[rules.fill]" clearable v-model="  username" autofocus required label="Логин"
                  v-validate="'required|max:20'" :counter="20"></v-text-field>
      <v-text-field oninput="this.value = this.value.replace(' ', '')"
                    v-model="password"
                    :append-icon="show1 ? 'visibility' : 'visibility_off'"
                    :rules="[rules.min]"
                    :type="show1 ? 'text' : 'password'"
                    name="password"
                    label="Пароль"
                    hint="Не менее 6 символов"
                    counter
                    @click:append="show1 = !show1"
      ></v-text-field>
      <v-text-field
                  v-model="conf"
									:type="'password'"
                  :rules="[rules.pas, rules.fill]"
                  label="Подтверждение пароля"
      ></v-text-field>
      <v-text-field :rules="[rules.fill]"  clearable v-model="name" :type="'text'" data-vv-name="name" name="name"
                    label="Имя"></v-text-field>
      <v-text-field :rules="[rules.fill]"  clearable v-model="surname" :type="'text'" data-vv-name="surname"
                    name="password" label="Фамилия"></v-text-field>
      <v-textarea
        label="Описание"
        rows="10"
        no-resize
        v-model="status"
      ></v-textarea>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn @click="$emit('cancel', false)">Отмена</v-btn>
        <v-btn :disabled="password != conf || password != ''" @click="add()">Сохранить</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'EditAccount',
  props: {
    dialog: {
      default: false
    }
  },
  data () {
    return {
      id: -1,
      username: '',
      password: '',
      conf: '',
      action: true,
      name: '',
      surname: '',
      status: null,
      image: null,
      color: null,
      roles: null,
      position: null,
      show1: false,
      rules: {
        min: v => v.length >= 6 || 'Min 6 characters',
        pas: v => v.toString() === this.password.toString() || 'Passwords don\'t match',
        fill: v => v !== null || 'Fill in the field',
      }
    }
  },
  watch: {
    getEditItem (value) {
      this.id = value.id
      this.username = value.username
      // this.password = value.password
      this.conf = value.conf
      this.action = value.action
      this.name = value.name
      this.surname = value.surname
      this.status = value.status
      this.image = value.image
      this.color = value.color
      this.roles = value.roles
      this.position = value.position
    }
  },
  computed: {
    ...mapGetters('app', ['getEditItem', 'getRoles']),
    ...mapGetters('posts', ['getAllPosts']),
  },
  methods: {
    add () {
      this.$emit('submit', {
        id: this.id,
        username: this.username,
        password: this.password,
        action: this.action,
        name: this.name,
        surname: this.surname,
        status: this.status == '' ? null : this.status,
        image: this.image,
        color: this.color,
        roles: this.roles,
        position: this.position
      })
      this.id = -1
      this.username = ''
      this.password = ''
      this.conf = ''
      this.action = true
      this.name = ''
      this.surname = ''
      this.status = null
      this.image = null
      this.color = null
      this.roles = null
      this.position = null
    }
  }
}
</script>

<style>

</style>
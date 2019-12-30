<template>
  <div>
    <v-layout align-space-around justify-center column fill-height>
      <v-flex xs1>
        <v-card style="margin: 5% 10%" class="pb-4 back-color">
          <v-card-title primary-title style="position: relative; border-radius: 50%;">
            <v-hover v-slot:default="{ hover }">
              <v-avatar size="250px" class="mx-5 mt-5 mb-2" :color="color">
                <div transition="scale-transition" style="position: absolute; z-index: 4">
                  <div class="cl--reveal" v-if="hover">
                    <v-btn icon
                      fab
                      @click="download"
                      style=" margin: 0; z-index: 5"
                    >
                      <v-icon x-large color="black">mdi-pencil</v-icon>
                    </v-btn>
                    <v-btn icon
                      fab
                      v-if="getCurrentUser.image"
                      @click="deleteM"
                      style="margin: 0; z-index: 5"
                    >
                      <v-icon x-large color="black">mdi-delete</v-icon>
                    </v-btn>
                    <v-btn icon
                      fab
                      v-if="!getCurrentUser.image"
                      @click="picker = !picker"
                      style="margin: 0; z-index: 5"
                    >
                      <v-icon x-large color="black">mdi-eyedropper</v-icon>
                    </v-btn>
                  </div>
                </div>
                <v-img v-if="getCurrentUser.image" style="position: absolute;" :src="getCurrentUser.image"/>
                <v-icon
                  v-else
                  style="position: absolute;"
                  class="display-4"
                  v-text="getUserNameShort"
                ></v-icon>
              </v-avatar>
            </v-hover>
            <div>
              <div class="d-flex">
                <span
                  class="white--text display-3"
                  style="font-family: Serif;"
                >{{ getUserNameFull }}</span>
                <v-btn icon
                  fab
                  title="Редактировать данные"
                  @click="edit"
                  class="mt-2 mr-0"
                >
                  <v-icon color="grey">mdi-pencil</v-icon>
                </v-btn>
                <v-btn icon
                  fab
                  title="Изменить пароль"
                  @click="password"
                  class="mt-2 ml-0"
                >
                  <v-icon color="grey">mdi-key</v-icon>
                </v-btn>
              </div>
              <div v-if="getCurrentUser" class="title grey--text">{{text}}</div>
            </div>
          </v-card-title>
          
          <v-card-text primary-title class="pl-5">
            <div class="headline" v-html="text1"></div>
          </v-card-text>
          <v-dialog
            v-model="picker" 
            hide-overlay
            max-width="230px"
            transition="dialog-transition"
          >
            <v-card>
              <photoshop-picker
                style="margin: auto"
                v-show="picker"
                v-model="colors"
              />
              <v-card-actions>
                <v-btn @click="picker = false">Отмена</v-btn>
                <v-btn @click="saveColor">Сохранить</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-card>
      </v-flex>
    </v-layout>
    <edit-account
      :dialog="dialog"
			@submit="editItem($event)"
			@cancel="dialog = false"
    />
    <edit-password
      :dialog="dialog2"
      @submit="editItem($event)"
      @cancel="dialog2 = false"
    />
  </div>
</template>

<script>
import EditAccount from './EditAccount.vue'
import EditPassword from './EditPassword.vue'
import { mapGetters, mapActions } from 'vuex'
import { Sketch } from 'vue-color'

export default {
  name: 'Account',
  data () {
    return {
      input: null,
      file: null,
      reader: null,
      image: null,
      dialog: false,
      dialog2: false,
      colors: {
        hex: '#1976D2'
      },
      picker: false
    }
  },
  components: {
    'photoshop-picker': Sketch,
    EditAccount,
    EditPassword
  },
  computed: {
    ...mapGetters('app', ['getUserNameShort', 'getUserNameFull', 'getCurrentUser']),
    ...mapGetters('users', ['getUserById']),
    color: {
      get () {
        return this.getUserById(this.getCurrentUser.id) && this.getUserById(this.getCurrentUser.id).color || this.colors.hex
      },
      set (value) { this.colors = value }
    },
    text () {
      return this.getCurrentUser.position && this.getCurrentUser.position.post || ''
    },
    text1 () {
      return this.getCurrentUser.status && this.getCurrentUser.status.split('\n').join('<br>') || 'Нет описания'
    }
  },
  methods: {
    ...mapActions('users', ['updateUserAction']),
    ...mapActions('app', ['setEditItem']),
    download () {
      if (this.input == null) this.input = document.createElement('input')
      this.input.accept = '.png, .jpg, .gif'
      this.input.type = 'file'
      this.input.ref = 'file'
      this.input.classList.add("mystyle")
      this.input.click()
      this.input.onchange = this.handler
 
    },
    handler () {
      this.file = this.input.files[0];
      this.reader = new FileReader();
      this.reader.onloadend = this.saveImage
      this.reader.readAsDataURL(this.file);
    },
    saveImage () {
      let res = this.reader.result
      this.file = null
      this.input.remove()
      this.input = null
      let user = this.getUserById(this.getCurrentUser.id)
      user.image = res
      this.updateUserAction(user)
    },
    saveColor () {
      this.picker = false
      let user = this.getUserById(this.getCurrentUser.id)
      user.color = this.colors.hex
      this.updateUserAction(user)
    },
    deleteM () {
      let user = this.getUserById(this.getCurrentUser.id)
      user.image = null
      this.updateUserAction(user)
    },
    edit () {
      this.dialog = true
      this.setEditItem(this.getUserById(this.getCurrentUser.id))
    },
    password (item) {
      this.dialog2 = true
      this.setEditItem(this.getUserById(this.getCurrentUser.id))
    },
    editItem (item) {
      this.dialog = false
      this.dialog2 = false
      this.updateUserAction(item)
    }
  }
}
</script>

<style scoped>
  .cl--reveal {
    background-color: rgba(255, 255, 255, 0.5);
    border-radius: 50%;
    line-height: 250px;
    height: 250px;
    width: 250px;
    z-index: 999
  }
  .custom-avatar {
    background-size: contain;
    /* background-repeat: no-repeat;
    background-position: center  */
  }
  .v-overlay--active {
    opacity: 0 !important;
  }

  .back-color {
    background: rgba(66, 66, 66, 0.9) !important
  }
</style>
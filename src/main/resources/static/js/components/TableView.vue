<template>
  <v-card class="ml-2 mr-2" :class="classS" fill-height>
    <v-card-title class="display-1">
      {{title}}
      <v-btn class="ml-4 mx-2" title="Добавить" fab dark @click="add" small color="indigo">
        <v-icon dark>mdi-plus</v-icon>
      </v-btn>
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-text-field
          title="Добавить"
          v-model="search"
          append-icon="search"
          label="Поиск"
          single-line
          hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="array"
        :search="search"
        :rows-per-page-items="[10, 20, {text: 'all', value: -1}]"
    >
            <!-- :pagination.sync="pagination" -->
      <template v-slot:items="props">
        <td class="text-xs-left"><i>{{props.item.id}}</i></td>
        <td class="text-xs-left title"
          :class="{'pl-5': typeof value === 'number' && typeof editMethods != 'function'}"
          v-bind:key="name"
          v-for="(value, name, index) in props.item"
          v-if="viewAttr(name)"
        >
          {{ typeof editMethods == 'function' ? editMethods(value, name, index) : value }}
        </td>
        <td class="text-xs-center title"> 
          <v-btn icon title="Редактировать" @click="edit(props.item)" :disabled="dialog2">
            <v-icon color="orange">edit</v-icon>
          </v-btn>
          <v-btn v-if="list" @click="expand(props.item)"  title="Редактировать список" :disabled="dialog" icon>
            <v-icon color="blue">mdi-beaker-plus</v-icon>
          </v-btn>
          <v-btn @click="remove(props.item)"  title="Удалить" :disabled="dialog" icon>
            <v-icon color="error">mdi-delete</v-icon>
          </v-btn>
        </td>
      </template>
      <template v-slot:no-results>
        <v-alert :value="true" color="error" class="title" icon="warning">По запросу ничего не найдено</v-alert>
      </template>
    </v-data-table>
    <!-- <div class="text-xs-center pt-2">
      <v-pagination v-model="pagination.page" :length="pages"></v-pagination>
    </div> -->
  </v-card>
</template>

<script>
	export default {
		name: "TableView",
    data () {
			return {
				search: '',
				dialog: false,
        dialog2: false,
        // pagination: {},
      }
    },
    props: {
      headers: {},
      array: {},
      title: {},
      classS: {},
      editMethods: {},
      list: {
        default: false
      },
      includesAttr: {
        type: Array,
        default: () => { return [] }
      }
    },
    computed: {
      viewAttr () {
        let arr = this.includesAttr
        arr.push('id')
        return name => !arr.includes(name)
      }
    },
    methods: {
      add () {
        this.$emit('addObject')
      },
      edit (item) {
        this.$emit('editObject', item)
      },
      expand (item) {
        this.$emit('expandObject', item)
      },
      remove (item) {
        this.$emit('deleteObject', item)
      }
    },
    // computed: {
    //   pages () {
    //     if (this.pagination.rowsPerPage == null ||
    //       this.pagination.totalItems == null
    //     ) return 0

    //     return Math.ceil(this.pagination.totalItems / this.pagination.rowsPerPage)
    //   }
    // }
	}
</script>

<style scoped>
</style>
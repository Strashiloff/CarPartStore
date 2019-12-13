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
      <template v-slot:items="props">
        <td class="text-xs-left"><i>{{props.item.id}}</i></td>
        <td class="text-xs-left title"
          :class="{'pl-5': typeof value === 'number' && typeof editMetods != 'function'}"
          v-bind:key="name"
          v-for="(value, name, index) in props.item"
          v-if="name !== 'id'"
        >
          {{ typeof editMetods == 'function' ? editMetods(value, name, index) : value }}
        </td>
        <td class="text-xs-center title"> 
          <v-btn icon title="Редактировать" @click="edit(props.item)" :disabled="dialog2">
            <v-icon color="orange">edit</v-icon>
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
      }
    },
    props: {
      headers: {},
      array: {},
      title: {},
      classS: {},
      editMetods: {},
    },
    methods: {
      add () {
        this.$emit('addObject')
      },
      edit (item) {
        this.$emit('editObject', item)
      },
      remove (item) {
        this.$emit('deleteObject', item)
      }
    }
	}
</script>

<style lang="scss" scoped>
</style>
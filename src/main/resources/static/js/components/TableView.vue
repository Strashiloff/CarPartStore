<template>
  <v-card class="mt-2 ml-2 mr-2">
    <v-card-title class="display-1">
      {{title}}
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-text-field
          v-model="search"
          append-icon="search"
          label="Search"
          single-line
          hide-details
      ></v-text-field>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="array"
        :search="search"
    >
      <template v-slot:items="props">
        <td class="text-xs-left"><i>{{props.item.id}}</i></td>
        <td class="text-xs-left title" v-bind:key="value" v-for="(value, name) in props.item" v-if="name !== 'id'" >{{ value }}</td>
        <v-btn icon @click="edit(props.item)" :disabled="dialog2">
          <v-icon>edit</v-icon>
        </v-btn>
        <v-btn @click="remove(props.item)" :disabled="dialog" icon>
          <v-icon>delete</v-icon>
        </v-btn>
      </template>
      <template v-slot:no-results>
        <v-alert :value="true" color="error" icon="warning"></v-alert>
      </template>
    </v-data-table>
    <v-dialog persistent v-model="dialog2" max-width="400">
      <v-card style="padding: 50px">
<!--        <user-dialog :userFrom="null" :mode="true" :editMethod="null"></user-dialog>-->
      </v-card>
    </v-dialog>
<!--    <ask-dialog :dialog="dialog" :text="text"></ask-dialog>-->
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
    props: ['headers', 'array', 'actions', 'title'],
    methods: {
      getData (item) {
        console.log(item)
        return 'tetx'
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

<style scoped lang="scss">

</style>
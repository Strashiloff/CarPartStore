<template>
  <div>
    <v-card class="mt-0">
      <div class="title-part">Управление поставками</div>
      <v-tabs centered>
        <v-tab to="/deliveries/supplies">Список поставок</v-tab>
        <v-tab to="/deliveries/countries">База стран / Катерогии поставщиков</v-tab>
        <v-tab to="/deliveries/purveyors">Поставщики</v-tab>
      </v-tabs>
    </v-card>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'Deliveries'
}
</script>

<style scoped>
  .title-part {
    text-align: center;
    font-size: 2em;
    padding: 10px
  }
</style>
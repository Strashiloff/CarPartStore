<template>
  <div>
    <v-card class="mt-0">
      <div class="title-part">Управление складами</div>
      <v-tabs centered>
        <v-tab to="/stokes/settings">Настройка складов</v-tab>
        <v-tab to="/stokes/content">Содержание складов</v-tab>
        <v-tab to="/stokes/sparepart">База запчастей</v-tab>
      </v-tabs>
    </v-card>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: "Stock"
  }
</script>

<style scoped>
  .title-part {
    text-align: center;
    font-size: 2em;
    padding: 10px
  }
</style>
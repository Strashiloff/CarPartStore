<template>
  <div>
    <v-card class="mt-0">
      <div class="title-part">Управление заказами</div>
      <v-tabs centered>
        <v-tab to="/orders/requests">Заявки</v-tab>
        <v-tab to="/orders/buys">Покупки</v-tab>
        <v-tab to="/orders/customers">База покупателей</v-tab>
        <v-tab to="/orders/defects">Позиции с дефектом</v-tab>
      </v-tabs>
    </v-card>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'Orders'
}
</script>

<style scoped>
  .title-part {
    text-align: center;
    font-size: 2em;
    padding: 10px
  }
</style>
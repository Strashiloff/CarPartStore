<template>
  <div id="container">
    <div id="grid-1">
      <v-btn color="success" to="/orders" class="button">Заказы</v-btn>
    </div>
    <div id="grid-2">
      <v-btn color="error" to="/stokes" class="button">Склад</v-btn>
    </div>
    <div id="grid-3">
      <v-btn color="warning" title="Раздел в разработке"  class="button">Бухгалтерия</v-btn>
    </div>
    <div id="grid-4">
      <v-btn color="info" to="/deliveries" class="button">Поставки</v-btn>
    </div>
  </div>
</template>

<script>
	export default {
		name: "Home"
	}
</script>

<style scoped>
  #container {
    width: 100%;
    height: 100%;
    display: grid;
    grid-template-rows: 50% 50%;
    grid-template-columns: 50vw 50vw
  }

  #grid-1 {
    padding: 10% 4% 4% 20%;
  }

  #grid-2 {
    padding: 10% 20% 4% 4%;
  }

  #grid-3 {
    padding: 4% 4% 10% 20%;
  }

  #grid-4 {
    padding: 4% 20% 10% 4%;
  }

  .button {
    width: 100%;
    height: 100%;
    color: white;
    font: bold 21px serif;
    border-radius: 20px;
  }

  @media (min-width: 434px) {
    .button {
      font-size: 4vw;
    }
  }
</style>
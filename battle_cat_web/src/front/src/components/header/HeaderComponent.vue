<template>
  <header>
    <div class="header-container">
      <router-link to="/" class="header-logo">
        <img src="./../../assets/temp/header_logo.png" alt="logo" />
      </router-link>
      <nav class="menu-bar">
        <main-menu />
      </nav>
    </div>
  </header>
</template>

<script>
import mainMenu from './HeaderMainMenuComponent.vue'

export default {
  components: {
    mainMenu,
  }
}
</script>

<style scoped>
header {
  width: 100%;
  height: 10vh;
  min-height: 50px;
  box-shadow: 0px 5px 3px #0003;
  display: flex;
}

.header-container {
  width: 60%;
  height: 100%;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 15% 85%;
  grid-template-rows: 100%;
}

.header-logo, .menu-bar {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
}

.header-logo > img {
  width: 100%;
}
</style>
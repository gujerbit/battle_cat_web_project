<template>
  <div id="container">
    <header-component />
    <transition name="fade" mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>

<script>
import headerComponent from './components/HeaderComponent.vue';

export default {
    name: 'App',
    components: {
      headerComponent,
    }
}
</script>

<style>
/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
} */

.fade-enter-active {
  transition: all 0.75s ease;
}

.fade-enter-from {
  opacity: 0;
}
</style>

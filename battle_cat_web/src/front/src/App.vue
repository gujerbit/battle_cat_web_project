<template>
  <div id="container">
    <!-- <header-component /> -->
    <div id="wrapper">
      <router-view v-slot="{ Component }">
        <transition name="slide">
          <component :is="Component" />
        </transition>
      </router-view>
    </div>
    <footer-component />
  </div>
</template>

<script>
import { ref, onBeforeMount } from 'vue';
// import $ from 'jquery';
// import headerComponent from './components/main/HeaderComponent.vue';
import footerComponent from './components/main/FooterComponent.vue';

export default {
    name: 'App',
    setup() {
      const user = ref({
        name: window.sessionStorage.getItem('name'),
      });

      onBeforeMount(() => {
        // $(document).ready(() => {
        //   $(document).bind('keydown', e => {
        //     if(e.ctrlKey || e.keyCode == 123) {
        //       e.preventDefault();
        //       e.returnValue = false;
        //     }
        //   });
        // });

        if(window.sessionStorage.getItem('init') == null) {
          window.sessionStorage.setItem('init', 'success');
          window.localStorage.clear();
          location.reload();
        }
      });

      return { user };
    },
    components: {
      // headerComponent,
      footerComponent
    },
}
</script>

<style>
@import "./../public/css/app.css";

#container {
  width: 100%;
  height: 100%;
}

#wrapper {
  width: 100%;
  height: 100%;
}

.slide-enter-active, .slide-leave-active {
  transition: all 1s;
}

.slide-enter-to {
  position: absolute;
  right: 0;
}

.slide-enter-from {
  position: absolute;
  right: -100%;
}

.slide-leave-to {
  position: absolute;
  left: -100%;
  opacity: 0;
}

.slide-leave-from {
  position: absolute;
  left: 0;
}
</style>
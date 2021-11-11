<template>
  <div id="container">
    <!-- <header-component /> -->
    <router-view v-slot="{ Component }">
      <transition name="slide">
        <component :is="Component" />
      </transition>
    </router-view>
    <!-- <div id="user-temp" v-if="user.name !== null">
      <p>{{user.name.length > 16 ? user.name.substring(0, 13) + '...' : user.name}}님 접속중</p>
      <button @click="logout()">로그아웃</button>
    </div> -->
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

      const logout = () => {
        window.sessionStorage.removeItem('jwt-auth-token');
        window.sessionStorage.removeItem('name');

        alert('로그아웃 하셨습니다.');
        location.href = '/login';
      };

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
          location.href = '/';
        }
      });

      return { user, logout };
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

#user-temp {
  position: absolute;
  top: 9%;
  left: 2.5%;
  border: 1px solid #000000;
  width: 13%;
  height: 10%;
  font-size: 2rem;
  display: grid;
  justify-items: center;
  background-color: #ffffff;
}

#user-temp button {
  width: 50%;
  height: 80%;
  border: 1px solid #000000;
  border-radius: 10px;
  background-color: #ffffff;
  transition: all 1s;
  font-size: 1.5rem;
}

#user-temp button:hover {
  transform: scale(105%);
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
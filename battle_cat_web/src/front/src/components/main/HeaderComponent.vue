<template>
  <header>
    <div id="header-container">
      <router-link to="/" class="header-logo">
        <img src="./../../assets/temp/header_logo.png" alt="logo" />
      </router-link>
      <nav class="menu">
        <router-link :to="`/${value}`" :class="proxy.store.getters.getPath === `/${value}` ? 'select' : ''" v-for="(value, index) in menus" :key="value">{{titles[index]}}</router-link>
      </nav>
    </div>
  </header>
</template>

<script>
import { getCurrentInstance, watchEffect } from 'vue'; //app.config.globalProperties에 등록된 글로벌 변수들 갖고오기

export default {
  setup() { //vue 3 composition api
    const { proxy } = getCurrentInstance(); //this.axios => proxy.axios처럼 대체
    let menus = ['unitInfo', 'enemyInfo', 'stageInfo', 'etcInfo', 'community'];
    let titles = ['아군 캐릭터 정보', '적군 캐릭터 정보', '스테이지 정보', '기타 정보', '커뮤니티'];

    watchEffect(() => {
      proxy.store.commit('setPath', proxy.$route.path);
    });

    return { proxy, menus, titles }; //return 해야 다른곳에서 쓸 수 있음
  },
}
</script>

<style scoped>
header {
  width: 100%;
  height: 6%;
  position: fixed;
  top: -5%;
  left: 0;
  display: flex;
  justify-content: center;
  background-color: #ffffff;
  box-shadow: 0px 3px 4px 0px #3d3d3d;
  opacity: 0;
  z-index: 1;
  transition: all 1s;
}

header:hover {
  top: 0;
  opacity: 1;
}

header * {
  text-decoration: none;
}
  
#header-container {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
  
.header-logo {
  width: 10%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
  
.header-logo img {
  width: 50%;
  height: 70%;
  transition: all 1s;
}

.header-logo img:hover {
  opacity: 0.5;
}
  
.menu {
  width: 90%;
  height: 100%;
  font-size: 2.5rem;
  display: flex;
}
  
.menu a {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
  
.menu a:hover {
  border-bottom: 1.5px solid #ffc038;
  padding-top: 1.5px;
}
  
.select {
  color: #ffc038;
}
</style>
<template>
  <header>
    <div id="header-container">
      <router-link to="/" class="header-logo">
        <img src="./../../assets/temp/header_logo.png" alt="logo" @click="selectMenu($event)" />
      </router-link>
      <nav class="menu">
        <router-link v-for="(value, index) in menus" :key="value" :name="value" :class="`${proxy.store.getters.getPath === value ? 'select' : ''}`" :to="`/${value}`" @click="selectMenu($event)">{{titles[index]}}</router-link>
      </nav>
    </div>
  </header>
</template>

<script>
import { getCurrentInstance } from 'vue'; //app.config.globalProperties에 등록된 글로벌 변수들 갖고오기

export default {
  setup() { //vue 3 composition api
    const { proxy } = getCurrentInstance(); //this.axios => proxy.axios처럼 대체
    let menus = ['unitInfo', 'enemyInfo', 'stageInfo', 'etcInfo', 'community'];
    let titles = ['아군 캐릭터 정보', '적군 캐릭터 정보', '스테이지 정보', '기타 정보', '커뮤니티'];

    const selectMenu = (event) => { //메뉴 선택
      if(event.currentTarget.getAttribute('name') === 'enemyInfo' || event.currentTarget.getAttribute('name') === 'stageInfo' || event.currentTarget.getAttribute('name') === 'etcInfo') {
        alert('준비중입니다');
        location.href = '/';
        proxy.store.commit('setPath', '');
        
        return;
      }

      let name = event.currentTarget.getAttribute('name'); //dom에 설정된 name 갖고오기
      proxy.store.commit("setPath", name); //store에 저장
    };

    return { selectMenu, proxy, menus, titles }; //return 해야 다른곳에서 쓸 수 있음
  },
}
</script>

<style scoped>
header {
  width: 100%;
  height: 7vh;
  display: flex;
  justify-content: center;
  box-shadow: 0px 3px 4px 0px #3d3d3d;
  margin-bottom: 1%;
}
  
#header-container {
  width: 80%;
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
  width: 70%;
  height: 80%;
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
  border-bottom: 2px solid #ffc038;
  padding-top: 2px;
}
  
.select {
  color: #ffc038;
}
</style>
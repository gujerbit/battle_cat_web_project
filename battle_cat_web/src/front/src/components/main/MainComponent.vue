<template>
  <main>
    <router-link :to="elements.path[index]" v-for="(value, index) in elements.content" :key="value">
      <p>{{elements.title[index]}}</p>
      <img :src="require(`../../assets/res/main/${value}.png`)" alt="">
    </router-link>
  </main>
</template>

<script>
import { onBeforeMount, getCurrentInstance } from 'vue';
import { setUnitInfo } from '../../js/unit/unitInfo.js';
import { setEnemyInfo } from '../../js/enemy/enemyInfo.js';
import { setStageInfo } from '../../js/stage/stageInfo.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const elements = {
      content: ['unit', 'enemy', 'stage', 'etc', 'community'],
      title: ['아군 캐릭터 정보', '적군 캐릭터 정보', '스테이지 정보', '기타 정보/일정', '커뮤니티'],
      path: ['/unitInfo', '/enemyInfo', '/stageInfo', '/etcInfo', '/community'],
    };

    onBeforeMount(() => {
      setUnitInfo(proxy.axios, proxy.store); //store에 데이터 담기
      setEnemyInfo(proxy.axios, proxy.store);
      setStageInfo(proxy.axios, proxy.store);
    });

    return { elements };
  }
};
</script>

<style src="../../css/main/mainComponent.css" scoped />
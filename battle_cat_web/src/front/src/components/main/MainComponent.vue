<template>
  <main>
    <router-link :to="elements.path[index]" v-for="(value, index) in elements.content" :key="value">
      <p>{{elements.title[index]}}</p>
      <img :src="require(`../../assets/res/main/${value}.png`)" alt="">
    </router-link>
  </main>
</template>

<script>
export default {
  setup() {
    const elements = {
      content: ['unit', 'enemy', 'stage', 'etc', 'community'],
      title: ['아군 캐릭터 정보', '적군 캐릭터 정보', '스테이지 정보', '기타 정보/일정', '커뮤니티'],
      path: ['/unitInfo', '/enemyInfo', '/stageInfo', '/etcInfo', '/community'],
    };

    return { elements };
  }
};
</script>

<style scoped src="../../css/main/mainComponent.css" />
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
import { checkReject } from '../../js/community/user/user.js';
import { getAccountInfo } from '../../js/community/admin/admin.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();
    
    const elements = {
      content: ['board', 'admin', 'user'],
      title: ['게시판', '관리자 페이지', '유저 정보'],
      path: ['/board', '/admin'],
    };

    onBeforeMount(() => {
      checkReject(proxy.axios);
      elements.path.push(`/userInfo/${getAccountInfo().name}`);
    });

    return { elements };
  }
}
</script>

<style scoped src="../../css/community/communityComponent.css" />
<template>
  <main>
    <router-link :to="elements.path[index]" v-for="(value, index) in elements.content" :key="value">
      <p>{{elements.title[index]}}</p>
      <img :src="require(`../../assets/res/main/${value}.png`)" alt="">
    </router-link>
  </main>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';
import { checkReject } from '../../js/community/user/user.js';
import { getAccountInfo } from '../../js/community/admin/admin.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();
    
    const elements = ref({
      content: ['board', 'admin', 'user'],
      title: [],
      path: ['/board', '/admin'],
    });

    onBeforeMount(() => {
      const temp = 1;

      if(temp === 1) {
        alert('개편중입니다');
        location.href = '/';
      } else {
        checkReject(proxy.axios);
        elements.value.path.push(`/userInfo/${getAccountInfo().name}`);
        elements.value.title = ['게시판', getAccountInfo().grade === 'user' ? '유저 목록/검색' : '관리자 페이지', '유저 정보'];
      }
    });

    return { elements };
  }
}
</script>

<style scoped src="../../css/community/communityComponent.css" />
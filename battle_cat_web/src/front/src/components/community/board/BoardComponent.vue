<template>
  <main>
      게시판
  </main>
</template>

<script>
import { onBeforeMount, getCurrentInstance } from 'vue';
import { checkReject } from '../../../js/community/user/user.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    onBeforeMount(() => {
      if(window.sessionStorage.getItem('jwt-auth-token') === null) {
        alert('로그인 후 이용가능한 시스템입니다');
        location.href = '/login';
      } else {
        checkReject(proxy.axios);
      }
    });
  }
}
</script>

<style scoped>

</style>
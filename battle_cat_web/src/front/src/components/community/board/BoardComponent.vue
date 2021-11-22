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
      checkReject(proxy.axios);
    });
  }
}
</script>

<style scoped>

</style>
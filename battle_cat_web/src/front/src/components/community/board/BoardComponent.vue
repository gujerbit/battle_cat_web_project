<template>
  <main>
      게시판
  </main>
</template>

<script>
import { onBeforeMount } from 'vue';

export default {
  setup() {
    onBeforeMount(() => {
      alert('준비중입니다');
      location.href = '/community';
    });
  }
}
</script>

<style scoped>

</style>
<template>
  <main>
    
  </main>
</template>

<script>
import { onBeforeMount } from 'vue';

export default {
  setup() {
    onBeforeMount(() => {
      alert('개발중입니다!');
      history.back();
    });

    return {};
  }
}
</script>

<style>

</style>
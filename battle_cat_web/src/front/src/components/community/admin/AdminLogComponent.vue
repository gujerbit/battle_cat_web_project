<template>
  <div id="admin-log">
    <div class="log" v-for="value in log.data" :key="value">
      {{value.content}}
      {{value.log_date}}
    </div>
  </div>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const log = ref({
      data: [],
    });

    onBeforeMount(async () => {
      let { data } = await proxy.axios.get('/get_admin_log');

      log.value.data = data.reverse();
    });

    return { log };
  }
}
</script>

<style scoped>

</style>
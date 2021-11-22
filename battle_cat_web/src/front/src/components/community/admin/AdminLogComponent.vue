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
#admin-log {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: repeat(20, 1fr);
}

.log {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2.5rem;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
}
</style>
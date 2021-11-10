<template>
  <main>
      <div class="patch-info" v-for="value in info.data" :key="value">
          <div class="title">
              <p>{{value.version}} 업데이트</p>
              <p>{{value.patch_date}}</p>
          </div>
          <div class="content">
              <p v-for="item in value.content.split('/')" :key="item">{{item}}</p>
          </div>
      </div>
  </main>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';
import { useRoute } from 'vue-router';
import { searchPatchNote } from '../../../js/etc/schedule/schedule.js';

export default {
    setup() {
        const route = useRoute();
        const { proxy } = getCurrentInstance();

        const info = ref({
            data: ''
        });

        onBeforeMount(() => {
            info.value.data = searchPatchNote(proxy.store, route.params.version);
        });

        return { info };
    }
}
</script>

<style scoped>
main {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
}

.patch-info {
    width: 50%;
}

.patch-info .title {
    display: flex;
    justify-content: space-around;
    font-size: 3rem;
    border-bottom: 1px solid #000000;
}

.patch-info .content {
    font-size: 2rem;
}

.content p {
    margin: 1%;
}
</style>
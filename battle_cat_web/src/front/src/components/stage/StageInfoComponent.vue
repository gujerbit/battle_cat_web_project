<template>
  <main>
    <div class="wrap">
      <stage-info-element-component />
      <section>
        <div class="content">

        </div>
      </section>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { onBeforeMount } from 'vue';

import stageInfoElementComponent from './stageInfoElementComponent.vue';

export default {
  setup() {
    onBeforeMount(() => {
    });
  },
  components: {
    stageInfoElementComponent
  },
};
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
}

main * {
  text-decoration: none;
}

.wrap {
  width: 100%;
  height: 100%;
  margin-top: 0.25%;
  display: grid;
  grid-template-rows: 10% 90%;
}

section {
  width: 90%;
  height: 100%;
  margin: 0 auto;
}

.content {
  width: 100%;
  height: 100%;
}

.main-page {
  width: 4.5%;
  height: 100%;
  border: 2px solid #ffc038;
  border-left: none;
  border-radius: 0 15px 15px 0;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  position: absolute;
  top: 0;
  left: 0;
  writing-mode: vertical-lr;
  display: flex;
  justify-content: center;
  align-items: center;
  opacity: 0;
  transition: all 1.5s;
  font-size: 3rem;
}
  
.main-page:hover {
  opacity: 1;
}
</style>
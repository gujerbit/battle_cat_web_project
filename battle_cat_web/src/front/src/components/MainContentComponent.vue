<template>
  <main>
    <div class="main-container">
      <div class="main-left">
        <updateVersionViewComponent :updateVersionData="updateVersionData" />
      </div>
      <div class="main-center">
        <div class="banner-imgs">
          <img src="/../../imgs/res/event/event_banner_easter.png" alt />
        </div>
      </div>
      <div class="main-right">
        <gachaScheduleViewComponent :gachaScheduleData="gachaScheduleData" />
      </div>
    </div>
  </main>
</template>

<script>
import updateVersionViewComponent from "./UpdateVersionViewComponent.vue";
import gachaScheduleViewComponent from "./GachaScheduleViewComponent.vue";

export default {
  data() {
    return {};
  },
  components: {
    updateVersionViewComponent,
    gachaScheduleViewComponent,
  },
  props: {
    updateVersionData: { type: Array, default: () => [] },
    gachaScheduleData: { type: Array, default: () => [] },
  },
};
</script>

<style scoped>
main {
    width: 100%;
}

.main-container {
    width: 80%;
    margin: 0 auto;
    margin-top: 0.5vh;
    display: grid;
    grid-template-columns: 1fr 2fr 1fr;
}

.main-left {
    display: flex;
    justify-content: center;
}

.main-center {
    display: flex;
    justify-content: center;
}

.main-right {
    display: flex;
    justify-content: center;
}
</style>
<template>
  <main>
    <div class="main-container">
      <div class="main-left">
        <!--
        <updateVersionViewComponent :updateVersionData="updateVersionData" />
        <gachaScheduleViewComponent :gachaScheduleData="gachaScheduleData" />
        <dailyScheduleViewComponent :dailyScheduleData="dailyScheduleData" />
        -->
        <event-schedule-view-component />
      </div>
      <div class="main-center">
        <div class="banner-imgs">
          <img src="/../../imgs/res/event/event_banner_easter.png" alt />
        </div>
      </div>
      <div class="main-right">
        <!--
        <eventScheduleViewComponent :eventScheduleData="eventScheduleData" />
        -->
        <event-schedule-view-component />
      </div>
    </div>
  </main>
</template>

<script>
// import updateVersionViewComponent from "./UpdateVersionViewComponent.vue";
// import gachaScheduleViewComponent from "./GachaScheduleViewComponent.vue";
import eventScheduleViewComponent from "./EventScheduleViewComponent.vue";
// import dailyScheduleViewComponent from "./DailyScheduleViewComponent.vue";

export default {
  data() {
    return {};
  },
  components: {
    // updateVersionViewComponent,
    // gachaScheduleViewComponent,
    eventScheduleViewComponent,
    // dailyScheduleViewComponent,
  },
  props: {
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
  margin-top: 2%;
  display: grid;
  grid-template-columns: 30% 35% 30%;
  grid-gap: 1%;
}

.main-left {
  width: 100%;
}

.main-center {
  width: 100%;
}

.main-right {
  width: 100%;
}

.banner-imgs {
  width: 100%;
}

.banner-imgs > img {
  width: 100%;
  max-width: 400px;
  min-width: 300px;
}
</style>
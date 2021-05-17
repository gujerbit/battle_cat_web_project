<template>
  <main>
    <div class="main-container">
      <div class="main-left">
        <updateVersionViewComponent />
        <gachaScheduleViewComponent />
      </div>
      <div class="main-center">
        <div class="gacha-banner-imgs">
          <img src="/../../imgs/res/gacha/gacha_banner_easter.png" />
        </div>
        <div class="event-banner-imgs">
          <img src="/../../imgs/res/event/event_banner_goldrush.jpg" >
        </div>
      </div>
      <div class="main-right">
        <event-schedule-view-component />
        <!-- <dailyScheduleViewComponent /> -->
      </div>
    </div>
  </main>
</template>

<script>
import updateVersionViewComponent from "./UpdateVersionViewComponent.vue";
import gachaScheduleViewComponent from "./GachaScheduleViewComponent.vue";
import eventScheduleViewComponent from "./EventScheduleViewComponent.vue";
// import dailyScheduleViewComponent from "./DailyScheduleViewComponent.vue";

export default {
  components: {
    updateVersionViewComponent,
    gachaScheduleViewComponent,
    eventScheduleViewComponent,
    // dailyScheduleViewComponent,
  }
};
</script>

<style scoped>
main {
  width: 100%;
}

.main-container {
  width: 80%;
  margin: 0 auto;
  margin-top: 0.5%;
  display: grid;
  grid-template-columns: 25% 45% 25%;
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

.gacha-banner-imgs, .event-banner-imgs {
  width: 100%;
}

.gacha-banner-imgs > img, .event-banner-imgs > img {
  width: 100%;
}
</style>
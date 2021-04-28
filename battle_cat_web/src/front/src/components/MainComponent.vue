<template>
  <div id="container">
    <headerComponent></headerComponent>
    <mainContentComponent
      :updateVersionData="updateVersionData"
      :gachaScheduleData="gachaScheduleData"
      :eventScheduleData="eventScheduleData"
      :dailyScheduleData="dailyScheduleData"
    ></mainContentComponent>
    <footerComponent></footerComponent>
  </div>
</template>

<script>
import headerComponent from "./HeaderComponent.vue";
import mainContentComponent from "./MainContentComponent.vue";
import footerComponent from "./FooterComponent.vue";
import axios from "axios";

const DOMAIN = "http://localhost:8090";

export default {
  data() {
    return {
      updateVersionData: [],
      gachaScheduleData: [],
      eventScheduleData: [],
      dailyScheduleData: [],
    };
  },
  components: {
    headerComponent,
    mainContentComponent,
    footerComponent,
  },
  async mounted() {
    // axios.get("/notice").then((res) => {
    //   console.log(res);
    // });
    //let { data } = await axios.get(`${DOMAIN}/main_update_version`);
    let updateVersion = await axios.get(`${DOMAIN}/update_version_list`);
    this.updateVersionData = updateVersion.data;

    let gachaSchedule = await axios.get(`${DOMAIN}/gacha_schedule_list`);
    this.gachaScheduleData = gachaSchedule.data;

    let eventSchedule = await axios.get(`${DOMAIN}/event_schedule_list`);
    this.eventScheduleData = eventSchedule.data;

    let dailySchedule = await axios.get(`${DOMAIN}/daily_schedule_list`);
    this.dailyScheduleData = dailySchedule.data;
    console.log(dailySchedule.data);
  },
};
</script>

<style scoped>
#container {
    width: 100%;
}
</style>
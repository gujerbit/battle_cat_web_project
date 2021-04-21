<template>
  <div id="container">
    <headerComponent></headerComponent>
    <mainContentComponent
      :updateVersionData="updateVersionData"
      :gachaScheduleData="gachaScheduleData"
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
      gachaScheduleData: []
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
    let updateVersion = await axios.get(`${DOMAIN}/main_update_version`);
    this.updateVersionData = updateVersion.data;

    let gachaSchedule = await axios.get(`${DOMAIN}/gacha_schedule`);
    this.gachaScheduleData = gachaSchedule.data;
  },
};
</script>

<style scoped>
#container {
    width: 100%;
}
</style>
<template>
  <div id="container">
    <headerComponent></headerComponent>
    <router-view></router-view>
    <footerComponent></footerComponent>
  </div>
</template>

<script>
import headerComponent from "./HeaderComponent.vue";
import footerComponent from "./FooterComponent.vue";

export default {
  data() {
    return {
    };
  },
  components: {
    headerComponent,
    footerComponent,
  },
  async mounted() {
    // axios.get("/notice").then((res) => {
    //   console.log(res);
    // });
    //let { data } = await axios.get(`${DOMAIN}/main_update_version`);
    // let updateVersion = await axios.get(`${DOMAIN}/update_version_list`);
    // this.updateVersionData = updateVersion.data;

    // let dailySchedule = await axios.get(`${DOMAIN}/daily_schedule_list`);
    // this.dailyScheduleData = dailySchedule.data;
  }
};
</script>

<style scoped>
#container {
  width: 100%;
}
</style>
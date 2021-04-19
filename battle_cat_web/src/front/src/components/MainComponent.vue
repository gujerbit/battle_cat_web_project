<template>
  <div id="container">
    <headerComponent></headerComponent>
    <mainContentComponent
      :updateVersionData="updateVersionData"
    ></mainContentComponent>
    <footerComponent></footerComponent>
  </div>
</template>

<script>
import headerComponent from "./HeaderComponent.vue";
import mainContentComponent from "./MainContentComponent.vue";
import footerComponent from "./FooterComponent.vue";
import axios from "axios";

const domain = "http://localhost:8090";

export default {
  data() {
    return {
      updateVersionData: [],
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
    let { data } = await axios.get(`${domain}/main_update_version`);
    this.updateVersionData = data;
    console.log(data);
  },
};
</script>

<style scoped>
</style>
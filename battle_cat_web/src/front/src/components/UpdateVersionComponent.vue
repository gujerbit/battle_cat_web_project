<template>
  <div class="update-version-container">
    <headerComponent></headerComponent>
    <main>
      <div class="temp" v-for="value in updateVersionData" :key="value">
        {{ value }}
      </div>
    </main>
    <footerComponent></footerComponent>
  </div>
</template>

<script>
import headerComponent from "./header/HeaderComponent.vue";
import footerComponent from "./FooterComponent.vue";
import axios from "axios";

const DOMAIN = "http://localhost:8090";

export default {
  data() {
    return {
      updateVersionData: [],
    };
  },
  components: {
    headerComponent,
    footerComponent,
  },
  async mounted() {
    let { data } = await axios.get(`${DOMAIN}/update_version_data/${this.version}`);
    this.updateVersionData = data;
    console.log(data);
  },
  props: {
    version: { type: String, default: "" },
  },
};
</script>

<style scoped>
@import url("./../../public/css/app.css");
</style>
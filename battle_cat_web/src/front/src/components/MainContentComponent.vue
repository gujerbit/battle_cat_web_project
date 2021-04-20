<template>
  <main>
    <div class="main-container">
      <div class="main-left">
        <div class="main-update-version">
          <!-- {{updateVersionData[0]['update_content']}} -->
          <div class="update-version-element">
            <div class="update-element-list">
              <p class="update-title-element">제목</p>
              <p class="update-date-element">날짜</p>
            </div>
          </div>
          <div class="update-version-content">
            <div class="update-content-list">
              <router-link :to="'/update_version/'+updateVersionValue['version']" @click="pageMove(updateVersionValue)" class="update-content" v-for="updateVersionValue in updateVersionData" :key="updateVersionValue">
                <p class="update-title">{{updateVersionValue['title']}}</p>
                <p class="update-date">{{updateVersionValue['update_date']}}</p>
              </router-link>
            </div>
          </div>
        </div>
      </div>
      <div class="main-center">
        <div class="banner-imgs">
          <img src="/../../imgs/res/event/event_banner_easter.png" alt />
        </div>
      </div>
      <div class="main-right">
        <div class="main-gacha-schedule">
          <div class="gacha-schedule-element">
            <div class="gacha-element-list">
              <p class="gacha-title-element">제목</p>
              <p class="gacha-start-date-element">시작 날짜</p>
              <p class="gacha-end-date-element">종료 날짜</p>
            </div>
          </div>
          <div class="gacha-schedule-content">
            <div class="gacha-schedule-list">
              <router-link to="" class="gacha-content" v-for="gachaScheduleValue in gachaScheduleData" :key="gachaScheduleValue">
                <p class="gacha-title" v-if="gachaScheduleValue['is_unconditional'] === false">{{gachaScheduleValue['content']}}</p>
                <p class="gacha-title is_unconditional" v-else>{{gachaScheduleValue['content']}}</p>
                <p class="gacha-start-date">{{gachaScheduleValue['gacha_start_date']}}</p>
                <p class="gacha-end-date">{{gachaScheduleValue['gacha_end_date']}}</p>
              </router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import axios from 'axios';

const DOMAIN = "http://localhost:8090";

export default {
  data() {
    return {};
  },
  components: {},
  mounted() {},
  methods: {
    async pageMove(value) {
      await axios.post(`${DOMAIN}/update_version`, value['version']);
    }
  },
  props: {
    updateVersionData: { type: Array, default: () => [] },
    gachaScheduleData: { type: Array, default: () => [] }
  }
};
</script>

<style scoped>
</style>
<template>
  <main>
    <div class="main-container">
      <div class="main-left">
        <div class="main-update-version">
          <!-- {{updateVersionData[0]['update_content']}} -->
          <div class="update-version-element">
              <p class="update-title-element">제목</p>
              <p class="update-date-element">날짜</p>
          </div>
          <div class="update-version-content">
              <div class="content-list">
                  <router-link to="" class="content" v-for="value in updateVersionData" :key="value">
                    <p class="title">{{value['title']}}</p>
                    <p class="update-date">{{value['update_date']}}</p>
                  </router-link>
              </div>
          </div>
        </div>
      </div>
      <div class="main-center">
        <div class="banner-imgs">
          <img src="/../../imgs/res/event/event_banner_easter.png" alt="" />
        </div>
      </div>
      <div class="main-right">

      </div>
    </div>
  </main>
</template>

<script>
export default {
  data() {
    return {

    };
  },
  components: {},
  mounted() {
      
  },
  props: {
    updateVersionData: { type: Array, default: () => [] },
  },
};
</script>

<style scoped>
</style>
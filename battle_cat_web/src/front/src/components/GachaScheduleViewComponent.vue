<template>
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
        <router-link
          to=""
          class="gacha-content"
          v-for="gachaScheduleValue in gachaScheduleData"
          :key="gachaScheduleValue"
        >
          <p
            class="gacha-title"
            v-if="gachaScheduleValue['is_unconditional'] === false"
          >
            {{ gachaScheduleValue["content"] }}
          </p>
          <p class="gacha-title is_unconditional" v-else>
            {{ gachaScheduleValue["content"] }}
          </p>
          <p class="gacha-start-date">
            {{ gachaScheduleValue["gacha_start_date"] }}
          </p>
          <p class="gacha-end-date">
            {{ gachaScheduleValue["gacha_end_date"] }}
          </p>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    gachaScheduleData: { type: Array, defalut: () => [] },
  },
};
</script>

<style scoped>
.main-gacha-schedule {
    width: 100%;
    /* display: grid; */
    /* grid-template-rows: 15% 85%; */
    border: 1px solid rgba(0, 0, 0, 1);
    /* overflow: hidden; */
}

.gacha-schedule-element {
    height: 15%;
    display: flex;
}

.gacha-element-list {
    width: 100%;
    /* height: 100%;   */
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    align-items: center;
    text-align: center;
    font-size: 1.4em;
    background-color: #f0a535;
}

.gacha-schedule-content {
    display: flex;
    justify-content: center;
}

.gacha-schedule-list {
    width: 100%;
    display: grid;
    grid-auto-rows: 15%;
}

.gacha-schedule-list > .gacha-content {
    width: 100%;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    font-size: 1.2em;
    box-shadow: 0px 1.5px 3px #f0a535;
    align-items: center;
}

.gacha-schedule-list > .gacha-content > .gacha-title, .gacha-start-date, .gacha-end-date {
    display: flex;
    justify-content: center;
    text-align: center;
}

.is_unconditional {
    color: #f0a535;
}
</style>
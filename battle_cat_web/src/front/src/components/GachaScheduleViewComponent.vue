<template>
  <div class="main-gacha-schedule">
    <div class="gacha-schedule-element">
      <p class="gacha-element">제목</p>
      <p class="gacha-element">시작 날짜</p>
      <p class="gacha-element">종료 날짜</p>
    </div>
    <div class="gacha-schedule-content">
      <router-link
        to=""
        class="gacha-content"
        v-for="gachaScheduleValue in gachaScheduleData"
        :key="gachaScheduleValue"
      >
        <p
          class="gacha-title"
          v-if="gachaScheduleValue['is_unconditional'] === false"
        >{{ gachaScheduleValue["content"] }}</p>
        <p class="gacha-title is_unconditional" v-else>{{ gachaScheduleValue["content"] }}</p>
        <p class="gacha-start-date">{{ gachaScheduleValue["gacha_start_date"] }}</p>
        <p class="gacha-end-date">{{ gachaScheduleValue["gacha_end_date"] }}</p>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    gachaScheduleData: { type: Array, defalut: () => [] }
  }
};
</script>

<style scoped>
.main-gacha-schedule {
  width: 100%;
  height: 50vh;
  min-width: 400px;
  min-height: 250px;
  display: grid;
  grid-template-rows: 10% 90%;
  text-align: center;
}

.gacha-schedule-element {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  font-size: 2rem;
  background-color: #fab92c;
}

.gacha-element {
  display: flex;
  justify-content: center;
  align-items: center;
}

.gacha-schedule-content {
  width: 100%;
  height: 100%;
  font-size: 1.5rem;
}

.gacha-content {
  width: 100%;
  height: 11%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  border-bottom: 1px solid #fab92c;
}

.gacha-content > p {
  display: flex;
  justify-content: center;
  align-items: center;
}

.is_unconditional {
  color: #fab92c;
}
</style>
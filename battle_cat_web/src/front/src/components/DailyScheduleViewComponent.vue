<template>
  <div class="main-daily-schedule">
    <div class="daily-week">
        <p class="week">일</p>
        <p class="week">월</p>
        <p class="week">화</p>
        <p class="week">수</p>
        <p class="week">목</p>
        <p class="week">금</p>
        <p class="week">토</p>
    </div>
    <div class="daily-schedule-element">
      <p class="daily-element">제목</p>
      <p class="daily-element">시작 시간</p>
      <p class="daily-element">종료 시간</p>
    </div>
    <div class="daily-schedule-content">
      <router-link
        to=""
        class="daily-content"
        v-for="dailyScheduleValue in dailyScheduleData"
        :key="dailyScheduleValue"
      >
        <p class="daily-title">{{dailyScheduleValue['content']}}</p>
        <p class="daily-start">{{dailyScheduleValue['start']}}</p>
        <p class="daily-end">{{dailyScheduleValue['end']}}</p>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
    props: {
        dailyScheduleData: { type: Array, default: () => [] }
    }
}
</script>

<style>
.main-daily-schedule {
    height: 30vh;
}
</style>
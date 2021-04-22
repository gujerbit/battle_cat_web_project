<template>
  <div class="main-event-schedule">
    <div class="event-schedule-element">
      <p class="event-element">제목</p>
      <p class="event-element">시작 날짜</p>
      <p class="event-element">종료 날짜</p>
    </div>
    <div class="event-schedule-content">
      <router-link
        to=""
        class="event-content"
        v-for="eventScheduleValue in eventScheduleData"
        :key="eventScheduleValue"
      >
        <p class="event-title">{{eventScheduleValue['content']}}</p>
        <p class="event-start-date">{{eventScheduleValue['event_start_date']}}</p>
        <p class="event-end-date">{{eventScheduleValue['event_end_date']}}</p>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    eventScheduleData: { type: Array, defalut: () => [] },
  },
};
</script>

<style scoped>
.main-event-schedule {
  width: 100%;
  height: 30vh;
  min-width: 400px;
  min-height: 250px;
  display: grid;
  grid-template-rows: 15% 85%;
  text-align: center;
}

.event-schedule-element {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  font-size: 2rem;
  background-color: #fab92c;
}

.event-element {
  display: flex;
  justify-content: center;
  align-items: center;
}

.event-schedule-content {
  width: 100%;
  height: 100%;
  font-size: 1.5rem;
}

.event-content {
  width: 100%;
  height: 20%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  border-bottom: 1px solid #fab92c;
}

.event-content > p {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
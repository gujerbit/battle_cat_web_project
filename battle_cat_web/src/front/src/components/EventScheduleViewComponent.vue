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
    <div class="size">
      <router-link class="prev" to="" @click="prevPage">&laquo;</router-link>
      <router-link class="page" to="" @click="pageChange(value)" v-for="value in currentPages[currentViewIdx]" :key="value">{{value}}</router-link>
      <router-link class="next" to="" @click="nextPage">&raquo;</router-link>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

const DOMAIN = "http://localhost:8090";

export default {
  data() {
    return {
      eventScheduleData: {},
      eventScheduleSizeData: 0,
      currentPages: [],
      currentViewIdx: 0,
      limit: 0
    }
  },
  props: {
    // eventScheduleData: { type: Array, defalut: () => [] },
  },
  async mounted() {
    let eventSchedule = await axios.get(`${DOMAIN}/event_schedule_list/${this.limit}`);
    this.eventScheduleData = eventSchedule.data;

    let eventScheduleSize = await axios.get(`${DOMAIN}/event_schedule_list_size`);
    this.eventScheduleSizeData = eventScheduleSize.data;

    this.currentPage();
  },
  methods: {
    currentPage() {
      let sizeArr = [];
      for(let i = 1; i <= Math.ceil(this.eventScheduleSizeData/5); i++) {
        sizeArr.push(i);

        if(i % 5 === 0 || i === Math.ceil(this.eventScheduleSizeData/5)) {
          this.currentPages.push(sizeArr);
          sizeArr = [];
        }
      }
      console.log(Math.ceil(this.eventScheduleSizeData/5));
      console.log(this.currentPages);
    },
    async nextPage() {
      if(this.currentPages.length-1 > this.currentViewIdx) {
        this.currentViewIdx++;
        this.limit = (this.currentPages[this.currentViewIdx][0] - 1) * 5;
        let eventSchedule = await axios.get(`${DOMAIN}/event_schedule_list/${this.limit}`);
        this.eventScheduleData = eventSchedule.data;
      }
    },
    async prevPage() {
      if(0 < this.currentViewIdx) {
        this.currentViewIdx--;
        this.limit = (this.currentPages[this.currentViewIdx][0] - 1) * 5;
        let eventSchedule = await axios.get(`${DOMAIN}/event_schedule_list/${this.limit}`);
        this.eventScheduleData = eventSchedule.data;
      }
    },
    async pageChange(page) {
      this.limit = (page-1) * 5;
      let eventSchedule = await axios.get(`${DOMAIN}/event_schedule_list/${this.limit}`);
      this.eventScheduleData = eventSchedule.data;
    }
  }
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
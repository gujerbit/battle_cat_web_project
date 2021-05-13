<template>
  <div class="event-schedule">
    <div class="elements">
      <p class="element">이벤트</p>
      <p class="element">시작 날짜</p>
      <p class="element">종료 날짜</p>
    </div>
    <div class="contents">
      <router-link
        to=""
        class="content"
        v-for="value in eventScheduleData"
        :key="value"
      >
        <p class="title" v-if="value['content'].length <= 10">{{value['content']}}</p>
        <p class="title" v-else>{{value['content'].substring(0, 7) + '...'}}</p>
        <p class="start">{{value['event_start_date']}}</p>
        <p class="end">{{value['event_end_date']}}</p>
      </router-link>
    </div>
    <div class="pages">
      <router-link class="prev" to="" @click="prevPage">&laquo;</router-link>
      <router-link class="current" to="" @click="pageChange(value)" v-for="value in currentPages[currentViewIdx]" :key="value">{{value}}</router-link>
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
.event-schedule {
  width: 100%;
  height: 30vh;
  display: grid;
  grid-template-rows: 15% 75% 10%;
  text-align: center;
  /* border: 1px solid #fab92c; */
}

.elements {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 0.3%;
  font-size: 1.9rem;
}

.element {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #fab92c;
  border-radius: 5px 5px 0 0;
}

.contents {
  width: 100%;
  height: 100%;
  font-size: 1.6rem;
}

.content {
  width: 100%;
  height: 20%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 0.3%;
  /* border-bottom: 1px solid #fab92c; */
  overflow: hidden;
}

.content:nth-child(2n - 1) > p { background-color: #fffdf8; }
.content:nth-child(2n) > p { background-color: #fff8e4; }

.content > p {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.pages {
  display: flex;
  justify-content: space-around;
  align-items: center;
  font-size: 1.25rem;
  background-color: #fff8e4;
}
</style>
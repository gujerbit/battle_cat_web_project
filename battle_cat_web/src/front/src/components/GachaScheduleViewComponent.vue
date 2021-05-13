<template>
  <div class="gacha-schedule">
    <div class="elements">
      <p class="element">이벤트</p>
      <p class="element">시작 날짜</p>
      <p class="element">종료 날짜</p>
    </div>
    <div class="contents">
      <router-link
        to=""
        class="content"
        v-for="value in gachaScheduleData"
        :key="value"
      >
        <p class="title is-unconditional" v-if="value['content'].length <= 10 && value['is_unconditional']">{{value['content']}}</p>
        <p class="title" v-else-if="value['content'].length <= 10 && !value['is_unconditional']">{{value['content']}}</p>
        <p class="title is-unconditional" v-else-if="value['content'].length > 10 && value['is_unconditional']">{{value['content'].substring(0, 8) + '..'}}</p>
        <p class="title" v-else-if="value['content'].length > 10 && !value['is_unconditional']">{{value['content'].substring(0, 8) + '..'}}</p>
        <p class="start">{{value['gacha_start_date']}}</p>
        <p class="end">{{value['gacha_end_date']}}</p>
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
      gachaScheduleData: {},
      gachaScheduleSizeData: 0,
      currentPages: [],
      currentViewIdx: 0,
      limit: 0
    }
  },
  async mounted() {
    let gachaSchedule = await axios.get(`${DOMAIN}/gacha_schedule_list/${this.limit}`);
    this.gachaScheduleData = gachaSchedule.data;

    console.log(gachaSchedule.data);

    let gachaScheduleSize = await axios.get(`${DOMAIN}/gacha_schedule_list_size`);
    this.gachaScheduleSizeData = gachaScheduleSize.data;

    this.currentPage();
  },
  methods: {
    currentPage() {
      let sizeArr = [];
      for(let i = 1; i <= Math.ceil(this.gachaScheduleSizeData/5); i++) {
        sizeArr.push(i);

        if(i % 5 === 0 || i === Math.ceil(this.gachaScheduleSizeData/5)) {
          this.currentPages.push(sizeArr);
          sizeArr = [];
        }
      }
    },
    async nextPage() {
      if(this.currentPages.length-1 > this.currentViewIdx) {
        this.currentViewIdx++;
        this.limit = (this.currentPages[this.currentViewIdx][0] - 1) * 5;
        let gachaSchedule = await axios.get(`${DOMAIN}/gacha_schedule_list/${this.limit}`);
        this.gachaScheduleData = gachaSchedule.data;
      }
    },
    async prevPage() {
      if(0 < this.currentViewIdx) {
        this.currentViewIdx--;
        this.limit = (this.currentPages[this.currentViewIdx][0] - 1) * 5;
        let gachaSchedule = await axios.get(`${DOMAIN}/gacha_schedule_list/${this.limit}`);
        this.gachaScheduleData = gachaSchedule.data;
      }
    },
    async pageChange(page) {
      this.limit = (page-1) * 5;
      let gachaSchedule = await axios.get(`${DOMAIN}/gacha_schedule_list/${this.limit}`);
      this.gachaScheduleData = gachaSchedule.data;
    }
  }
};
</script>

<style scoped>
.gacha-schedule {
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
  font-size: 1.8rem;
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
  font-size: 1.5rem;
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

.is-unconditional {
  color: #ff5353;
}
</style>
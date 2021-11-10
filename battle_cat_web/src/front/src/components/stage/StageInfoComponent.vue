<template>
  <main>
    <nav class="select-stage">
      <router-link to="" class="select">
        세계편
        <div class="step">
          <div class="step-area">
            <p class="world 1" @click="selectStage($event)">1장</p>
            <p class="world 2" @click="selectStage($event)">2장</p>
            <p class="world 3" @click="selectStage($event)">3장</p>
          </div>
        </div>
      </router-link>
      <router-link to="">
        미래편
        <div class="step">
          <div class="step-area">
            <p class="future 1" @click="selectStage($event)">1장</p>
            <p class="future 2" @click="selectStage($event)">2장</p>
            <p class="future 3" @click="selectStage($event)">3장</p>
          </div>
        </div>
      </router-link>
      <router-link to="">
        우주편
        <div class="step">
          <div class="step-area">
            <p class="space 1" @click="selectStage($event)">1장</p>
            <p class="space 2" @click="selectStage($event)">2장</p>
            <p class="space 3" @click="selectStage($event)">3장</p>
          </div>
        </div>
      </router-link>
      <router-link to="">
        레전드 스토리
        <div class="step">
          <div class="step-area">
            <p class="legend 1" @click="selectStage($event)">1성</p>
            <p class="legend 2" @click="selectStage($event)">2성</p>
            <p class="legend 3" @click="selectStage($event)">3성</p>
            <p class="legend 3" @click="selectStage($event)">4성</p>
          </div>
        </div>
      </router-link>
      <router-link to="">
        신레전드 스토리
        <div class="step">
          <div class="step-area">
            <p class="origin-legend 1" @click="selectStage($event)">1성</p>
            <p class="origin-legend 2" @click="selectStage($event)">2성</p>
          </div>
        </div>
      </router-link>
      <router-link to="" class="event non-step" @click="selectStage($event)">스페셜 스테이지</router-link>
      <router-link to="" class="collabo non-step">콜라보 스테이지</router-link>
      <input type="text" class="search" @input="searchStage($event)" placeholder="검색">
      <img src="" alt="">
    </nav>
    <nav class="event-stage" v-if="stage === 'event' || stage === 'guerrilla' || stage === 'week' || stage === 'drop' || stage === 'advent' || stage === 'crazy' || stage === 'cyclone' || stage === 'arousal' || stage === 'excavation' || stage === 'lesson'">
      <router-link to="" class="event non-step select" @click="selectEventStage($event)">
        이벤트 스테이지
      </router-link>
      <router-link to="" class="week">
        게릴라/요일 스테이지
        <div class="step">
          <div class="step-area">
            <p class="guerrilla" @click="selectEventStage($event)">게릴라</p>
            <p class="sunday" @click="selectEventStage($event)">일</p>
            <p class="monday" @click="selectEventStage($event)">월</p>
            <p class="tuesday" @click="selectEventStage($event)">화</p>
            <p class="wednesday" @click="selectEventStage($event)">수</p>
            <p class="thursday" @click="selectEventStage($event)">목</p>
            <p class="friday" @click="selectEventStage($event)">금</p>
            <p class="saturday" @click="selectEventStage($event)">토</p>
          </div>
        </div>
      </router-link>
      <router-link to="" class="drop" @click="selectEventStage($event)">
        드롭 스테이지
        <div class="step">
          <div class="step-area">
            <p class="drop" @click="selectEventStage($event)">드롭</p>
            <p class="advent" @click="selectEventStage($event)">강림</p>
            <p class="crazy" @click="selectEventStage($event)">광란</p>
            <p class="cyclone" @click="selectEventStage($event)">사이클론</p>
            <p class="arousal" @click="selectEventStage($event)">각성</p>
          </div>
        </div>
      </router-link>
      <router-link to="" class="excavation" @click="selectEventStage($event)">
        발굴 스테이지
        <div class="step">
          <div class="step-area">
            <p class="high" @click="selectEventStage($event)">최고급</p>
            <p class="common" @click="selectEventStage($event)">평범한</p>
            <p class="crude" @click="selectEventStage($event)">조잡한</p>
          </div>
        </div>
      </router-link>
      <router-link to="" class="lesson non-step" @click="selectEventStage($event)">
        강습
      </router-link>
    </nav>
    <content-component v-if="searchData.length === 0" :stageData="stageData" :step="step" />
    <search-component v-else :searchData="searchData" :step="step" />
  </main>
</template>

<script>
import { onBeforeMount } from 'vue';
import contentComponent from "./StageInfoContentComponent.vue";
import searchComponent from './StageInfoSearchComponent.vue';

export default {
  setup() {
    onBeforeMount(() => {
      alert('준비중입니다');
      location.href = '/';
    });
  },
  data() {
    return {
      stageData: [],
      searchData: [],
      stage: "world",
      step: 1,
    };
  },
  async mounted() {
    let { data } = await this.axios.get(`/stage_data_world`);
    this.stageData = data;
  },
  methods: {
    async selectStage(event) {
      let stageName = event.currentTarget.className;
      let menu = event.currentTarget.closest('nav');

      menu.childNodes[7].value = '';
      this.searchData = [];
      
      for(let i = 0; i < 6; i++) menu.childNodes[i].classList.remove('select');
      event.currentTarget.closest('a').classList.add('select');

      if (stageName.includes("world")) {
        let { data } = await this.axios.get(`/stage_data_world`);
        this.stageData = data;
        this.stage = "world";
      } else if (stageName.includes("future")) {
        let { data } = await this.axios.get(`/stage_data_future`);
        this.stageData = data;
        this.stage = "future";
      } else if (stageName.includes("space")) {
        let { data } = await this.axios.get(`/stage_data_space`);
        this.stageData = data;
        this.stage = "space";
      } else if (stageName.includes("legend") && !stageName.includes('origin-legend')) {
        let { data } = await this.axios.get(`/stage_data_legend`);
        this.stageData = data;
        this.stage = "legend";
      } else if (stageName.includes("origin-legend")) {
        let { data } = await this.axios.get(`/stage_data_origin_legend`);
        this.stageData = data;
        this.stage = "origin-legend";
      } else if (stageName.includes("event")) {
        let { data } = await this.axios.get(`/stage_data_event`);
        this.stageData = data;
        console.log(data);
        this.stage = "event";
      } else if (stageName.includes("collabo")) {
        let { data } = await this.axios.get(`/stage_data_collabo`);
        this.stageData = data;
        this.stage = 'collabo';
      }

      if(stageName.includes('1')) {
        this.step = 1;
      } else if(stageName.includes('2')) {
        this.step = 2;
      } else if(stageName.includes('3')) {
        this.step = 3;
      } else if(stageName.includes('4')) {
        this.step = 4;
      }
    },
    async selectEventStage(event) {
      let stageName = event.currentTarget.className;
      let menu = event.currentTarget.closest('nav').childNodes;
      event.currentTarget.closest('main').childNodes[0].childNodes[7].value = ''
      this.searchData = [];

      for(let i = 0; i < menu.length; i++) menu[i].classList.remove('select');
      event.currentTarget.closest('a').classList.add('select');

      if (stageName.includes("event")) {
        let { data } = await this.axios.get(`/stage_data_event`);
        this.stageData = data;
        this.stage = "event";
      } else if(stageName.includes("guerrilla")) {
        let { data } = await this.axios.get(`/stage_data_guerrilla`);
        this.stageData = data;
        this.stage = "guerrilla";
      } else if(stageName.includes("day")) {
        let week = '';

        if(stageName === 'sunday') {
          week = '일'
        } else if(stageName === 'monday') {
          week = '월';
        } else if(stageName === 'tuesday') {
          week = '화';
        } else if(stageName === 'wednesday') {
          week = '수';
        } else if(stageName === 'thursday') {
          week = '목';
        } else if(stageName === 'friday') {
          week = '금';
        } else if(stageName === 'saturday') {
          week = '토';
        }

        let { data } = await this.axios.get(`/stage_data_week/${week}`);
        this.stageData = data;
        this.stage = "week";
      } else if(stageName === 'drop') {
        let { data } = await this.axios.get(`/stage_data_drop`);
        this.stageData = data;
        this.stage = "drop";
      } else if(stageName === 'advent') {
        let { data } = await this.axios.get(`/stage_data_advent`);
        this.stageData = data;
        this.stage = "advent";
      } else if(stageName === 'crazy') {
        let { data } = await this.axios.get(`/stage_data_crazy`);
        this.stageData = data;
        this.stage = "crazy";
      } else if(stageName === 'cyclone') {
        let { data } = await this.axios.get(`/stage_data_cyclone`);
        this.stageData = data;
        this.stage = "cyclone";
      } else if(stageName === 'arousal') {
        let { data } = await this.axios.get(`/stage_data_arousal`);
        this.stageData = data;
        this.stage = "arousal";
      } else if(stageName === 'high') {
        let { data } = await this.axios.get(`/stage_data_excavation/${'최고급'}`);
        this.stageData = data;
        this.stage = "excavation";
      } else if(stageName === 'common') {
        let { data } = await this.axios.get(`/stage_data_excavation/${'평범한'}`);
        this.stageData = data;
        this.stage = "excavation";
      } else if(stageName === 'crude') {
        let { data } = await this.axios.get(`/stage_data_excavation/${'조잡한'}`);
        this.stageData = data;
        this.stage = "excavation";
      } else if(stageName === 'lesson') {
        this.stage = "lesson";
      }
    },
    async searchStage(event) {
      let stageName = event.currentTarget.value;
      let sortation = '';

      if (this.stage === 'world') {
        sortation = '세계편';
      } else if (this.stage === 'future') {
        sortation = '미래편';
      } else if (this.stage === 'space') {
        sortation = '우주편';
      } else if (this.stage === 'legend') {
        sortation = '레전드 스토리';
      } else if (this.stage === 'origin-legend') {
        sortation = '신레전드 스토리';
      } else if (this.stage === 'event') {
        sortation = '이벤트 스테이지';
      } else if(this.stage === 'guerrilla') {
        sortation = '게릴라 스테이지';
      } else if(this.stage === 'week') {
        sortation = '요일 스테이지'
      } else if(this.stage === 'drop') {
        sortation = '드롭 스테이지';
      } else if(this.stage === 'advent') {
        sortation = '강림 스테이지';
      } else if(this.stage === 'crazy') {
        sortation = '광란 스테이지';
      } else if(this.stage === 'cyclone') {
        sortation = '사이클론 스테이지';
      } else if(this.stage === 'arousal') {
        sortation = '각성 스테이지';
      } else if (this.stage === 'excavation') {
        sortation = '발굴 스테이지';
      } else if (this.stage === 'lesson') {
        sortation = '강습 스테이지';
      } else if (this.stage === 'collabo') {
        sortation = '콜라보 스테이지';
      }

      let stageInfo = [sortation, stageName];
      if(stageName.length !== 0) {
        let { data } = await this.axios.get(`/stage_data_name/${stageInfo}`);
        this.searchData = data;
      } else {
        this.searchData = [];
      }
    }
  },
  components: {
    contentComponent,
    searchComponent,
  },
};
</script>

<style scoped>
main {
  width: 100%;
}

nav {
  width: 55%;
  margin: 0.5% auto;
  font-size: 1.5rem;
  display: grid;
  grid-template-rows: 120%;
}

.select-stage {
  grid-template-columns: repeat(8, 1fr);
}

.event-stage {
  grid-template-columns: repeat(5, 1fr);
}

nav a {
  border: 1px solid #000000;
  border-left: none;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
}

nav a:nth-child(1) {
  border-left: 1px solid #000000;
}

nav a:hover {
  color: #ffffff;
}

.non-step:hover {
  background-color: #ffc038;
}

nav a:hover .step {
  display: flex;
}

.select {
  color: #ffc038;
}

.step {
  display: none;
  width: 100%;
  height: 100%;
  position: absolute;
  lefT: 0;
  top: 0;
}

.step-area {
  width: 100%;
  height: 100%;
  display: flex;
}

.step-area p {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
}

.event-stage > .week .step-area p {
  font-size: 1.1rem;
}

.event-stage > .drop .step-area p {
  font-size: 1.3rem;
}

.step-area p:hover {
  background-color: #ffc038;
  color: #ffffff;
}

input {
  border: 1px solid #000000;
  border-left: none;
  text-align: center;
  outline: 0;
}
</style>
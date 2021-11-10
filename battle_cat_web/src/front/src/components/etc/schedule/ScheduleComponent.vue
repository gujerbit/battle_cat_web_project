<template>
  <div id="schedule">
    <nav class="schedule-menu">
      <p v-for="(value, index) in menus" :key="value" :name="value" :class="`${value === menu.current ? 'select' : ''}`" @click="selectSchedule($event)">{{titles[index]}}</p>
    </nav>
    <div class="schedule-content">
      <daily-schedule-info-component v-show="menu.current === 'daily'" />
      <event-schedule-info-component v-show="menu.current === 'event'" />
      <gacha-schedule-info-component v-show="menu.current === 'gacha'" />
      <update-schedule-info-component v-show="menu.current === 'update'" />
      <patch-note-component v-show="menu.current === 'patch'" />
    </div>
  </div>
</template>

<script>
import { ref, getCurrentInstance, onBeforeMount } from 'vue';
import { setSchedule } from '../../../js/etc/schedule/schedule.js';
import dailyScheduleInfoComponent from './DailyScheduleInfoComponent.vue';
import eventScheduleInfoComponent from './EventScheduleInfoComponent.vue';
import gachaScheduleInfoComponent from './GachaScheduleInfoComponent.vue';
import updateScheduleInfoComponent from './UpdateScheduleInfoComponent.vue';
import patchNoteComponent from './PatchNoteComponent.vue';

export default {
  setup() {
    const { proxy } = getCurrentInstance(); //이렇게 하면 (proxy.전역 변수)로 가져올 수 있음
    const menus = ['daily', 'event', 'gacha', 'update', 'patch']; //메뉴에 강조 효과주기 위한 변수들
    const titles = ['하루 일정', '이벤트 일정', '가챠 일정', '업데이트 일정', '패치 노트']; //메뉴 탭 제목들

    const menu = ref({current: 'daily'}); //현재 선택된 메뉴 탭

    const selectSchedule = (event) => { //어떤 메뉴 선택한건지 알아오는 함수
      menu.value.current = event.currentTarget.getAttribute('name');
    };

    onBeforeMount(() => { //dom 부착 전에
      setSchedule(proxy.axios, proxy.store); //이벤트 일정들 전부 세팅
    });

    return { selectSchedule, menus, titles, menu };
  },
  components: {
    dailyScheduleInfoComponent,
    eventScheduleInfoComponent,
    gachaScheduleInfoComponent,
    updateScheduleInfoComponent,
    patchNoteComponent,
  },
}
</script>

<style scoped>
#schedule {
  width: 100%;
  height: 100%;
}

.schedule-menu {
  width: 100%;
  height: 5%;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  margin: 0.5% 0;
}

.schedule-menu > p {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 3.2rem;
  cursor: pointer;
  transition: all 0.5s;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  border: 2px solid #ffc038;
  border-left: none;
}

.schedule-menu > p:first-child {
  border-left: 2px solid #ffc038;
}
  
.select {
  background-color: #ffc038;
  color: #ffffff !important;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  pointer-events: none;
}
  
.schedule-menu p:hover {
  color: #ffffff;
  
}
</style>
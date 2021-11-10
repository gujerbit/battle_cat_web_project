<template>
  <section>
    <div id="schedule">
      <p class="schedule-title">가챠 일정</p>
      <div class="title">
        <p>내용</p>
        <p>시작</p>
        <p>종료 시간</p>
      </div>
      <div class="info" v-for="value in schedule.currentData" :key="value">
        <p :class="new Date(value.start) <= new Date(dateInfo.date) && new Date(value.end) >= new Date(dateInfo.date) ? 'select' : ''">{{value.content}}</p>
        <p :class="new Date(value.start) <= new Date(dateInfo.date) && new Date(value.end) >= new Date(dateInfo.date) ? 'select' : ''">{{value.start}}</p>
        <p :class="new Date(value.start) <= new Date(dateInfo.date) && new Date(value.end) >= new Date(dateInfo.date) ? 'select' : ''">{{value.end}}</p>
      </div>
    </div>
    <div class="pages">
      <p to="" @click="prevPage()">&laquo;</p>
      <p to="" :class="'page ' + `${pageInfo.currentPage === value ? 'select' : ''}`" v-for="value in pageInfo.totalPage[pageInfo.viewPage]" :key="value" @click="selectPage(value)">{{value}}</p>
      <p to="" @click="nextPage()">&raquo;</p>
    </div>
  </section>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';
import { getGachaSchedule } from '../../../js/etc/schedule/schedule.js';
import { pageDivision, pagination } from '../../../js/util/pagination.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();
    const date = new Date();

    const dateInfo = ref({
      date: date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
    });
    const schedule = ref({
      data: [], //현재 요일 전체 데이터
      currentData: [], //현재 페이지 데이터
    });
    const pageInfo = ref({
      totalPage: [], //전체 페이지
      divisionPage: 5, //한 번호에 몇 개씩 데이터를 보여줄 것인지 정하는 변수
      currentPage: 1, //현재 페이지
      viewPage: 0, //현재 번호
    });

    const nextPage = () => { //다음 페이지
      if(pageInfo.value.totalPage.length - 1 > pageInfo.value.viewPage) { //전체 페이지 수보다 현재 페이지 수가 적다면
        pageInfo.value.viewPage++; //현재 페이지 수 증가
        pageInfo.value.currentPage = pageInfo.value.totalPage[pageInfo.value.viewPage][0]; //해당 페이지에 있는 번호 중 가장 앞 번호를 현재 번호로 설정
        contentUpdate(); //현재 번호에 해당하는 내용 업데이트
      }
    };

    const prevPage = () => { //이전 페이지
      if(0 < pageInfo.value.viewPage) { //현재 페이지가 0보다 크다면
        pageInfo.value.viewPage--; //현재 페이지 감소
        pageInfo.value.currentPage = pageInfo.value.totalPage[pageInfo.value.viewPage][0]; //해당 페이지에 있는 번호 중 가장 앞 번호를 현재 번호로 설정
        contentUpdate(); //현재 번호에 해당하는 내용 업데이트
      }
    };

    const selectPage = (page) => { //번호 선택할 때 실행됨
      pageInfo.value.currentPage = page; //사용자가 누른 번호를 현재 번호로 설정
      contentUpdate(); //현재 번호에 해당하는 내용 업데이트
    };

    const contentUpdate = () => { //현재 번호에 해당하는 내용 업데이트
      schedule.value.currentData = pagination(getGachaSchedule(proxy.store), pageInfo.value.currentPage, pageInfo.value.divisionPage); //현재 번호에 해당하는 내용 페이지네이션
      pageInfo.value.totalPage = pageDivision(getGachaSchedule(proxy.store), pageInfo.value.divisionPage); //현재 요일 전체 데이터를 기반으로 총 페이지 수 정하기
    };

    onBeforeMount(() => { //dom에 부착되었을 때
      const loadData = setInterval(() => {
        if(getGachaSchedule(proxy.store) !== undefined) {
          schedule.value.data = getGachaSchedule(proxy.store);
          contentUpdate();
          clearInterval(loadData);
        }
      }, 100);
    });

    return { dateInfo, schedule, pageInfo, nextPage, prevPage, selectPage };
  },
}
</script>

<style scoped>
section {
  width: 100%;
  margin: 0 auto;
}

#schedule {
  width: 100%;
}

.schedule-title {
  width: 100%;
  border: 1px solid #000000;
  border-right: none;
  border-left: none;
  font-size: 2rem;
  text-align: center;
}

.select {
  color: #ffc038;
}

.title {
  width: 100%;
  display: grid;
  grid-template-columns: 2.5fr 1fr 1fr;
  text-align: center;
  font-size: 1.8rem;
  border-bottom: 1px solid #000000;
}

.info {
  width: 100%;
  display: grid;
  grid-template-columns: 2.5fr 1fr 1fr;
  text-align: center;
  align-items: center;
  font-size: 1.5rem;
}

.info p {
  margin: 2% 0;
  border-bottom: 1px solid #000000;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.pages {
  width: 100%;
  font-size: 3.5rem;
  display: flex;
  justify-content: space-around;
  align-items: center;
  border-bottom: 1px solid #000000;
}

.page {
  font-size: 1.8rem;
}

.pages p {
  cursor: pointer;
}
</style>
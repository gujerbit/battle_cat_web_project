<template>
  <div id="admin-log">
    <div class="log">
      <div class="content" v-for="value in log.current" :key="value">
        <p>
          <router-link class="name" :to="`/userInfo/${value.name}`">{{value.name}}</router-link>
          {{value.content.split(`${value.name}`)[1].split(`${value.target}`)[0]}}
          <router-link class="target" :to="`/userInfo/${value.target}`">{{value.target}}</router-link>
          {{value.content.split(`${value.name}`)[1].split(`${value.target}`)[1]}}
        </p>
        <p>{{value.log_date}}</p>
      </div>
    </div>
    <div class="pages">
      <p class="prev" @click="prevPage()">&lt;</p>
      <p :class="'page ' + `${pageInfo.currentPage === value ? 'select' : ''}`" v-for="value in pageInfo.totalPage[pageInfo.viewPage]" :key="value" @click="selectPage(value)">{{value}}</p>
      <p class="next" @click="nextPage()">&gt;</p>
    </div>
  </div>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';
import { pagination, pageDivision } from '../../../js/util/pagination.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const log = ref({
      data: [],
      current: [],
    });

    const pageInfo = ref({
      totalPage: [], //전체 페이지
      divisionPage: 15, //한 번호에 몇 개씩 데이터를 보여줄 것인지 정하는 변수
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
      log.value.current = pagination(log.value.data, pageInfo.value.currentPage, pageInfo.value.divisionPage); //현재 번호에 해당하는 내용 페이지네이션
      pageInfo.value.totalPage = pageDivision(log.value.data, pageInfo.value.divisionPage); //현재 요일 전체 데이터를 기반으로 총 페이지 수 정하기
    };

    onBeforeMount(async () => {
      let { data } = await proxy.axios.get('/get_admin_log');

      log.value.data = data.reverse();
      contentUpdate();
    });

    return { log, pageInfo, nextPage, prevPage, selectPage };
  }
}
</script>

<style scoped>
#admin-log {
  width: 100%;
  height: 90%;
  display: grid;
  grid-template-rows: 9.25fr 0.75fr;
}

.log {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: repeat(15, 1fr);
  align-items: center;
  font-size: 2.5rem;
}

.content {
  width: 100%;
  height: 90%;
  display: grid;
  grid-template-columns: 8fr 2fr;
  justify-items: center;
  align-items: center;
  border: 2px solid #ffc038;
  border-radius: 15px;
}

.log p {
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
}

.log a {
  transition: all 1s;
}

.name {
  color: #000000;
  text-shadow: -0.5px 0 #ffc038, 0 0.5px #ffc038, 0.5px 0 #ffc038, 0 -0.5px #ffc038;
}

.name:hover {
  color: #ffffff;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
}

.target {
  color: #ffffff;
}

.target:hover {
  color: #000000;
  text-shadow: -0.5px 0 #ffc038, 0 0.5px #ffc038, 0.5px 0 #ffc038, 0 -0.5px #ffc038;
}

.pages {
  width: 100%;
  height: 100%;
  margin: 0 auto;
  display: flex;
  justify-content: space-around;
  align-items: center;
  border: 2px solid #ffc038;
  border-radius: 15px;
}
  
.pages p {
  width: 2%;
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  transition: all 0.5s;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
}
  
.pages p:hover {
  color: #ffffff;
  text-shadow: -0.5px 0 #ffc038, 0 0.5px #ffc038, 0.5px 0 #ffc038, 0 -0.5px #ffc038;
}
  
.page {
  width: 100%;
  height: 100%;
  font-size: 3rem;
}
  
.select {
  pointer-events: none;
  color: #ffffff !important;
  text-shadow: -2px 0 3px #ffc038, 0 2px 3px #ffc038, 2px 0 3px #ffc038, 0 -2px 3px #ffc038 !important;
}
  
.prev, .next {
  width: 100%;
  height: 100%;
  color: #ffffff;
  font-size: 3rem;
}
</style>
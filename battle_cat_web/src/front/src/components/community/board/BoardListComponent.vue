<template>
  <div id="board-list">
    <div class="board">
      <router-link @click="viewCountUpdate(value.idx, proxy.axios)" :to="`/board_data/${value.idx}`" class="content" v-for="value in board.current" :key="value">
        <div class="info">
          <p class="title">
            <span>[{{value.type === 'normal' ? '일반' : value.type === 'notice' ? '공지' : value.type === 'info' ? '정보/공략' : value.type === 'ask' ? '질문' : value.type === 'creative' ? '창작/번역' : '문의/피드백/정보'}}]</span>
            {{value.title.length > 20 ? (value.title.substring(0, 20) + '...') : value.title}}
          </p>
          <p class="name" :class="value.grade">
            {{value.name}}
            <span class="count">
              <p>조회 {{value.view_count}}</p>
              <p>추천 {{value.good_count}}</p>
              <p>비추천 {{value.bad_count}}</p>
            </span>
          </p>
        </div>
        <p class="write-date">{{new Date(new Date(value.writing_date) + 3240 * 10000).toISOString().replace('T', ' ').replace(/\..*/, '')}}</p>
      </router-link>
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
import { checkReject } from '../../../js/community/user/user.js';
import { pagination, pageDivision } from '../../../js/util/pagination.js';
import { viewCountUpdate } from '../../../js/community/board/board.js';
import { rejectAlert } from '../../../js/util/alert.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const board = ref({
      list: [],
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
      board.value.current = pagination(board.value.list, pageInfo.value.currentPage, pageInfo.value.divisionPage); //현재 번호에 해당하는 내용 페이지네이션
      pageInfo.value.totalPage = pageDivision(board.value.list, pageInfo.value.divisionPage); //현재 요일 전체 데이터를 기반으로 총 페이지 수 정하기
    };

    onBeforeMount(async () => {
      checkReject(proxy.axios);

      try {
        let { data } = await proxy.axios.get('/get_board_list', {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        board.value.list = data.reverse();
        contentUpdate();
      } catch (error) {
        rejectAlert();
      }
    });

    return { board, pageInfo, proxy, nextPage, prevPage, selectPage, viewCountUpdate };
  }
}
</script>

<style scoped>
#board-list {
  width: 100%;
  height: 90%;
  display: grid;
  grid-template-rows: 97% 5%;
  justify-items: center;
  align-items: center;
}

#board-list * {
  text-decoration: none;
}

.board {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: repeat(15, 1fr);
}

.content {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: left;
  align-items: center;
  border: 1px solid #ffc038;
  border-top: none;
}

.content:nth-child(1) {
  border-top: 1px solid #ffc038;
}

.info {
  width: 50%;
  height: 100%;
  display: grid;
  grid-template-rows: repeat(2, 1fr);
  justify-items: left;
  align-items: center;
  margin-left: 1%;
}

.title {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: left;
  align-items: center;
  font-size: 2.3rem;
}

.title span {
  font-size: 1.7rem;
  margin-right: 1%;
}

.name {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: left;
  align-items: center;
  font-size: 1.8rem;
}

.developer {
  color: #fe9ec4;
}

.operator {
  color: #a97ee4;
}

.admin {
  color: #84a9ea;
}

.count {
  width: 25%;
  height: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  font-size: 1.5rem;
}

.write-date {
  width: 50%;
  display: flex;
  justify-content: left;
  align-items: center;
  padding-left: 40%;
  font-size: 1.5rem;
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
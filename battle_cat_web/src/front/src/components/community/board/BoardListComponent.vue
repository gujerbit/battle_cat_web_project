<template>
  <div id="board-list">
    <div class="title">
      <p>글 번호</p>
      <p>작성자</p>
      <p>제목</p>
      <p>작성 날짜</p>
    </div>
    <div class="board">
      <router-link :to="`/board_data/${value.idx}`" class="content" v-for="value in board.current" :key="value">
        <p>{{value.idx}}</p>
        <p>{{value.name}}</p>
        <p>{{value.title.length > 20 ? (value.title.substring(0, 20) + '...') : value.title}}</p>
        <p>{{new Date(new Date(value.writing_date) + 3240 * 10000).toISOString().replace('T', ' ').replace(/\..*/, '')}}</p>
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

      let { data } = await proxy.axios.get('/get_board_list');
      board.value.list = data.reverse();
      contentUpdate();
    });

    return { board, pageInfo, nextPage, prevPage, selectPage };
  }
}
</script>

<style scoped>
#board-list {
  width: 100%;
  height: 89%;
  display: grid;
  grid-template-rows: 0.5fr 9fr 0.5fr;
  justify-items: center;
  align-items: center;
}

.board {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: repeat(15, 1fr);
}

.content {
  width: 100%;
  height: 95%;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  justify-items: center;
  align-items: center;
  border: 2px solid #ffc038;
  border-radius: 15px;
  transition: all 1s;
  cursor: pointer;
}

.content:hover {
  transform: scale(95%);
}

.content p {
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 3rem;
}

.title {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  justify-items: center;
  align-items: center;
}

.title p {
  width: 95%;
  height: 95%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 2px solid #ffc038;
  border-radius: 15px;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 3rem;
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
<template>
  <main>
    <div id="board">
      <div class="menu">
        <nav>
          <p @click="menu.type = 'all'; search()" :class="menu.type === 'all' ? 'choose' : ''">전체</p>
          <p @click="menu.type = 'normal'; search()" :class="menu.type === 'normal' ? 'choose' : ''">일반</p>
          <p @click="menu.type = 'notice'; search()" :class="menu.type === 'notice' ? 'choose' : ''">공지</p>
          <p @click="menu.type = 'info'; search()" :class="menu.type === 'info' ? 'choose' : ''">정보/공략</p>
          <p @click="menu.type = 'ask'; search()" :class="menu.type === 'ask' ? 'choose' : ''">질문</p>
          <p @click="menu.type = 'creative'; search()" :class="menu.type === 'creative' ? 'choose' : ''">창작/번역</p>
          <p @click="menu.type = 'feedback'; search()" :class="menu.type === 'feedback' ? 'choose' : ''">문의/피드백/재보</p>
        </nav>
        <div class="search">
          <input @input="search()" v-model="menu.value" type="text" placeholder="검색">
          <select @change="search()" v-model="menu.valueType">
            <option value="all" selected>전체</option>
            <option value="title_content">제목 + 내용</option>
            <option value="title">제목</option>
            <option value="content">내용</option>
            <option value="writer">작성자</option>
          </select>
        </div>
        <router-link to="/board_writing">글 쓰기</router-link>
      </div>
      <div class="board-list">
        <router-link @click="countUpdate(value.idx, 'view', proxy.axios)" :to="`/board_data/${value !== undefined ? value.idx : ''}`" class="content" v-for="value in board.current" :key="value">
          <div class="info">
            <p class="title">
              <span>[{{value.type === 'normal' ? '일반' : value.type === 'notice' ? '공지' : value.type === 'info' ? '정보/공략' : value.type === 'ask' ? '질문' : value.type === 'creative' ? '창작/번역' : '문의/피드백/정보'}}]</span>
              {{value.title.length > 20 ? (value.title.substring(0, 20) + '...') : value.title}}
            </p>
            <p class="name">
              <img :src="require(`../../../assets/res/etc/item/${value.grade === 'user' ? 'cats_eye_rare.png' : value.grade === 'admin' ? 'cats_eye_super_rare.png' : value.grade === 'operator' ? 'cats_eye_uber_rare.png' : 'cats_eye_legend_rare.png'}`)" alt="">
              {{value.name}}
              <span class="count">
                <p>조회 {{getCountData(board.countList, value.idx, 'view')}}</p>
                <p>추천 {{getCountData(board.countList, value.idx, 'good')}}</p>
                <p>비추천 {{getCountData(board.countList, value.idx, 'bad')}}</p>
                <p>댓글 {{getCommentData(board.commentList, value.idx)}}</p>
              </span>
            </p>
          </div>
          <!-- <p class="write-date">{{new Date(new Date(value.writing_date) + 3240 * 10000).toISOString().replace('T', ' ').replace(/\..*/, '')}}</p> -->
          <p class="write-date">{{new Date(value.writing_date).toLocaleString("ko-KR", {timeZone: 'Asia/Seoul'})}}</p>
        </router-link>
      </div>
      <div class="pages">
        <p class="prev" @click="prevPage()">&lt;</p>
        <p :class="'page ' + `${pageInfo.currentPage === value ? 'select' : ''}`" v-for="value in pageInfo.totalPage[pageInfo.viewPage]" :key="value" @click="selectPage(value)">{{value}}</p>
        <p class="next" @click="nextPage()">&gt;</p>
      </div>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/community" class="community-page">커뮤니티 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';
import { checkReject } from '../../../js/community/user/user.js';
import { pagination, pageDivision } from '../../../js/util/pagination.js';
import { countUpdate, getCountData, searchBoardData, getCommentData } from '../../../js/community/board/board.js';
import { rejectAlert } from '../../../js/util/alert.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const board = ref({
      data: [],
      list: [],
      countList: [],
      commentList: [],
      current: [],
    });

    const menu = ref({
      type: 'all',
      value: '',
      valueType: 'all',
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

    const search = () => {
      if(menu.value.type === 'all' || board.value.list.length <= 0) {
        board.value.list = [];
        board.value.data.forEach(res => board.value.list.push(res));
      }

      board.value.list = searchBoardData(board.value.data, menu.value.type, menu.value.value, menu.value.valueType);

      if(board.value.list.length <= 0) {
        board.value.current = [];
        pageInfo.value.totalPage = [1];
      } else contentUpdate();
    };

    onBeforeMount(async () => {
      checkReject(proxy.axios);

      try {
        let { data:boardSize } = await proxy.axios.get('/get_board_list_size', {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        const boardSizeArr = [];
        const boardData = [];

        for(let i = 0; i < Math.ceil(boardSize / 100); i++) boardSizeArr.push(i * 100);

        for(let i = 0; i < boardSizeArr.length; i++) {
          let { data:boards } = await proxy.axios.get(`/get_board_list/${boardSizeArr[i]}`, {
            headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
          });

          for(let j = 0; j < boards.length; j++) boardData.push(boards[j]);
        }
        
        let { data:countSize } = await proxy.axios.get('/get_board_count_list_size', {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        const countSizeArr = [];
        const countData = [];

        for(let i = 0; i < Math.ceil(countSize / 100); i++) countSizeArr.push(i * 100);

        for(let i = 0; i < countSizeArr.length; i++) {
          let { data:counts } = await proxy.axios.get(`/get_board_count_list/${countSizeArr[i]}`, {
            headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
          });

          for(let j = 0; j < counts.length; j++) countData.push(counts[j]);
        }

        let { data:commentSize } = await proxy.axios.get('/get_comment_list_size', {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        const commentSizeArr = [];
        const commentData = [];

        for(let i = 0; i < Math.ceil(commentSize / 100); i++) commentSizeArr.push(i * 100);

        for(let i = 0; i < commentSizeArr.length; i++) {
          let { data:comments } = await proxy.axios.get(`/get_comment_list/${commentSizeArr[i]}`, {
            headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
          });

          for(let j = 0; j < comments.length; j++) commentData.push(comments[j]);
        }
        console.log(commentData);
        const list = boardData.reverse().filter(res => !res.remove);
        board.value.list = list;
        board.value.data = list;
        board.value.countList = countData;
        board.value.commentList = commentData;
        contentUpdate();
      } catch (error) {
        rejectAlert();
      }
    });

    return { menu, board, pageInfo, proxy, nextPage, prevPage, selectPage, countUpdate, getCountData, search, getCommentData };
  },
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
}

main * {
  text-decoration: none;
}

#board {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  display: grid;
  grid-template-rows: 5% 89% 5%;
  justify-items: center;
  align-items: center;
}

.menu {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: left;
  align-items: center;
  font-size: 1.8rem;
}

nav {
  width: 60%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  justify-items: center;
  align-items: center;
}

nav p {
  width: 100%;
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1.5px solid #ffc038;
  border-left: none;
  cursor: pointer;
}

nav p:first-child {
  border-left: 1.5px solid #ffc038;
}

.search {
  width: 30%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.search input {
  width: 60%;
  height: 80%;
  border: 1.5px solid #ffc038;
  border-radius: 5px 0 0 5px;
  text-align: center;
  font-size: 1.8rem;
  outline: 0;
}

.search input::placeholder {
  color: #000000;
}

.search select {
  width: 30%;
  height: 80%;
  border: 1.5px solid #ffc038;
  border-left: none;
  border-radius: 0 5px 5px 0;
  text-align: center;
  font-size: 1.8rem;
  outline: 0;
}

.menu a {
  width: 10%;
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1.5px solid #ffc038;
  border-radius: 5px;
  cursor: pointer;
}

.choose {
  color: #ffc038;
  pointer-events: none;
}

.board-list {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: repeat(15, 1fr);
  overflow: auto;
  border-top: 1.5px solid #ffc038;
}

.board-list::-webkit-scrollbar {
  display: none;
}

.content {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: left;
  align-items: center;
  border: 1.5px solid #ffc038;
  border-top: none;
  cursor: pointer;
}

.content:nth-child(15) {
  border-bottom: none;
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
  font-size: 2.3rem;
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

.name img {
  width: 3%;
  height: 85%;
  margin-right: 1%;
}

.count {
  width: 30%;
  height: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
  font-size: 1.8rem;
}

.write-date {
  width: 50%;
  height: 100%;
  display: flex;
  justify-content: left;
  align-items: center;
  font-size: 1.8rem;
  padding-left: 37.5%;
}

.pages {
  width: 100%;
  height: 100%;
  margin: 0 auto;
  display: flex;
  justify-content: space-around;
  align-items: center;
  border: 1.5px solid #ffc038;
}
  
.pages p {
  width: 2.5%;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}
  
.page {
  width: 100%;
  height: 100%;
  font-size: 2.8rem;
}
  
.select {
  color: #ffc038;
  pointer-events: none;
}
  
.prev, .next {
  width: 100%;
  height: 100%;
  font-size: 2.8rem;
}

.main-page, .community-page {
  width: 4.5%;
  height: 100%;
  border: 2px solid #ffc038;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  position: absolute;
  top: 0;
  writing-mode: vertical-lr;
  display: flex;
  justify-content: center;
  align-items: center;
  opacity: 0;
  transition: all 1.5s;
  font-size: 3rem;
}

.main-page {
  border-left: none;
  border-radius: 0 15px 15px 0;
  left: 0;
}

.community-page {
  border-right: none;
  border-radius: 15px 0 0 15px;
  right: 0;
}
  
.main-page:hover, .community-page:hover {
  opacity: 1;
}
</style>
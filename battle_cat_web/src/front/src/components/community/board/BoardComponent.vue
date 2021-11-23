<template>
  <main>
    <div id="board">
      <nav class="menu">
        <p class="choose">전체</p>
        <p>공지</p>
        <p>일반</p>
        <p>정보/공략</p>
        <p>질문</p>
        <p>창작/번역</p>
        <p>문의/피드백/제보</p>
      </nav>
      <div class="header">
        <input type="text" placeholder="검색">
        <select>
          <option selected>전체</option>
          <option>제목 + 내용</option>
          <option>제목</option>
          <option>내용</option>
          <option>작성자</option>
        </select>
        <router-link to="/board_writing">글 쓰기</router-link>
      </div>
      <board-list-component />
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/community" class="community-page">커뮤니티 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { onBeforeMount, getCurrentInstance } from 'vue';
import { checkReject } from '../../../js/community/user/user.js';

import boardListComponent from './BoardListComponent.vue';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    onBeforeMount(() => {
      checkReject(proxy.axios);
    });

    return {};
  },
  components: {
    boardListComponent
  }
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
}

#board {
  width: 90%;
  height: 100%;
  margin: 0 auto;
}

.menu {
  width: 100%;
  height: 5%;
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  justify-items: center;
  align-items: center;
}

.menu p {
  width: 95%;
  height: 95%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 2px solid #ffc038;
  border-radius: 15px;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.5rem;
  cursor: pointer;
  transition: all 1s;
}

.menu p:hover {
  transform: scale(95%);
}

.choose {
  background-color: #ffc038;
  color: #ffffff !important;
  pointer-events: none;
}

.header {
  width: 100%;
  height: 5%;
  display: flex;
  justify-content: right;
  align-items: center;
}

.header input {
  width: 20%;
  height: 95%;
  border: 2px solid #ffc038;
  border-radius: 15px 0 0 15px;
  text-align: center;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
  outline: 0;
}

.header input::placeholder {
  color: #ffc038;
}

.header select {
  width: 10%;
  height: 95%;
  border: 2px solid #ffc038;
  border-left: none;
  border-radius: 0 15px 15px 0;
  text-align: center;
  outline: 0;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
  transition: all 1s;
  cursor: pointer;
}

.header select:hover {
  background-color: #ffc038;
  color: #ffffff !important;
}

.header a {
  width: 10%;
  height: 95%;
  border: 2px solid #ffc038;
  border-radius: 15px;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.5rem;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 2.5%;
  transition: all 1s;
}

.header a:hover {
  transform: scale(95%);
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
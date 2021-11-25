<template>
  <main>
    <div id="board">
      <div class="menu">
        <nav>
          <p class="choose">전체</p>
          <p>일반</p>
          <p>공지</p>
          <p>정보/공략</p>
          <p>질문</p>
          <p>창작/번역</p>
          <p>문의/피드백/재보</p>
        </nav>
        <div class="search">
          <input type="text" placeholder="검색">
          <select>
            <option selected>전체</option>
            <option>제목 + 내용</option>
            <option>제목</option>
            <option>내용</option>
            <option>작성자</option>
          </select>
        </div>
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

main * {
  text-decoration: none;
}

#board {
  width: 90%;
  height: 100%;
  margin: 0 auto;
}

.menu {
  width: 100%;
  height: 5%;
  margin: 0.5% 0;
  display: flex;
  justify-content: left;
  align-items: center;
  font-size: 1.5rem;
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
  width: 95%;
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #ffc038;
  border-radius: 15px;
}

.search {
  width: 25%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.search input {
  width: 60%;
  height: 80%;
  border: 1px solid #ffc038;
  border-radius: 15px 0 0 15px;
  text-align: center;
  font-size: 1.5rem;
  outline: 0;
}

.search select {
  width: 30%;
  height: 80%;
  border: 1px solid #ffc038;
  border-left: none;
  border-radius: 0 15px 15px 0;
  text-align: center;
  font-size: 1.5rem;
  outline: 0;
}

.menu a {
  width: 10%;
  height: 80%;
  margin-left: 5%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1px solid #ffc038;
  border-radius: 15px;
}

.choose {
  background-color: #ffc038;
  color: #ffffff;
  pointer-events: none;
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
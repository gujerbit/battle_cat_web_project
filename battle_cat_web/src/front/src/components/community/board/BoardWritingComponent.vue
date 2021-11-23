<template>
  <main>
    <div id="board-writing">
      <div class="title">
        <input v-model="writeInfo.title" type="text" placeholder="글 제목 입력">
        <select v-model="writeInfo.type">
          <option value="all">전체</option>
          <option value="normal">일반</option>
          <option value="notice">공지</option>
          <option value="info">정보/공략</option>
          <option value="ask">질문</option>
          <option value="creative">창작/번역</option>
          <option value="feedback">문의/피드백/제보</option>
        </select>
      </div>
      <div class="option">
        <p>bold</p>
        <p>italic</p>
      </div>
      <div class="content">
        <div class="editor">
          <textarea v-model="writeInfo.content"></textarea>
          <div class="view" v-html="writeInfo.content"></div>
        </div>
      </div>
      <button class="writing" @click="writing(writeInfo.title, writeInfo.content, proxy.axios)">글 작성</button>
      <router-link to="/board">취소</router-link>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/board" class="board-page">게시판 목록으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, getCurrentInstance } from 'vue';
import { writing } from '../../../js/community/board/board.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const writeInfo = ref({
      title: '',
      content: '',
      type: 'all',
    });

    return { writeInfo, proxy, writing };
  },
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
}

#board-writing {
  width: 90%;
  height: 100%;
  margin: 0 auto;
}

.title {
  width: 100%;
  height: 5%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0.5% 0;
}

.title input {
  width: 90%;
  height: 95%;
  border: 2px solid #ffc038;
  border-radius: 15px 0 0 15px;
  padding-left: 1%;
}

.title select {
  width: 10%;
  height: 95%;
  border: 2px solid #ffc038;
  border-left: none;
  border-radius: 0 15px 15px 0;
}

.option {
  width: 100%;
  height: 5%;
}

.content {
  width: 100%;
  height: 80%;
}

.editor {
  width: 100%;
  height: 100%;
  border: 2px solid #ffc038;
  border-radius: 15px;
  position: relative;
}

.editor textarea {
  width: 100%;
  height: 100%;
  border-radius: 15px;
  resize: none;
  opacity: 0;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 10;
}

.editor .view {
  width: 100%;
  height: 100%;
  border-radius: 15px;
  position: absolute;
  top: 0;
  left: 0;
  white-space: pre;
  padding-left: 0.5%;
  font-size: 3rem;
}

.main-page, .board-page {
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

.board-page {
  border-right: none;
  border-radius: 15px 0 0 15px;
  right: 0;
}
  
.main-page:hover, .board-page:hover {
  opacity: 1;
}
</style>
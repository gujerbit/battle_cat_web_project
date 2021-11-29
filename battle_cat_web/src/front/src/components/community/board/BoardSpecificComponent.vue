<template>
  <main>
    <div id="board">
      <p class="title">{{board.content.title}}</p>
      <div class="content">
        <div id="editor" />
      </div>
      <div class="btn-field">
        <button class="user">작성자 정보 확인</button>
        <button class="good" @click="countUpdate(board.content.idx, 'good', proxy.axios)">{{getCountData(board.count, board.content.idx, 'good')}} 추천</button>
        <button class="bad" @click="countUpdate(board.content.idx, 'bad', proxy.axios)">{{getCountData(board.count, board.content.idx, 'bad')}} 비추천</button>
        <button class="report">신고</button>
        <router-link to="">수정</router-link>
        <router-link to="">삭제</router-link>
      </div>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/board" class="board-page">게시판 목록으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, onBeforeMount, onMounted, getCurrentInstance } from 'vue';
import { useRoute } from 'vue-router';
import { Quill } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import { rejectAlert } from '../../../js/util/alert';
import { countUpdate, getCountData } from '../../../js/community/board/board.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();
    const route = useRoute();

    const board = ref({
      content: [],
      count: [],
    });

    let quill;

    onBeforeMount(async () => {
      try {
        let { data } = await proxy.axios.get(`/get_board_data/${route.params.idx}`, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        let { data:count } = await proxy.axios.get(`/get_board_count/${route.params.idx}`, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        board.value.content = data;
        board.value.count = count;
      } catch (error) {
        rejectAlert();
      }
    });

    onMounted(() => {
      setTimeout(() => {
        quill = new Quill('#editor', {
          modules: {
            toolbar: {
              container: '#editor'
            }
          },
          readOnly: true,
          theme: 'snow',
        });

        quill.root.innerHTML = board.value.content.content;
      }, 100);
    });

    return { board, quill, proxy, countUpdate, getCountData };
  },
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
  overflow: auto;
}

.title {
  width: 100%;
  height: 5%;
  display: flex;
  justify-content: left;
  align-items: center;
  margin: 0.5% 0;
  border: 2px solid #ffc038;
  padding-left: 2%;
  font-size: 3rem;
}

.content {
  width: 100%;
  height: 80%;
}

.btn-field {
  width: 100%;
  height: 5%;
  margin-top: 1%;
  display: flex;
  justify-content: right;
  align-items: center;
}

.btn-field a {
  width: 10%;
  height: 90%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 2px solid #ffc038;
  margin-left: 1%;
  text-decoration: none;
  font-size: 2.3rem;
}

.btn-field button {
  width: 10%;
  height: 90%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 2px solid #ffc038;
  margin-left: 1%;
  background-color: #ffffff;
  font-size: 2.3rem;
  cursor: pointer;
}

.good {
  color: #adf6a9;
}

.bad {
  color: #f2828f;
}

.report {
  color: #ff0000;
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
  text-decoration: none;
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
<template>
  <main>
    <div id="board">
      <p v-for="value in board.content" :key="value"><span v-html="value.content"></span></p>
      <router-link to="">수정</router-link>
      <router-link to="">삭제</router-link>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/board" class="board-page">게시판 목록으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';
import { useRoute } from 'vue-router';

export default {
  setup() {
    const { proxy } = getCurrentInstance();
    const route = useRoute();

    const board = ref({
      content: [],
    });

    onBeforeMount(async () => {
      let { data } = await proxy.axios.get(`/get_board_data/${route.params.idx}`);
      board.value.content.push(data);
    });

    return { board };
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
<template>
  <main>
    <div id="board">
      <p class="title">{{board.content.title}}</p>
      <div class="content">
        <div id="view-editor" />
      </div>
      <div class="comment">
        <div v-show="comment.data.length > 0" class="comment-list">
          <div class="comment-content" v-for="value in comment.data" :key="value">
            <div @click="comment.commentIdx = value.idx; comment.parentComment = value.comment" v-if="value.comment_idx <= 0" class="comment-main">
              <img :src="require(`../../../assets/res/unit/${value.profile_img}`)" alt="">
              <div class="comment-info">
                <section>
                  <img :src="require(`../../../assets/res/etc/item/${value.grade === 'user' ? 'cats_eye_rare.png' : value.grade === 'admin' ? 'cats_eye_super_rare.png' : value.grade === 'operator' ? 'cats_eye_uber_rare.png' : 'cats_eye_legend_rare.png'}`)" alt="">
                  <p>{{value.name}}</p>
                </section>
                <p>{{new Date(value.comment_date).toLocaleString("ko-KR", {timeZone: 'Asia/Seoul'})}}</p>
              </div>
              <p class="comment-data">{{!value.remove ? value.comment : '삭제된 댓글입니다.'}}</p>
              <div class="comment-btn-area">
                <button>
                  <router-link :to="`/userInfo/${value.name}`">작성자 정보 확인</router-link>
                </button>
                <button @click="comment.update = true; comment.updateComment = value.comment; comment.updateIdx = value.idx" v-if="value.email === getAccountInfo().email">수정하기</button>
                <button @click="deleteComment(value.idx, proxy.axios)" v-if="value.email === getAccountInfo().email && !value.remove">삭제하기</button>
                <button v-if="value.email !== getAccountInfo().email">신고하기</button>
              </div>
            </div>
            <div @click="comment.commentIdx = value.comment_idx; comment.parentComment = value.comment" v-else class="comment-sub">
              <img :src="require(`../../../assets/res/unit/${value.profile_img}`)" alt="">
              <div class="comment-info">
                <section>
                  <img :src="require(`../../../assets/res/etc/item/${value.grade === 'user' ? 'cats_eye_rare.png' : value.grade === 'admin' ? 'cats_eye_super_rare.png' : value.grade === 'operator' ? 'cats_eye_uber_rare.png' : 'cats_eye_legend_rare.png'}`)" alt="">
                  <p>{{value.name}}</p>
                </section>
                <p>{{new Date(value.comment_date).toLocaleString("ko-KR", {timeZone: 'Asia/Seoul'})}}</p>
              </div>
              <p>
                <span>└{{value.parent_comment.length > 10 ? (value.parent_comment.substring(0, 10) + '...') : value.parent_comment}}</span>
                {{(!value.remove ? value.comment : '삭제된 댓글입니다.')}}
              </p>
              <div class="comment-btn-area">
                <button>
                  <router-link :to="`/userInfo/${value.name}`">작성자 정보 확인</router-link>
                </button>
                <button @click="comment.update = true; comment.updateComment = value.comment; comment.updateIdx = value.idx" v-if="value.email === getAccountInfo().email">수정하기</button>
                <button @click="deleteComment(value.idx, proxy.axios)" v-if="value.email === getAccountInfo().email && !value.remove">삭제하기</button>
                <button v-if="value.email !== getAccountInfo().email">신고하기</button>
              </div>
            </div>
          </div>
        </div>
        <div class="comment-btn-field">
          <textarea :placeholder="comment.parentComment.length > 0 ? ('└ ' + comment.parentComment) : '댓글을 입력하세요'" v-model="comment.content" type="text" />
          <div class="comment-write-btn-field">
            <button @click="comment.commentIdx = 0; comment.parentComment = ''" :disabled="comment.commentIdx <= 0">대댓 취소</button>
            <button @click="quillSetting(comment.data.length > 0 ? comment.data.length : 0)">댓글 작성</button>
          </div>
          <p :class="`comment-length ${comment.content.length > 300 ? 'excess' : ''}`">{{comment.content.length}}</p>
        </div>
      </div>
      <div class="btn-field">
        <router-link :to="`/userInfo/${board.content.name}`" class="user">작성자 정보 확인</router-link>
        <div class="view">{{getCountData(board.count, board.content.idx, 'view')}} 조회</div>
        <button class="good" @click="countUpdate(board.content.idx, 'good', proxy.axios)">{{getCountData(board.count, board.content.idx, 'good')}} 추천</button>
        <button class="bad" @click="countUpdate(board.content.idx, 'bad', proxy.axios)">{{getCountData(board.count, board.content.idx, 'bad')}} 비추천</button>
        <button class="report" v-if="getAccountInfo().email !== board.content.email">신고</button>
        <router-link :to="`/board_updating/${board.content.idx}`" v-if="getAccountInfo().email === board.content.email">수정</router-link>
        <button @click="deleteBoard(board.content.idx, proxy.axios)" v-if="getAccountInfo().email === board.content.email">삭제</button>
      </div>
    </div>
    <div class="comment-popup" v-if="comment.update">
      <div class="comment-update">
        <textarea v-model="comment.updateComment"></textarea>
        <div class="popup-btn-field">
          <button @click="updateComment(comment.updateIdx, comment.updateComment, proxy.axios)">완료</button>
          <button @click="comment.update = false; comment.updateComment = ''; comment.updateIdx = 0">취소</button>
        </div>
      </div>
      <div class="background" />
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
import { countUpdate, getCountData, deleteBoard, writingComment, updateComment, deleteComment } from '../../../js/community/board/board.js';
import { getAccountInfo } from '../../../js/community/admin/admin.js';
import { checkReject } from '../../../js/community/user/user';

export default {
  setup() {
    const { proxy } = getCurrentInstance();
    const route = useRoute();

    const board = ref({
      content: [],
      count: [],
    });

    const comment = ref({
      data: [],
      content: '',
      commentIdx: 0,
      parentComment: '',
      update: false,
      updateIdx: 0,
      updateComment: '',
    });

    let quill;

    const quillSetting = (saltIdx) => {
      if(comment.value.commentIdx > 0) {
        comment.value.data.forEach(res => {
          if(res.idx === comment.value.commentIdx) {
            saltIdx = res.salt_idx;
          }
        });

        let saltValue = 1;

        comment.value.data.forEach(res => {
          if(res.comment_idx === comment.value.commentIdx) saltValue++;
        });

        saltIdx += '.' + saltValue;
        saltIdx *= 1;
      }

      writingComment(route.params.idx, comment.value.commentIdx > 0 ? comment.value.commentIdx : 0, saltIdx, comment.value.content, comment.value.parentComment, proxy.axios);
    };

    onBeforeMount(async () => {
      const temp = 1;

      if(temp === 1) {
        alert('개편중입니다');
        location.href = '/';
      } else {
        checkReject(proxy.axios);

        try {
          let { data } = await proxy.axios.get(`/get_board_data/${route.params.idx}`, {
            headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
          });

          board.value.content = data;

          if(data.remove) {
            alert('해당 게시물은 삭제되었습니다!');
            location.href = '/board';
          }

          let { data:count } = await proxy.axios.get(`/get_board_count/${route.params.idx}`, {
            headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
          });

          board.value.count = count;

          let { data:commentData } = await proxy.axios.get(`/get_comment_data/${route.params.idx}`, {
            headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
          });

          comment.value.data = commentData;
          let temp = {};

          for(let i = 0; i < comment.value.data.length - 1; i++) {
            for(let j = i; j < comment.value.data.length - 1; j++) {
              if(comment.value.data.length <= 1) break;

              if(comment.value.data[i].salt_idx * 1 > comment.value.data[j + 1].salt_idx * 1) {
                temp = comment.value.data[i];
                comment.value.data[i] = comment.value.data[j + 1];
                comment.value.data[j + 1] = temp;
              }
            }
          }
        } catch (error) {
          rejectAlert();
        }
      }
    });

    onMounted(() => {
      quill = new Quill('#view-editor', {
        modules: {
          toolbar: {
            container: '#view-editor'
          }
        },
        readOnly: true,
        theme: 'snow',
      });

      quill.root.innerHTML = '';
      
      const interval = setInterval(() => {
        if(board.value.content !== undefined && board.value.content.content.length > 0) {
          quill.root.innerHTML = board.value.content.content;
          clearInterval(interval);
        }
      }, 100);
    });

    return { board, quill, proxy, comment, countUpdate, getCountData, getAccountInfo, deleteBoard, quillSetting, updateComment, deleteComment };
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

#board * {
  text-decoration: none;
}

#board::-webkit-scrollbar {
  display: none;
}

.title {
  width: 100%;
  height: 5%;
  display: flex;
  justify-content: left;
  align-items: center;
  margin: 0.5% 0;
  border: 1.5px solid #ffc038;
  padding-left: 1%;
  font-size: 2.8rem;
}

.content {
  width: 100%;
  height: 80%;
}

.btn-field {
  width: 100%;
  height: 5%;
  margin: 0.5% 0;
  display: flex;
  justify-content: right;
  align-items: center;
}

.btn-field button, .btn-field > a, .view {
  width: 10%;
  height: 90%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 1.5px solid #ffc038;
  margin-left: 1%;
  background-color: #ffffff;
  font-size: 2.3rem;
  text-decoration: none;
  cursor: pointer;
}

.view {
  cursor: default;
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

.comment {
  width: 100%;
  height: 40%;
  margin-top: 0.5%;
}

.comment img {
  width: 85%;
  height: 90%;
  margin-left: 2.5%;
  margin-top: 3.5%;
}

.comment-list {
  width: 100%;
  height: 70%;
  border: 1.5px solid #ffc038;
  overflow: auto;
}

.comment-list::-webkit-scrollbar {
  display: none;
}

.comment-btn-field {
  width: 100%;
  height: 30%;
  display: grid;
  grid-template-columns: 90% 10%;
  justify-items: center;
  align-items: flex-end;
  position: relative;
}

.comment-write-btn-field {
  width: 100%;
  height: 50%;
  margin-bottom: 15%;
}

.comment-btn-field textarea {
  width: 100%;
  height: 95%;
  border: 1.5px solid #ffc038;
  outline: 0;
  resize: none;
}

.comment-btn-field textarea::-webkit-scrollbar {
  display: none;
}

.comment-btn-field button {
  width: 95%;
  height: 70%;
  margin-left: 5%;
  border: 1.5px solid #ffc038;
  outline: 0;
  cursor: pointer;
  background-color: #ffffff;
  margin-bottom: 2%;
}

.comment-content {
  width: 100%;
  height: 50%;
  cursor: pointer;
  border-bottom: 1.5px solid #ffc038;
}

.comment-content:last-child {
  border-bottom: none;
}

.comment-main, .comment-sub {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: 10% 30% 50% 10%;
}

.comment-sub {
  padding-left: 10%;
  grid-template-columns: 11% 28% 50% 11%;
}

.comment-sub span {
  color: #cccccc;
}

.comment-content:last-child {
  border-bottom: none;
}

.comment-content:first-child {
  border-bottom: 1.5px solid #ffc038;
}

.comment-content p {
  width: 100%;
  height: 100%;
  font-size: 2.3rem;
}

.comment-info {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: repeat(2, 1fr);
  align-items: center;
}

.comment-info section {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: 10% 90%;
  align-items: center;
}

.comment-info section p {
  display: flex;
  justify-content: left;
  align-items: center;
}

.comment-main .comment-info section img {
  width: 80%;
  height: 65%;
}

.comment-sub .comment-info section img {
  width: 90%;
  height: 65%;
}

.comment-btn-area {
  width: 100%;
  height: 100%;
}

.comment-btn-area button {
  width: 95%;
  height: 30%;
  margin: 1% 0;
  border: 1.5px solid #ffc038;
  background-color: #ffffff;
  cursor: pointer;
}

.comment-btn-area a {
  width: 100%;
  height: 100%;
  cursor: pointer;
}

button:disabled {
  opacity: 0.5;
  pointer-events: none;
}

.comment-data {
  margin-right: 2.5%;
  overflow: auto;
}

.comment-update {
  margin-right: 2.5%;
  resize: none;
  border: none;
  outline: 0;
  background-color: #eeeeee;
}

.comment-data::-webkit-scrollbar {
  display: none;
}

.comment-length {
  position: absolute;
  left: 0;
  bottom: -25%;
  font-size: 2.3rem;
}

.excess {
  color: #f11212;
}

.comment-popup {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  display: flex;
  justify-content: center;
  align-items: center;
}

.comment-update {
  width: 30%;
  height: 20%;
  background-color: #ffffff;
  display: grid;
  grid-template-rows: 70% 30%;
  border: 1.5px solid #ffc038;
  z-index: 20;
}

.comment-popup textarea {
  width: 100%;
  height: 100%;
  border: none;
  resize: none;
  border-bottom: 1.5px solid #ffc038;
  font-size: 2.3rem;
  outline: 0;
}

.comment-popup textarea::placeholder {
  color: #000000;
}

.popup-btn-field {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-around;
  align-items: center;
}

.popup-btn-field button {
  width: 25%;
  height: 80%;
  border: 1.5px solid #ffc038;
  border-radius: 5px;
  background-color: #ffffff;
}

.popup-btn-field button:disabled {
  opacity: 0.5;
  pointer-events: none;
}

.background {
  width: 100%;
  height: 100%;
  background-color: #ffffff;
  opacity: 0.7;
  position: absolute;
  z-index: 10;
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
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
      <div class="content">
        <div id="editor">
        </div>
        <input type="file" id="getFile" @change="upload($event)" hidden>
        <img src="color-text.png" alt="">
      </div>
      <button class="writing" @click="upload()">글 작성</button>
      <!-- <button @click="check()">확인</button> -->
      <router-link to="/board">취소</router-link>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/board" class="board-page">게시판 목록으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, getCurrentInstance, onMounted } from 'vue';
import { writing } from '../../../js/community/board/board.js';
import { Quill } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import { ImageDrop } from 'quill-image-drop-module';
import BlotFormatter from 'quill-blot-formatter';

Quill.register('modules/imageDrop', ImageDrop);
Quill.register('modules/blotFormatter', BlotFormatter);

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const writeInfo = ref({
      title: '',
      content: '',
      type: 'all',
      load: false,
    });

    let quill;
    const toolbarOptions = [
      ['bold', 'italic', 'underline', 'strike', 'blockquote', 'code-block' , 'link'],
      [
        {list: 'ordered'},
        {list: 'bullet'},
      ],
      [
        {script: 'sub'},
        {script: 'super'},
      ],
      [
        {indent: '-1'},
        {indent: '+1'},
        {direction: 'rtl'}
      ],
      [
        {size: ['small', false, 'large', 'huge']},
        {color: []},
        {background: []},
        {font: []},
        {align: []},
      ],
      ['image', 'video'],
      ['clean'],
    ];

    const upload = async () => {
      // writeInfo.value.content = quill.root.innerHTML;
      // const files = event.currentTarget.files;

      // let { data } = await axios;
    };

    onMounted(() => {
      quill = new Quill('#editor', {
        modules: {
          imageDrop: true,
          blotFormatter: {},
          toolbar: {
            container: toolbarOptions,
            handlers: {
              image: () => {
                document.getElementById('getFile').click();
              }
            }
          }
        },
        theme: 'snow'
      });
    });

    return { writeInfo, proxy, quill, writing, upload };
  },
  components: {
  }
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
  border: 1px solid #ffc038;
  padding-left: 1%;
  outline: 0;
}

.title select {
  width: 10%;
  height: 95%;
  border: 1px solid #ffc038;
  border-left: none;
  outline: 0;
}

.option {
  width: 100%;
  height: 5%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 1% 0;
}

.option img {
  width: 2.5%;
  height: 95%;
  cursor: pointer;
}

.content {
  width: 100%;
  height: 80%;
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
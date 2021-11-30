<template>
  <main>
    <div id="board-updating">
      <div class="title">
        <input v-model="updateInfo.title" type="text" placeholder="제목을 입력하세요">
        <select v-model="updateInfo.type">
          <option value="normal">일반</option>
          <option value="notice">공지</option>
          <option value="info">정보/공략</option>
          <option value="ask">질문</option>
          <option value="creative">창작/번역</option>
          <option value="feedback">문의/피드백/제보</option>
        </select>
      </div>
      <div class="content">
        <div id="update-editor" />
        <p>{{updateInfo.htmlContent.length}}</p>
      </div>
      <div class="btn-field">
        <button class="updating" @click="quillSetting()">글 수정</button>
        <router-link to="/board">취소</router-link>
      </div>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/board" class="board-page">게시판 목록으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, getCurrentInstance, onMounted, onBeforeMount } from 'vue';
import { useRoute } from 'vue-router';
import { updating } from '../../../js/community/board/board.js';
import { Quill } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import BlotFormatter from 'quill-blot-formatter';
import ImageUploader from 'quill-image-uploader';
import { rejectAlert } from '../../../js/util/alert.js';
import { checkReject } from '../../../js/community/user/user.js';

Quill.register('modules/blotFormatter', BlotFormatter);
Quill.register('modules/imageUploader', ImageUploader);

export default {
  setup() {
    const { proxy } = getCurrentInstance();
    const route = useRoute();

    const updateInfo = ref({
      title: '',
      type: 'normal',
      content: '',
      htmlContent: ''
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

    const quillSetting = () => {
      updating(route.params.idx, updateInfo.value.title, quill.root.innerHTML, quill.getText(), updateInfo.value.type, proxy.axios);
    };

    onBeforeMount(async () => {
      checkReject(proxy.axios);

      try {
        let { data } = await proxy.axios.get(`/get_board_data/${route.params.idx}`, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        updateInfo.value.title = data.title;
        updateInfo.value.type = data.type;
        updateInfo.value.content = data.content;
      } catch (error) {
        rejectAlert();
      }
    });

    onMounted(() => {
      quill = new Quill('#update-editor', {
        modules: {
          blotFormatter: {},
          imageUploader: {
            upload: file => {
              return new Promise((resolve, reject) => {
                const formData = new FormData();
                formData.append('image', file);

                proxy.axios.post('https://api.imgbb.com/1/upload?key=2c1f21d106572ee9bf1999c3b56a21a7', formData).then(response => JSON.parse(JSON.stringify(response))).then(result => {
                  resolve(result.data.data.url);
                }).catch(error => {
                  reject('upload failed');
                  console.error('Error: ', error);
                });
              });
            }
          },
          toolbar: {
            container: toolbarOptions,
          },
        },
        placeholder: '내용을 입력하세요',
        theme: 'snow'
      });

      quill.root.innerHTML = '';
      
      const interval = setInterval(() => {
        if(updateInfo.value.content.length > 0) {
          quill.root.innerHTML = updateInfo.value.content;
          clearInterval(interval);
        }
      }, 100);

      setInterval(() => {
        updateInfo.value.htmlContent = quill.root.innerHTML;
      }, 1000);
    });

    return { updateInfo, proxy, quill, quillSetting };
  }
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
}

#board-updating {
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
  border: 1px solid #cccccc;
  padding-left: 1%;
  outline: 0;
  font-size: 3rem;
}

.title select {
  width: 10%;
  height: 95%;
  border: 1px solid #cccccc;
  border-left: none;
  outline: 0;
  font-size: 2rem;
  text-align: center;
}

.content {
  width: 100%;
  height: 75%;
}

.btn-field {
  width: 100%;
  height: 5%;
  margin-top: 5%;
  display: flex;
  justify-content: right;
  align-items: center;
}

.btn-field * {
  width: 10%;
  height: 90%;
  margin-left: 1%;
  border: 2px solid #ffc038;
  background-color: #ffffff;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 2.3rem;
  text-decoration: none;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
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
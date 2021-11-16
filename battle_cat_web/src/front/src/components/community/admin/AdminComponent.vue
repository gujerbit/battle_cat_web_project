<template>
  <main>
    <div id="admin">
      {{userInfo.data}}
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/community" class="community-page">커뮤니티 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const userInfo = ref({
      data: [],
    });

    onBeforeMount(async () => {
      if(window.sessionStorage.getItem('jwt-auth-token') === null) {
        alert('로그인 후 이용가능한 시스템입니다');
        location.href = '/login';
      } else {
        const info = JSON.parse(window.sessionStorage.getItem('user-info'));

        if(info.grade === 'admin' || info.grade === 'developer') {
          let { data } = await proxy.axios.get('/user_data');
          userInfo.value.data = data;
        } else {
          alert('관리자만 접근 가능합니다!');
          location.href = '/community'
        }
      }
    });

    return { userInfo };
  }
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
}

#admin {
  width: 90%;
  height: 100%;
  margin: 0 auto;
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
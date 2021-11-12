<template>
  <main>
    <div class="wrap">
      this system 1.2 version available
      <button @click="logout()">logout</button>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { onBeforeMount } from 'vue';

export default {
  setup() {
    const logout = () => {
      alert('로그아웃 완료');
      window.sessionStorage.removeItem('jwt-auth-token');
      window.sessionStorage.removeItem('user-info');
      location.href = '/login';
    }

    onBeforeMount(() => {
      if(window.sessionStorage.getItem('jwt-auth-token') === null) {
        alert('로그인 후 이용가능한 시스템입니다');
        location.href = '/login';
      }
    });

    return { logout };
  }
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
}

.wrap {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 5rem;
}

.main-page {
  width: 4.5%;
  height: 100%;
  border: 2px solid #ffc038;
  border-left: none;
  border-radius: 0 15px 15px 0;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  position: absolute;
  top: 0;
  left: 0;
  writing-mode: vertical-lr;
  display: flex;
  justify-content: center;
  align-items: center;
  opacity: 0;
  transition: all 1.5s;
  font-size: 3rem;
}
  
.main-page:hover {
  opacity: 1;
}
</style>
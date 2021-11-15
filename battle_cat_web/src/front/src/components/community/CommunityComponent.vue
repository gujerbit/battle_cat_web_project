<template>
  <main>
    <router-link :to="elements.path[index]" v-for="(value, index) in elements.content" :key="value">
      <p>{{elements.title[index]}}</p>
      <img :src="require(`../../assets/res/main/${value}.png`)" alt="">
    </router-link>
    <!-- <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link> -->
  </main>
</template>

<script>
import { onBeforeMount } from 'vue';

export default {
  setup() {
    const elements = {
      content: ['board', 'admin', 'user'],
      title: ['게시판', '관리자 페이지', '유저 정보'],
      path: ['/board', '/admin'],
    };

    onBeforeMount(() => {
      if(window.sessionStorage.getItem('jwt-auth-token') === null) {
        alert('로그인 후 이용가능한 시스템입니다');
        location.href = '/login';
      } else {
        const temp = JSON.parse(window.sessionStorage.getItem('user-info'));

        if(temp.forever_reject) {
          alert('해당 계정은 영구차단 되었습니다!');
          window.sessionStorage.removeItem('jwt-auth-token');
          window.sessionStorage.removeItem('user-info');
          location.href = '/login';
        } else if(new Date(temp.reject_end_date).getTime() - new Date().getTime() > 0) {
          alert(`해당 계정은 차단된 계정입니다! 남은 차단 일수: ${new Date(new Date(temp.reject_end_date).getTime() - new Date().getTime()).getDate() - 1}일`);
          window.sessionStorage.removeItem('jwt-auth-token');
          window.sessionStorage.removeItem('user-info');
          location.href = '/login';
        } else elements.path.push(`/userInfo/${temp.name}`);
      }
    });

    return { elements };
  }
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
}
  
main > a {
  width: 100%;
  height: 130%;
  transform: translateY(-10%);
  transition: all 1s;
  position: relative;
}
  
main > a > img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  opacity: 0.3;
  filter: grayscale(0.5);
  transition: all 1s;
}
  
main > a > p {
  width: 100%;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  font-size: 4.5rem;
  text-align: center;
  color: #ffc038;
  text-shadow: -2px 0 #000000, 0 2px #000000, 2px 0 #000000, 0 -2px #000000;
  z-index: 10;
  pointer-events: none;
}

main > a:nth-child(2n - 1):hover {
  transform: translateY(-20%);
}

main > a:nth-child(2n):hover {
  transform: translateY(0);
}
  
main > a:hover {
  transition: all 3s;
}
  
main > a > img:hover {
  transition: all 3s;
  opacity: 0.9;
  filter: grayscale(0.1);
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
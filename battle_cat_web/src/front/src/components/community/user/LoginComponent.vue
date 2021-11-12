<template>
  <main>
    <div id="login">
      <p class="title">LOGIN PAGE</p>
      <div class="input-field">
        <input @keyup.enter="login()" v-model="loginInfo.email" type="text" class="id" placeholder="이메일을 입력해주세요" onfocus="this.select()">
        <input @keyup.enter="login()" v-model="loginInfo.password" type="password" class="password" placeholder="비밀번호를 입력해주세요" onfocus="this.select()">
        <button @click="login()">로그인</button>
      </div>
      <div class="advice">
        <router-link to="/register">혹시 계정이 없으신가요?</router-link>
        <router-link to="/find_account">이메일/비밀번호를 잊어버리셨나요?</router-link>
      </div>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, getCurrentInstance, onBeforeMount } from 'vue';

export default {
  setup() {
    const { proxy } = getCurrentInstance();
    const loginInfo = ref({
      email: '',
      password: '',
    });

    const login = async () => {
      if(loginInfo.value.email.length > 0 && loginInfo.value.password.length > 0) {
        let { data, headers } = await proxy.axios.post('/login_process', {
          email: loginInfo.value.email,
          password: loginInfo.value.password,
        });

        if(data.status) {
          window.sessionStorage.setItem('jwt-auth-token', headers['jwt-auth-token']);
          window.sessionStorage.setItem('user-info', JSON.stringify(data.data));

          alert('로그인 성공!');
          location.href = '/community';
        } else {
          alert('로그인 실패. ID와 비밀번호를 다시 한 번 확인해주세요.');
        }
      } else {
        alert('ID와 비밀번호를 모두 입력해주세요');
      }
    };

    onBeforeMount(() => {
      if(window.sessionStorage.getItem('jwt-auth-token') !== null) {
        alert('로그인 상태에서는 이용하실 수 없습니다');
        location.href = '/';
      }
    });

    return { loginInfo, login };
  }
}
</script>

<style scoped src="../../../css/community/user/loginComponent.css" />
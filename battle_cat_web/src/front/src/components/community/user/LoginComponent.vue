<template>
  <main>
    <div id="login">
      <p class="title">LOGIN PAGE</p>
      <div class="input-field">
        <input @keyup.enter="login(loginInfo, proxy.axios)" v-model="loginInfo.email" type="text" class="id" placeholder="이메일을 입력해주세요" onfocus="this.select()">
        <input @keyup.enter="login(loginInfo, proxy.axios)" v-model="loginInfo.password" type="password" class="password" placeholder="비밀번호를 입력해주세요" onfocus="this.select()">
        <button @click="login(loginInfo, proxy.axios)">로그인</button>
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
import { login } from '../../../js/community/user/user.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();
    
    const loginInfo = ref({
      email: '',
      password: '',
    });

    onBeforeMount(() => {
      if(window.sessionStorage.getItem('jwt-auth-token') !== null) {
        alert('로그인 상태에서는 이용하실 수 없습니다');
        location.href = '/';
      }
    });

    return { loginInfo, proxy, login };
  }
}
</script>

<style scoped src="../../../css/community/user/loginComponent.css" />
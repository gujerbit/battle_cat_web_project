<template>
  <main>
    <div id="register-code" v-if="!registerInfo.registerCodeCheck">
      <p class="title">REGISTER PAGE</p>
      <div class="email">
        <p class="tip">{{tip.email}}</p>
        <input v-if="registerInfo.registerCode.length <= 0" @input="checkEmail(registerInfo, tip)" v-model="registerInfo.email" type="text" placeholder="이메일을 입력하세요" onfocus="this.select()">
        <input v-else v-model="registerInfo.inputCode" type="text" placeholder="이메일로 발송된 회원가입 코드를 입력해주세요" onfocus="this.select()">
      </div>
      <div class="btn-field">
        <button v-if="registerInfo.registerCode.length <= 0" @click="getRegisterCode(registerInfo, proxy.axios)" :disabled="tip.email.length > 0 || registerInfo.email.length <= 0">확인</button>
        <button v-else @click="checkRegisterCode(registerInfo)" :disabled="registerInfo.inputCode.length <= 0">확인</button>
        <button>
          <router-link @click="removeSessionStorage(['registerInfo', 'registerCodeCheck'])" to="/login">취소</router-link>
        </button>
      </div>
    </div>
    <div id="register" v-else-if="registerInfo.registerCodeCheck && registerInfo.registerCode.length > 0">
      <p class="title">REGISTER PAGE</p>
      <div class="content">
        <input v-model="registerInfo.email" type="text" readonly>
        <p class="tip">{{tip.password}}</p>
        <input @input="checkPassword(registerInfo, tip)" v-model="registerInfo.password" type="password" placeholder="비밀번호를 입력해주세요" onfocus="this.select()">
        <p class="tip">{{tip.checkPassword}}</p>
        <input @input="checkCheckPassword(registerInfo, tip)" v-model="registerInfo.checkPassword" type="password" placeholder="비밀번호를 다시 한 번 입력해주세요" onfocus="this.select()">
        <p class="tip">{{tip.name}}</p>
        <input @input="checkName(registerInfo, tip)" v-model="registerInfo.name" type="text" placeholder="닉네임을 입력해주세요" onfocus="this.select()">
        <p class="tip">{{tip.code}}</p>
        <input @input="checkCode(registerInfo, tip)" v-model="registerInfo.code" type="text" placeholder="문의코드를 입력해주세요" onfocus="this.select()">
      </div>
      <div class="register-field">
        <button @click="register(registerInfo, proxy.axios, router)" :disabled="registerInfo.password.length <= 0 || registerInfo.checkPassword.length <= 0 || registerInfo.name.length <= 0 || registerInfo.code.length <= 0 || tip.password.length + tip.checkPassword.length + tip.name.length + tip.code.length > 0">회원가입</button>
        <button>
          <router-link @click="removeSessionStorage(['registerInfo', 'registerCodeCheck'])" to="/login">취소</router-link>
        </button>
      </div>
    </div>
    <router-link @click="removeSessionStorage(['registerInfo', 'registerCodeCheck'])" to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link @click="removeSessionStorage(['registerInfo', 'registerCodeCheck'])" to="/login" class="login-page">로그인 화면으로 돌아가기</router-link>
    <div class="loading">
      <div class="loading-content">
        <h1>LOADING</h1>
        <p>회원가입 코드를 전송중입니다. 잠시만 기다려주세요</p>
      </div>
    </div>
  </main>
</template>

<script>
import { ref, getCurrentInstance, onBeforeMount } from 'vue';
import { useRouter } from 'vue-router';
import { checkPassword, checkCheckPassword, checkName, checkCode, checkEmail } from '../../../js/util/validation.js';
import { getRegisterCode, checkRegisterCode, register } from '../../../js/community/user/user.js';
import { removeSessionStorage } from '../../../js/util/value.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();
    const router = useRouter();

    const registerInfo = ref({
      email: '',
      password: '',
      checkPassword: '',
      name: '',
      code: '',
      registerCode: '',
      inputCode: '',
      expireTime: '',
      registerCodeCheck: false
    });

    const tip = ref({
      email: '',
      password: '',
      checkPassword: '',
      name: '',
      code: '',
    });

    onBeforeMount(() => {
      if(window.sessionStorage.getItem('jwt-auth-token') !== null) {
        alert('로그인 상태에서는 이용하실 수 없습니다');
        location.href = '/';
      }

      if(window.sessionStorage.getItem('registerInfo') != null) {
        registerInfo.value.email = JSON.parse(window.sessionStorage.getItem('registerInfo')).email;
        registerInfo.value.registerCode = JSON.parse(window.sessionStorage.getItem('registerInfo')).registerCode;
        registerInfo.value.expireTime = JSON.parse(window.sessionStorage.getItem('registerInfo')).expireTime;
      }

      if(window.sessionStorage.getItem('registerCodeCheck') != null) registerInfo.value.registerCodeCheck = window.sessionStorage.getItem('registerCodeCheck');
    });

    return { registerInfo, tip, proxy, router, getRegisterCode, checkRegisterCode, register, removeSessionStorage, checkPassword, checkCheckPassword, checkName, checkCode, checkEmail };
  }
}
</script>

<style scoped src="../../../css/community/user/registerComponent.css" />
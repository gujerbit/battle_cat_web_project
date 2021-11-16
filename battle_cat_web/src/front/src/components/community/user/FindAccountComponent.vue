<template>
  <main>
    <div id="find" v-if="!show.email && !show.password">
      <div id="find-email" @click="show.email = true">
        <p>이메일 찾기</p>
      </div>
      <div id="find-password" @click="show.password = true">
        <p>비밀번호 찾기</p>
      </div>
    </div>
    <div class="email" v-if="show.email">
      <div v-if="!show.emailResult" class="content">
        <p class="title">이메일 찾기</p>
        <p class="tip">{{tip.code}}</p>
        <input @input="checkCode(find, tip)" v-model="find.code" type="text" placeholder="문의코드를 입력해주세요" onfocus="this.select()">
        <div class="btn-field">
          <button @click="findEmail(find, show, result, proxy.axios)" :disabled="tip.code.length > 0 || find.code.length <= 0">이메일 찾기</button>
          <button @click="show.email = false; clear()">취소</button>
        </div>
      </div>
      <div class="result" v-else>
        <div class="content">
          <p class="title">이메일을 찾았습니다</p>
          <p class="find">{{result.email}}</p>
          <div class="result-field">
            <router-link to="/login" @click="clear()">로그인하러 가기</router-link>
            <button @click="show.password = true; clear()">비밀번호 찾기</button>
          </div>
        </div>
      </div>
    </div>
    <div class="password" v-if="show.password && result.passwordChangeCode.length <= 0 && !show.passwordChangeCodeCheck">
      <div class="content">
        <p class="title">비밀번호 찾기</p>
        <p class="tip">{{tip.email}}</p>
        <input @input="checkEmail(find, tip)" v-model="find.email" type="text" placeholder="이메일을 입력해주세요" onfocus="this.select()">
        <p class="tip">{{tip.code}}</p>
        <input @input="checkCode(find, tip)" v-model="find.code" type="text" placeholder="문의코드를 입력해주세요" onfocus="this.select()">
        <div class="btn-field">
          <button @click="getPasswordChangeCode(find, result, proxy.axios)" :disabled="tip.code.length + tip.email.length > 0 || find.email.length <= 0 || find.code.length <= 0">비밀번호 찾기</button>
          <button @click="show.password = false; passwordChangeInfoClear(); clear()">취소</button>
        </div>
      </div>
    </div>
    <div class="password-change-code" v-if="show.password && result.passwordChangeCode.length > 0 && !show.passwordChangeCodeCheck">
      <div class="content">
        <p class="title">비밀번호 변경</p>
        <input v-model="find.passwordChangeCode" type="text" placeholder="비밀번호 변경 코드를 입력하세요">
        <div class="btn-field">
          <button @click="checkPasswordChangeCode(find, result, show)" :disabled="find.passwordChangeCode.length <= 0">입력</button>
          <button @click="show.password = false; passwordChangeInfoClear(); clear()">취소</button>
        </div>
      </div>
    </div>
    <div class="password-change" v-if="show.password && show.passwordChangeCodeCheck">
      <div class="content">
        <p class="title">비밀번호 변경</p>
        <p class="tip">{{tip.password}}</p>
        <input @input="checkPassword(find, tip)" v-model="find.password" type="password" placeholder="변경할 비밀번호를 입력해주세요">
        <p class="tip">{{tip.checkPassword}}</p>
        <input @input="checkCheckPassword(find, tip)" v-model="find.checkPassword" type="password" placeholder="변경할 비밀번호를 다시 한 번 입력해주세요">
        <div class="btn-field">
          <button @click="changePassword(find, result, proxy.axios)" :disabled="tip.password.length + tip.checkPassword.length > 0 || find.password.length <= 0 || find.checkPassword.length <= 0">비밀번호 변경</button>
          <button @click="show.password = false; passwordChangeInfoClear(); clear()">취소</button>
        </div>
      </div>
    </div>
    <router-link @click="passwordChangeInfoClear()" to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link @click="passwordChangeInfoClear()" to="/login" class="login-page">로그인 화면으로 돌아가기</router-link>
    <div class="loading">
      <div class="loading-content">
        <h1>LOADING</h1>
        <p>비밀번호 변경 코드를 전송중입니다. 잠시만 기다려주세요</p>
      </div>
    </div>
  </main>
</template>

<script>
import { ref, getCurrentInstance, onBeforeMount } from 'vue';
import { checkCode, checkEmail, checkPassword, checkCheckPassword } from '../../../js/util/validation.js';
import { findEmail, getPasswordChangeCode, checkPasswordChangeCode, changePassword } from '../../../js/community/user/findAccount.js';
import { clearValue, removeSessionStorage } from '../../../js/util/value.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const find = ref({
      email: '',
      code: '',
      passwordChangeCode: '',
      password: '',
      checkPassword: '',
    });

    const show = ref({
      email: false,
      password: false,
      emailResult: false,
      passwordChangeCodeCheck: false,
    });

    const tip = ref({
      email: '',
      code: '',
      password: '',
      checkPassword: '',
    });

    const result = ref({
      email: '',
      passwordChangeCode: '',
      expireTime: '',
    });

    const clear = () => {
      clearValue(find.value);
      clearValue(result.value);
      clearValue(show.value);
      clearValue(tip.value);
    };

    const passwordChangeInfoClear = () => {
      removeSessionStorage('passwordChangeInfo');
      clearValue(result.value);
    }

    onBeforeMount(() => {
      if(window.sessionStorage.getItem('jwt-auth-token') !== null) {
        alert('로그인 상태에서는 이용하실 수 없습니다');
        location.href = '/';
      }
    });

    return { find, show, tip, result, proxy, findEmail, getPasswordChangeCode, clear, removeSessionStorage, passwordChangeInfoClear, checkPasswordChangeCode, changePassword, checkPassword, checkCheckPassword, checkCode, checkEmail };
  }
}
</script>

<style scoped src="../../../css/community/user/findAccountComponent.css" />
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
        <p class="tip">{{tip.codeEmail}}</p>
        <input @input="checkValue()" v-model="find.codeEmail" type="text" placeholder="문의코드를 입력해주세요" onfocus="this.select()">
        <div class="btn-field">
          <button @click="findEmail()" :disabled="tip.codeEmail.length > 0 || find.codeEmail.length <= 0">이메일 찾기</button>
          <button @click="show.email = false; clear()">취소</button>
        </div>
      </div>
      <div class="result" v-else>
        <div class="content">
          <p class="title">이메일을 찾았습니다</p>
          <p class="find">{{result.email}}</p>
          <div class="result-field">
            <router-link to="/login">로그인하러 가기</router-link>
            <button @click="clear(); show.password = true;">비밀번호 찾기</button>
          </div>
        </div>
      </div>
    </div>
    <div class="password" v-if="show.password && result.passwordChangeCode.length <= 0 && !show.passwordChangeCodeCheck">
      <div class="content">
        <p class="title">비밀번호 찾기</p>
        <p class="tip">{{tip.email}}</p>
        <input @input="emailCheck()" v-model="find.email" type="text" placeholder="이메일을 입력해주세요" onfocus="this.select()">
        <p class="tip">{{tip.codePassword}}</p>
        <input @input="checkValue()" v-model="find.codePassword" type="text" placeholder="문의코드를 입력해주세요" onfocus="this.select()">
        <div class="btn-field">
          <button @click="getPasswordChangeCode()" :disabled="tip.codePassword.length + tip.email.length > 0 || find.email.length <= 0 || find.codePassword.length <= 0">비밀번호 찾기</button>
          <button @click="show.password = false; passwordChangeInfoClear(); clear()">취소</button>
        </div>
      </div>
    </div>
    <div class="password-change-code" v-if="show.password && result.passwordChangeCode.length > 0 && !show.passwordChangeCodeCheck">
      <div class="content">
        <p class="title">비밀번호 변경</p>
        <input v-model="find.passwordChangeCode" type="text" placeholder="비밀번호 변경 코드를 입력하세요">
        <div class="btn-field">
          <button @click="checkPasswordChangeCode()" :disabled="find.passwordChangeCode.length <= 0">입력</button>
          <button @click="show.password = false; passwordChangeInfoClear(); clear()">취소</button>
        </div>
      </div>
    </div>
    <div class="password-change" v-if="show.password && show.passwordChangeCodeCheck">
      <div class="content">
        <p class="title">비밀번호 변경</p>
        <p class="tip">{{tip.password}}</p>
        <input @input="checkValue()" v-model="find.password" type="password" placeholder="변경할 비밀번호를 입력해주세요">
        <p class="tip">{{tip.checkPassword}}</p>
        <input @input="checkValue()" v-model="find.checkPassword" type="password" placeholder="변경할 비밀번호를 다시 한 번 입력해주세요">
        <div class="btn-field">
          <button @click="changePassword()" :disabled="tip.password.length + tip.checkPassword.length > 0 || find.password.length <= 0 || find.checkPassword.length <= 0">비밀번호 변경</button>
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

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const find = ref({
      email: '',
      codeEmail: '',
      codePassword: '',
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
      codeEmail: '',
      codePassword: '',
      password: '',
      checkPassword: '',
    });

    const result = ref({
      email: '',
      passwordChangeCode: '',
      expireTime: '',
    })

    const findEmail = async () => {
      let { data } = await proxy.axios.post('/find_email', {
        code: find.value.codeEmail
      });

      if(data.length > 0) {
        show.value.emailResult = true;
        result.value.email = data;
      } else alert('이메일 찾기 실패. 문의코드를 다시 한 번 확인해주세요');
    };

    const getPasswordChangeCode = async () => {
      document.querySelector('.loading').style.display = 'flex';

      let { data } = await proxy.axios.post('/get_password_change_code', {
        email: find.value.email,
        code: find.value.codePassword
      });

      if(!data) {
        alert('코드 발급 실패. 이메일과 문의코드를 다시 한 번 확인해주세요');
        document.querySelector('.loading').style.display = 'none';
      }
      else {
        alert('해당 이메일로 회원가입 코드를 전송했습니다!');
        document.querySelector('.loading').style.display = 'none';

        let temp = {
          email: find.value.email,
          passwordChangeCode: data,
          expireTime: new Date().setMinutes(new Date().getMinutes() + 10),
        };

        window.sessionStorage.setItem('passwordChangeInfo', JSON.stringify(temp));
        result.value.email = find.value.email;
        result.value.passwordChangeCode = data;
        result.value.expireTime = temp.expireTime;
      }
    };

    const checkPasswordChangeCode = () => {
      if(result.value.passwordChangeCode === find.value.passwordChangeCode) {
        let today = new Date();

        if(result.value.expireTime - today.getTime() <= 0) {
          alert('만료된 회원가입 코드입니다');
          window.sessionStorage.removeItem('passwordChangeInfo');
          location.href = '/find_account';
        } else {
          alert('회원가입 코드 확인 완료');
          show.value.passwordChangeCodeCheck = true;
        }
      } else alert('올바르지 않은 비밀번호 변경 코드입니다');
    };

    const changePassword = async () => {
      let { data } = await proxy.axios.post('/change_password', {
        email: result.value.email,
        password: find.value.password,
      });

      if(data > 0) {
        alert('성공적으로 비밀번호를 변경하였습니다');
        location.href = '/login';
      } else alert('비밀번호를 변경 중 문제가 발생했습니다');
    }

    const emailCheck = () => {
      const emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;

      if(!emailPattern.test(find.value.email) && find.value.email.length > 0) tip.value.email = '올바른 이메일을 입력해주세요';
      else tip.value.email = '';
    };

    const checkValue = () => {
      const specialPattern = /[!@#$%^&*()_+={}:;<>?/|.,`~'"[\]\\-]/gi;
      const includePattern = /[!@#$%^&*?.]/gi;
      const decludePattern = /[()_+={}:;<>/|,`~'"[\]\\-]/gi;
      const numberPattern = /[0-9]/;

      if(find.value.password.length !== 0) {
        if(find.value.password.length < 8 || find.value.password.length > 20) tip.value.password = '비밀번호는 8자리 이상, 20자리 이하여야 합니다.';
        else if(find.value.password.search(/\s/) != -1) tip.value.password = '공백은 포함하실 수 없습니다.';
        else if(decludePattern.test(find.value.password)) tip.value.password = '해당 특수문자는 포함하실 수 없습니다.';
        else if(!includePattern.test(find.value.password)) tip.value.password = '!,@,#,$,%,^,&,*,?,.중 하나가 포함되어야 합니다.';
        else if(numberPattern.test(find.value.password.split('')[0])) tip.value.password = '처음은 문자로 시작하셔야 합니다.';
        else tip.value.password = '';
      } else tip.value.password = '';

      if(find.value.checkPassword.length !== 0) {
        if(find.value.checkPassword !== find.value.password) tip.value.checkPassword = '비밀번호가 맞지 않습니다.';
        else tip.value.checkPassword = '';
      } else tip.value.checkPassword = '';

      if(find.value.codeEmail.length !== 0) {
        if(find.value.codeEmail.length !== 9) tip.value.codeEmail = '문의코드는 9자리입니다.';
        else if(find.value.codeEmail.search(/\s/) != -1) tip.value.codeEmail = '공백은 포함하실 수 없습니다.';
        else if(specialPattern.test(find.value.codeEmail)) tip.value.codeEmail = '해당 특수문자는 포함하실 수 없습니다.';
        else tip.value.codeEmail = '';
      } else tip.value.codeEmail = '';

      if(find.value.codePassword.length !== 0) {
        if(find.value.codePassword.length !== 9) tip.value.codePassword = '문의코드는 9자리입니다.';
        else if(find.value.codePassword.search(/\s/) != -1) tip.value.codePassword = '공백은 포함하실 수 없습니다.';
        else if(specialPattern.test(find.value.codePassword)) tip.value.codePassword = '해당 특수문자는 포함하실 수 없습니다.';
        else tip.value.codePassword = '';
      } else tip.value.codePassword = '';
    };

    const clear = () => {
      find.value.email = '';
      find.value.codeEmail = '';
      find.value.codePassword = '';
      find.value.findCode = '';
      find.value.password = '';
      find.value.checkPassword = '';
      show.value.email = false;
      show.value.password = false;
      show.value.emailResult = false;
      show.value.passwordChangeCodeCheck = false;
      result.value.email = '';
      tip.value.email = '';
      tip.value.codeEmail = '';
      tip.value.codePassword = '';
      tip.value.password = '';
      tip.value.checkPassword = '';
    };

    const passwordChangeInfoClear = () => {
      window.sessionStorage.removeItem('passwordChangeInfo');
      result.value.passwordChangeCode = '';
      result.value.expireTime = '';
    }

    onBeforeMount(() => {
      if(window.sessionStorage.getItem('jwt-auth-token') !== null) {
        alert('로그인 상태에서는 이용하실 수 없습니다');
        location.href = '/';
      }
    });

    return { find, show, tip, result, findEmail, getPasswordChangeCode, emailCheck, checkValue, clear, passwordChangeInfoClear, checkPasswordChangeCode, changePassword };
  }
}
</script>

<style scoped src="../../../css/community/user/findAccountComponent.css" />
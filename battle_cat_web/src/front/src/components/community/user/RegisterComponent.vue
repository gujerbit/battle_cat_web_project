<template>
  <main>
    <div id="register-code" v-if="!registerInfo.registerCodeCheck">
      <p class="title">REGISTER PAGE</p>
      <div class="email">
        <p class="tip">{{tip.email}}</p>
        <input v-if="registerInfo.registerCode.length <= 0" @input="emailCheck()" v-model="registerInfo.email" type="text" placeholder="이메일을 입력하세요" onfocus="this.select()">
        <input v-else v-model="registerInfo.inputCode" type="text" placeholder="이메일로 발송된 회원가입 코드를 입력해주세요" onfocus="this.select()">
      </div>
      <div class="btn-field">
        <button v-if="registerInfo.registerCode.length <= 0" @click="getRegisterCode()" :disabled="tip.email.length > 0 || registerInfo.email.length <= 0">확인</button>
        <button v-else @click="checkRegisterCode()" :disabled="registerInfo.inputCode.length <= 0">확인</button>
        <button>
          <router-link @click="registerInfoClear()" to="/login">취소</router-link>
        </button>
      </div>
    </div>
    <div id="register" v-else-if="registerInfo.registerCodeCheck && registerInfo.registerCode.length > 0">
      <p class="title">REGISTER PAGE</p>
      <div class="content">
        <input v-model="registerInfo.email" type="text" readonly>
        <p class="tip">{{tip.password}}</p>
        <input @input="checkValue()" v-model="registerInfo.password" type="password" placeholder="비밀번호를 입력해주세요" onfocus="this.select()">
        <p class="tip">{{tip.checkPassword}}</p>
        <input @input="checkValue()" v-model="registerInfo.checkPassword" type="password" placeholder="비밀번호를 다시 한 번 입력해주세요" onfocus="this.select()">
        <p class="tip">{{tip.name}}</p>
        <input @input="checkValue()" v-model="registerInfo.name" type="text" placeholder="닉네임을 입력해주세요" onfocus="this.select()">
        <p class="tip">{{tip.code}}</p>
        <input @input="checkValue()" v-model="registerInfo.code" type="text" placeholder="문의코드를 입력해주세요" onfocus="this.select()">
      </div>
      <div class="register-field">
        <button @click="register()" :disabled="registerInfo.password.length <= 0 || registerInfo.checkPassword.length <= 0 || registerInfo.name.length <= 0 || registerInfo.code.length <= 0 || tip.password.length + tip.checkPassword.length + tip.name.length + tip.code.length > 0">회원가입</button>
        <button>
          <router-link @click="registerInfoClear()" to="/login">취소</router-link>
        </button>
      </div>
    </div>
    <router-link @click="registerInfoClear()" to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link @click="registerInfoClear()" to="/login" class="login-page">로그인 화면으로 돌아가기</router-link>
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

    const getRegisterCode = async () => {
      document.querySelector('.loading').style.display = 'flex';

      let { data:idCheck } = await proxy.axios.post('/check_email', {
        email: registerInfo.value.email
      });

      if(idCheck) {
        let { data:registerCode } = await proxy.axios.post('/register_code', {
          email: registerInfo.value.email
        });

        alert('해당 이메일로 회원가입 코드를 전송했습니다!');
        document.querySelector('.loading').style.display = 'none';

        let registerTemp = {
          email: registerInfo.value.email,
          registerCode: registerCode,
          expireTime: new Date().setMinutes(new Date().getMinutes() + 10),
        };

        window.sessionStorage.setItem('registerInfo', JSON.stringify(registerTemp));
        registerInfo.value.registerCode = registerCode;
        registerInfo.value.expireTime = registerTemp.expireTime;
      } else {
        alert('해당 이메일은 이미 사용중입니다!');
        document.querySelector('.loading').style.display = 'none';
      }
    };

    const checkRegisterCode = () => {
      if(registerInfo.value.inputCode === registerInfo.value.registerCode) {
        let today = new Date();

        if(registerInfo.value.expireTime - today.getTime() <= 0) {
          alert('만료된 회원가입 코드입니다');
          window.sessionStorage.removeItem('registerInfo');
          location.href = '/register';
        } else {
          alert('회원가입 코드 확인 완료');
          registerInfo.value.registerCodeCheck = true;
          window.sessionStorage.setItem('registerCodeCheck', true);
        }
      } else alert('올바르지 않은 회원가입 코드입니다');
    };

    const register = async () => {
      let { data:checkName } = await proxy.axios.post('/check_name', {
        name: registerInfo.value.name
      });

      if(checkName) {
        let { data:checkCode } = await proxy.axios.post('/check_code', {
          code: registerInfo.value.code
        });

        if(checkCode) {
          let { data } = await proxy.axios.post('/register_process', {
            email: registerInfo.value.email,
            password: registerInfo.value.password,
            name: registerInfo.value.name,
            code: registerInfo.value.code
          });

          if(data > 0) {
            alert('회원가입이 정상적으로 처리되었습니다');
            registerInfoClear();
            router.push('/login');
          } else {
            alert('회원가입 중 문제가 발생했습니다');
            registerInfoClear();
            location.href = '/register';
          }
        } else alert('중복된 문의코드입니다. 이미 가입한 계정이 있는지 다시 한 번 확인해주세요');
      } else alert('중복된 닉네임입니다');
    };

    const emailCheck = () => {
      const emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;

      if(!emailPattern.test(registerInfo.value.email) && registerInfo.value.email.length > 0) tip.value.email = '올바른 이메일을 입력해주세요';
      else tip.value.email = '';
    };

    const checkValue = () => {
      const specialPattern = /[!@#$%^&*()_+={}:;<>?/|.,`~'"[\]\\-]/gi;
      const includePattern = /[!@#$%^&*?.]/gi;
      const decludePattern = /[()_+={}:;<>/|,`~'"[\]\\-]/gi;
      const numberPattern = /[0-9]/;

      if(registerInfo.value.password.length !== 0) {
        if(registerInfo.value.password.length < 8 || registerInfo.value.password.length > 20) tip.value.password = '비밀번호는 8자리 이상, 20자리 이하여야 합니다.';
        else if(registerInfo.value.password.search(/\s/) != -1) tip.value.password = '공백은 포함하실 수 없습니다.';
        else if(decludePattern.test(registerInfo.value.password)) tip.value.password = '해당 특수문자는 포함하실 수 없습니다.';
        else if(!includePattern.test(registerInfo.value.password)) tip.value.password = '!,@,#,$,%,^,&,*,?,.중 하나가 포함되어야 합니다.';
        else if(numberPattern.test(registerInfo.value.password.split('')[0])) tip.value.password = '처음은 문자로 시작하셔야 합니다.';
        else tip.value.password = '';
      } else tip.value.password = '';

      if(registerInfo.value.checkPassword.length !== 0) {
        if(registerInfo.value.checkPassword !== registerInfo.value.password) tip.value.checkPassword = '비밀번호가 맞지 않습니다.';
        else tip.value.checkPassword = '';
      } else tip.value.checkPassword = '';

      if(registerInfo.value.name.length !== 0) {
        if(registerInfo.value.name.length > 20) tip.value.name = '닉네임은 20자리 이하여야 합니다.';
        else if(registerInfo.value.name.search(/\s/) != -1) tip.value.name = '공백은 포함하실 수 없습니다.';
        else if(decludePattern.test(registerInfo.value.name)) tip.value.name = '해당 특수문자는 포함하실 수 없습니다.';
        else tip.value.name = '';
      } else tip.value.name = '';

      if(registerInfo.value.code.length !== 0) {
        if(registerInfo.value.code.length !== 9) tip.value.code = '문의코드는 9자리입니다.';
        else if(registerInfo.value.code.search(/\s/) != -1) tip.value.code = '공백은 포함하실 수 없습니다.';
        else if(specialPattern.test(registerInfo.value.code)) tip.value.code = '해당 특수문자는 포함하실 수 없습니다.';
        else tip.value.code = '';
      } else tip.value.code = '';
    };

    const registerInfoClear = () => {
      window.sessionStorage.removeItem('registerInfo');
      window.sessionStorage.removeItem('registerCodeCheck');
    };

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

    return { registerInfo, tip, window, getRegisterCode, emailCheck, checkRegisterCode, checkValue, register, registerInfoClear };
  }
}
</script>

<style scoped src="../../../css/community/user/registerComponent.css" />
<template>
  <main>
    <div id="register">
      <p class="title">REGISTER PAGE</p>
      <div class="input-field">
        <p class="id-tip">{{tip.email}}</p>
        <input @input="emailCheck()" v-model="registerInfo.email" type="text" placeholder="이메일을 입력하세요">
      </div>
      <div class="btn-field">
        <button @click="register()" :disabled="tip.email.length > 0 || registerInfo.email.length <= 0">회원가입</button>
        <button>
          <router-link to="/login">취소</router-link>
        </button>
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

        const registerInfo = ref({
            email: '',
            password: '',
            checkPassword: '',
            name: '',
            code: '',
            registerCode: '',
        });

        const tip = ref({
            email: '',
            password: '',
            checkPassword: '',
            name: '',
            code: '',
        });

        const register = async () => {
            let { data:idCheck } = await proxy.axios.post('/check_id', {
                email: registerInfo.value.email
            });

            if(idCheck) {
              let { data:registerCode } = await proxy.axios.post('/register_code', {
                email: registerInfo.value.email
              });

              alert('해당 이메일로 회원가입 코드를 전송했습니다!');
              registerInfo.value.registerCode = registerCode;
            } else {
              alert('해당 이메일은 이미 사용중입니다!');
            }
        };

        const emailCheck = () => {
          const emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;

          if(!emailPattern.test(registerInfo.value.email)) tip.value.email = '올바른 이메일을 입력해주세요';
          else tip.value.email = '';
        }

        // const checkValue = () => {
        //     const specialPattern = /[!@#$%^&*()_+={}:;<>?/|.,`~'"[\]\\-]/gi;
        //     const includePattern = /[!@#$%^&*?.]/gi;
        //     const decludePattern = /[()_+={}:;<>/|,`~'"[\]\\-]/gi;
        //     const engPattern = /[A-Za-z]/;
        //     const numberPattern = /[0-9]/;

        //     if(registerInfo.value.id.length !== 0) {
        //         if(registerInfo.value.id.length < 8 || registerInfo.value.id.length > 20) tip.value.id = 'ID는 8자리 이상, 20자리 이하여야 합니다.';
        //         else if(registerInfo.value.id.search(/\s/) != -1) tip.value.id = '공백은 포함하실 수 없습니다.';
        //         else if(specialPattern.test(registerInfo.value.id)) tip.value.id = '해당 특수문자는 포함하실 수 없습니다.';
        //         else if(numberPattern.test(registerInfo.value.id.split('')[0])) tip.value.id = '처음은 문자로 시작하셔야 합니다.';
        //         else tip.value.id = '';

        //         registerInfo.value.id.split('').forEach(res => {
        //             if(!engPattern.test(res) && !numberPattern.test(res)) tip.value.id = '해당 문자는 사용하실 수 없습니다';
        //         });
        //     }

        //     if(registerInfo.value.password.length !== 0) {
        //         if(registerInfo.value.password.length < 8 || registerInfo.value.password.length > 20) tip.value.password = '비밀번호는 8자리 이상, 20자리 이하여야 합니다.';
        //         else if(registerInfo.value.password.search(/\s/) != -1) tip.value.password = '공백은 포함하실 수 없습니다.';
        //         else if(decludePattern.test(registerInfo.value.password)) tip.value.password = '해당 특수문자는 포함하실 수 없습니다.';
        //         else if(!includePattern.test(registerInfo.value.password)) tip.value.password = '!,@,#,$,%,^,&,*,?,.중 하나가 포함되어야 합니다.';
        //         else if(numberPattern.test(registerInfo.value.password.split('')[0])) tip.value.password = '처음은 문자로 시작하셔야 합니다.';
        //         else tip.value.password = '';
        //     }

        //     if(registerInfo.value.checkPassword.length !== 0) {
        //         if(registerInfo.value.checkPassword !== registerInfo.value.password) tip.value.checkPassword = '비밀번호가 맞지 않습니다.';
        //         else tip.value.checkPassword = '';
        //     }

        //     if(registerInfo.value.name.length !== 0) {
        //         if(registerInfo.value.name.length > 20) tip.value.name = '닉네임은 20자리 이하여야 합니다.';
        //         else if(registerInfo.value.name.search(/\s/) != -1) tip.value.name = '공백은 포함하실 수 없습니다.';
        //         else if(decludePattern.test(registerInfo.value.name)) tip.value.name = '해당 특수문자는 포함하실 수 없습니다.';
        //         else tip.value.name = '';
        //     }

        //     if(registerInfo.value.code.length !== 0) {
        //         if(registerInfo.value.code.length !== 8) tip.value.code = '냥코 클럽의 회원 번호는 8자리입니다.';
        //         else if(registerInfo.value.code.search(/\s/) != -1) tip.value.code = '공백은 포함하실 수 없습니다.';
        //         else if(specialPattern.test(registerInfo.value.code)) tip.value.code = '해당 특수문자는 포함하실 수 없습니다.';
        //         else tip.value.code = '';

        //         registerInfo.value.code.split('').forEach(res => {
        //             if(!numberPattern.test(res)) tip.value.code = '냥코 클럽의 회원 번호는 오직 숫자로만 이루어져 있습니다.';
        //         });
        //     }
        // }

        onBeforeMount(() => {
            // if(window.sessionStorage.getItem('jwt-auth-token') !== null) location.href = '/';
        })

        return { registerInfo, tip, register, emailCheck };
    }
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

#register {
  width: 35%;
  height: 30%;
  border: 2px solid #ffc038;
  border-radius: 15px;
}

.title {
  width: 100%;
  height: 20%;
  font-size: 4rem;
  color: #ffc038;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: default;
}

.input-field {
  width: 100%;
  height: 40%;
  display: grid;
  grid-template-rows: 1fr 2fr;
  justify-items: center;
  align-items: center;
}

.input-field input {
  width: 75%;
  height: 90%;
  border: 2px solid #ffc038;
  border-radius: 15px;
  text-align: center;
  font-size: 2rem;
  outline: 0;
  transition: all 1s;
  color: #ffc038;
}

.input-field input::placeholder {
  color: #ffc038;
}

.input-field > input:hover, .btn-field > button:hover, .id-check:hover {
  transform: scale(105%);
}

.btn-field {
  width: 100%;
  height: 40%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.btn-field > button {
  width: 25%;
  height: 40%;
  border: 2px solid #ffc038;
  border-radius: 10px;
  outline: 0;
  transition: all 1s;
  background-color: #ffffff;
  cursor: pointer;
  margin: 0 5%;
}

button > a {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

button:disabled {
  opacity: 0.5;
  cursor:no-drop;
  pointer-events: none;
}

.input-field > p {
  color: #f11212;
  font-size: 1.8rem;
  display: flex;
  justify-content: center;
  align-items: center;
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
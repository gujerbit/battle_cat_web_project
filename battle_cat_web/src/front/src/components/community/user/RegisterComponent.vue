<template>
  <main>
      <div id="register">
          <div class="input-field">
              <p class="id-tip">{{tip.id}}</p>
              <input @input="checkValue()" v-model="registerInfo.id" type="text" placeholder="ID를 입력하세요">
              <button @click="checkID()" :disabled="tip.id.length > 0 || registerInfo.id.length <= 0" class="id-check">ID 중복 검사</button>
              <p class="password-tip">{{tip.password}}</p>
              <input @input="checkValue()" v-model="registerInfo.password" type="password" placeholder="비밀번호를 입력하세요">
              <p class="repassword-tip">{{tip.checkPassword}}</p>
              <input @input="checkValue()" v-model="registerInfo.checkPassword" type="password" placeholder="비밀번호를 다시 입력하세요">
              <p class="name-tip">{{tip.name}}</p>
              <input @input="checkValue()" v-model="registerInfo.name" type="text" placeholder="닉네임을 입력하세요">
              <p class="code-tip">{{tip.code}}</p>
              <input @input="checkValue()" v-model="registerInfo.code" type="text" placeholder="냥코 클럽의 회원 번호를 입력하세요">
          </div>
          <div class="btn-field">
              <button @click="register()" :disabled="tip.id.length + tip.password.length + tip.checkPassword.length + tip.name.length + tip.code.length > 0 || registerInfo.id.length <= 0 || registerInfo.password.length <= 0 || registerInfo.checkPassword.length <= 0 || registerInfo.name.length <= 0 || registerInfo.code.length <= 0 || !registerInfo.idCheck">회원가입</button>
              <router-link to="/login">취소</router-link>
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
            id: '',
            password: '',
            checkPassword: '',
            name: '',
            code: '',
            idCheck: false,
        });

        const tip = ref({
            id: '',
            password: '',
            checkPassword: '',
            name: '',
            code: '',
        });

        const register = async () => {
            await proxy.axios.post('/register_process', {
                id: registerInfo.value.id,
                password: registerInfo.value.password,
                name: registerInfo.value.name,
                code: registerInfo.value.code,
            });

            alert('회원 가입 성공!');
            location.href = '/login';
        };

        const checkValue = () => {
            const specialPattern = /[!@#$%^&*()_+={}:;<>?/|.,`~'"[\]\\-]/gi;
            const includePattern = /[!@#$%^&*?.]/gi;
            const decludePattern = /[()_+={}:;<>/|,`~'"[\]\\-]/gi;
            const engPattern = /[A-Za-z]/;
            const numberPattern = /[0-9]/;

            if(registerInfo.value.id.length !== 0) {
                if(registerInfo.value.id.length < 8 || registerInfo.value.id.length > 20) tip.value.id = 'ID는 8자리 이상, 20자리 이하여야 합니다.';
                else if(registerInfo.value.id.search(/\s/) != -1) tip.value.id = '공백은 포함하실 수 없습니다.';
                else if(specialPattern.test(registerInfo.value.id)) tip.value.id = '해당 특수문자는 포함하실 수 없습니다.';
                else if(numberPattern.test(registerInfo.value.id.split('')[0])) tip.value.id = '처음은 문자로 시작하셔야 합니다.';
                else tip.value.id = '';

                registerInfo.value.id.split('').forEach(res => {
                    if(!engPattern.test(res) && !numberPattern.test(res)) tip.value.id = '해당 문자는 사용하실 수 없습니다';
                });
            }

            if(registerInfo.value.password.length !== 0) {
                if(registerInfo.value.password.length < 8 || registerInfo.value.password.length > 20) tip.value.password = '비밀번호는 8자리 이상, 20자리 이하여야 합니다.';
                else if(registerInfo.value.password.search(/\s/) != -1) tip.value.password = '공백은 포함하실 수 없습니다.';
                else if(decludePattern.test(registerInfo.value.password)) tip.value.password = '해당 특수문자는 포함하실 수 없습니다.';
                else if(!includePattern.test(registerInfo.value.password)) tip.value.password = '!,@,#,$,%,^,&,*,?,.중 하나가 포함되어야 합니다.';
                else if(numberPattern.test(registerInfo.value.password.split('')[0])) tip.value.password = '처음은 문자로 시작하셔야 합니다.';
                else tip.value.password = '';
            }

            if(registerInfo.value.checkPassword.length !== 0) {
                if(registerInfo.value.checkPassword !== registerInfo.value.password) tip.value.checkPassword = '비밀번호가 맞지 않습니다.';
                else tip.value.checkPassword = '';
            }

            if(registerInfo.value.name.length !== 0) {
                if(registerInfo.value.name.length > 20) tip.value.name = '닉네임은 20자리 이하여야 합니다.';
                else if(registerInfo.value.name.search(/\s/) != -1) tip.value.name = '공백은 포함하실 수 없습니다.';
                else if(decludePattern.test(registerInfo.value.name)) tip.value.name = '해당 특수문자는 포함하실 수 없습니다.';
                else tip.value.name = '';
            }

            if(registerInfo.value.code.length !== 0) {
                if(registerInfo.value.code.length !== 8) tip.value.code = '냥코 클럽의 회원 번호는 8자리입니다.';
                else if(registerInfo.value.code.search(/\s/) != -1) tip.value.code = '공백은 포함하실 수 없습니다.';
                else if(specialPattern.test(registerInfo.value.code)) tip.value.code = '해당 특수문자는 포함하실 수 없습니다.';
                else tip.value.code = '';

                registerInfo.value.code.split('').forEach(res => {
                    if(!numberPattern.test(res)) tip.value.code = '냥코 클럽의 회원 번호는 오직 숫자로만 이루어져 있습니다.';
                });
            }
        }

        const checkID = async () => {
            let { data } = await proxy.axios.post('/check_id', {
                id: registerInfo.value.id
            });

            registerInfo.value.idCheck = data;

            if(!registerInfo.value.idCheck) alert('중복된 ID 입니다!');
            else alert('해당 ID는 사용 가능합니다!');
        }

        onBeforeMount(() => {
            // if(window.sessionStorage.getItem('jwt-auth-token') !== null) location.href = '/';
        })

        return { registerInfo, tip, register, checkValue, checkID };
    }
}
</script>

<style scoped>
main {
    width: 100%;
    height: 100%;
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
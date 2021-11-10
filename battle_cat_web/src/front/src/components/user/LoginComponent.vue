<template>
  <main>
      <div id="login">
            <p class="title">LOGIN PAGE</p>
            <div class="input-field">
                <input @keyup.enter="login()" v-model="loginInfo.id" type="text" class="id" placeholder="ID를 입력해주세요" onfocus="this.select()">
                <input @keyup.enter="login()" v-model="loginInfo.password" type="password" class="password" placeholder="비밀번호를 입력해주세요" onfocus="this.select()">
                <button @click="login()">로그인</button>
            </div>
            <div class="advice">
                <router-link to="/register">혹시 계정이 없으신가요?</router-link>
                <router-link to="" @click="preventLocation()">ID/비밀번호를 잊어버리셨나요?</router-link>
            </div>
      </div>
  </main>
</template>

<script>
import { ref, getCurrentInstance, onBeforeMount, onMounted } from 'vue';

export default {
    setup() {
        const { proxy } = getCurrentInstance();
        const loginInfo = ref({
            id: '',
            password: '',
        });

        const login = async () => {
            if(loginInfo.value.id.length > 0 && loginInfo.value.password.length > 0) {
                let { data, headers } = await proxy.axios.post('/login_process', {
                    id: loginInfo.value.id,
                    password: loginInfo.value.password,
                });

                if(data.status) {
                    window.sessionStorage.setItem('jwt-auth-token', headers['jwt-auth-token']);
                    window.sessionStorage.setItem('name', data.data.name);

                    alert('로그인 성공!');
                    location.href = '/';
                } else {
                    alert('로그인 실패. ID와 비밀번호를 다시 한 번 확인해주세요.');
                }
            } else {
                alert('ID와 비밀번호를 모두 입력해주세요');
            }
        };

        const preventLocation = () => {
            alert('준비중입니다');
            location.href = '/login';
        };

        onBeforeMount(() => {
            alert('개편중입니다');
            location.href = '/';
            // if(window.sessionStorage.getItem('jwt-auth-token') !== null) location.href = '/';
            // else window.localStorage.clear();
        });

        onMounted(() => {
            document.querySelector('.id').focus();
        });

        return { loginInfo, login, preventLocation };
    }
}
</script>

<style scoped src="../../css/user/loginComponent.css" />
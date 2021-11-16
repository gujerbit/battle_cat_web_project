<template>
  <main>
      <div class="user-info" v-for="value in userInfo.data" :key="value">
        <div class="summary">
          <div class="profile-img">
            <img v-if="value.email !== undefined" @click="update.profileImg = true" class="change-image" :src="require(`../../../assets/res/unit/${value.profileImg}`)" alt="">
            <img v-else :src="require(`../../../assets/res/unit/${value.profileImg}`)" alt="">
          </div>
          <div class="account">
            <p class="name">{{value.name}}
              <span class="grade">[{{value.grade === 'user' ? '유저' : '관리자'}}]</span>
              <span class="reject" v-if="value.foreverReject">[영구 차단됨]</span>
              <span class="reject" v-else-if="new Date(value.rejectEndDate).getTime() - new Date().getTime() > 0">[차단됨 {{new Date(new Date(value.rejectEndDate).getTime() - new Date().getTime()).getDate() - 1}}일 남음]</span>
            </p>
            <p v-if="value.email !== undefined">이메일: {{value.email}}</p>
            <p v-if="value.code !== undefined">문의 코드: {{value.code}}</p>
            <p>가입날짜: {{value.regDate}}</p>
          </div>
          <div class="update">
            <button v-if="value.email !== undefined" @click="logout()">로그아웃</button>
            <button v-if="value.email !== undefined" @click="update.info = true">정보 수정</button>
          </div>
        </div>
        <div class="description">
          <div class="description-header">
            <p class="title">자기 소개</p>
            <button v-if="value.email !== undefined" @click="descriptionChange(value.email)">{{update.description ? '완료' : '수정'}}</button>
            <p class="length" v-if="update.description" :style="userInfo.description.length > 100 ? { color: '#f11212' } : ''">{{userInfo.description.length}}</p>
          </div>
          <textarea v-model="userInfo.description" :disabled="!update.description" />
        </div>
      </div>
      <div class="user-info-update" v-if="update.info">
        <div class="update-popup">
          <p>유저 정보 변경</p>
          <div class="user-name">
            <p class="popup-title">닉네임 변경</p>
            <p class="tip">{{tip.name}}</p>
            <div class="input-field">
              <input @input="checkValue()" type="text" v-model="userInfo.name" :disabled="!update.name">
              <button @click="nameChange()" :disabled="update.name && tip.name.length > 0">{{update.name ? '완료' : '수정'}}</button>
            </div>
          </div>
          <div class="code">
            <p class="popup-title">문의 코드 변경</p>
            <p class="tip">{{tip.code}}</p>
            <div class="input-field">
              <input @input="checkValue()" type="text" v-model="userInfo.code" :disabled="!update.code">
              <button @click="codeChange()" :disabled="update.code && tip.code.length > 0">{{update.code ? '완료' : '수정'}}</button>
            </div>
          </div>
          <button @click="update.info = false; clear()">취소</button>
        </div>
        <div class="background" />
      </div>
      <div class="user-profile" v-if="update.profileImg">
        <div class="profile-popup">
          <p class="popup-title">프로필 이미지 변경</p>
          <div class="img-field">
            <img @click="profileImgChange(value.image_dir)" v-for="value in unit.all" :key="value" :src="require(`../../../assets/res/unit/${value.image_dir}`)" alt="">
          </div>
          <button @click="update.profileImg = false">취소</button>
        </div>
        <div class="background" />
      </div>
      <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
      <router-link to="/community" class="community-page">커뮤니티 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';
import { useRoute } from 'vue-router';
import { checkName, checkCode } from '../../../js/util/validation.js';
import { getUnitInfo } from '../../../js/unit/unitInfo.js';

export default {
  setup() {
    const route = useRoute();
    const { proxy } = getCurrentInstance();

    const userInfo = ref({
      data: [],
      description: '',
      beforeDescription: '',
      name: '',
      beforeName: '',
      code: '',
      beforeCode: '',
      email: '',
    });

    const update = ref({
      info: false,
      description: false,
      name: false,
      code: false,
      profileImg: false,
    });

    const tip = ref({
      name: '',
      code: '',
    });

    const unit = ref({
      all: {},
    });

    const descriptionChange = async (email) => {
      update.value.description = !update.value.description;

      if(!update.value.description) {
        if(userInfo.value.beforeDescription !== userInfo.value.description) {
          if(userInfo.value.description.length <= 100) {
            let { data } = await proxy.axios.post('/change_description', {
              email: email,
              description: userInfo.value.description
            });

            if(data > 0) {
              const info = JSON.parse(window.sessionStorage.getItem('user-info'));
              info.description = userInfo.value.description;
              window.sessionStorage.setItem('user-info', JSON.stringify(info));
              alert('자기 소개 문구가 성공적으로 수정되었습니다!');
            }
          } else {
            alert('자기 소개 문구는 최대 100자까지 입력하실 수 있습니다');
            update.value.description = true;
          }
        }
      } else userInfo.value.beforeDescription = userInfo.value.description;
    };

    const nameChange = async () => {
      update.value.name = !update.value.name;

      if(!update.value.name) {
        if(userInfo.value.beforeName !== userInfo.value.name) {
          let { data:check } = await proxy.axios.post('/check_name', {
            name: userInfo.value.name
          });

          if(check) {
            let { data } = await proxy.axios.post('/change_name', {
              email: userInfo.value.email,
              name: userInfo.value.name
            });

            if(data > 0) {
              const info = JSON.parse(window.sessionStorage.getItem('user-info'));
              info.name = userInfo.value.name;
              window.sessionStorage.setItem('user-info', JSON.stringify(info));
              alert('닉네임이 성공적으로 변경되었습니다!');
              location.href = `/userInfo/${userInfo.value.name}`;
            }
            else alert('닉네임 변경 실패');
          } else {
            alert('중복된 닉네임입니다');
            update.value.name = true;
          }
        }
      } else userInfo.value.beforeName = userInfo.value.name;
    };

    const codeChange = async () => {
      update.value.code = !update.value.code;

      if(!update.value.code) {
        if(userInfo.value.beforeCode !== userInfo.value.code) {
          let { data:check } = await proxy.axios.post('/check_code', {
            code: userInfo.value.code
          });

          if(check) {
            let { data } = await proxy.axios.post('/change_code', {
              email: userInfo.value.email,
              code: userInfo.value.code
            });

            if(data > 0) {
              const info = JSON.parse(window.sessionStorage.getItem('user-info'));
              info.code = userInfo.value.code;
              window.sessionStorage.setItem('user-info', JSON.stringify(info));
              alert('문의 코드가 성공적으로 변경되었습니다!');
              location.reload();
            }
            else alert('문의 코드 변경 실패');
          } else {
            alert('중복된 문의 코드입니다! 이미 가입된 계정이 있는지 다시 한 번 확인해주세요!');
            update.value.code = true;
          }
        }
      } else userInfo.value.beforeCode = userInfo.value.code;
    };

    const profileImgChange = async (value) => {
      let { data } = await proxy.axios.post('/change_profile_img', {
        email: userInfo.value.email,
        profile_img: value
      });

      if(data > 0) {
        const info = JSON.parse(window.sessionStorage.getItem('user-info'));
        info.profile_img = value;
        window.sessionStorage.setItem('user-info', JSON.stringify(info));
        alert('프로필 이미지가 성공적으로 변경되었습니다!');
        location.reload();
      } else alert('프로필 이미지 변경 실패');
    };

    const checkValue = () => {
      checkName(userInfo.value, tip.value);
      checkCode(userInfo.value, tip.value);
    };

    const logout = () => {
      alert('로그아웃 완료');
      window.sessionStorage.removeItem('jwt-auth-token');
      window.sessionStorage.removeItem('user-info');
      location.href = '/login';
    };

    const getUserInfo = async (name) => {
      let { data } = await proxy.axios.get(`/user_info/${name}`);
      const info = {
        name: data.name,
        grade: data.grade,
        regDate: data.reg_date,
        profileImg: data.profile_img,
        description: data.description,
        rejectEndDate: data.reject_end_date,
        foreverReject: data.forever_reject,
        reputation: data.reputation,
      };

      userInfo.value.data.push(info);
      userInfo.value.description = data.description;
    };

    const clear = () => {
      if(userInfo.value.beforeName.length > 0) userInfo.value.name = userInfo.value.beforeName;
      if(userInfo.value.beforeCode.length > 0) userInfo.value.code = userInfo.value.beforeCode;
      update.value.name = false;
      update.value.code = false;
      tip.value.name = '';
      tip.value.code = '';
    };

    onBeforeMount(() => {
      const userName = route.params.userName;
      const temp = JSON.parse(window.sessionStorage.getItem('user-info'));

      if(userName === temp.name) {
        const info = {
          email: temp.email,
          name: temp.name,
          code: temp.code,
          grade: temp.grade,
          regDate: temp.reg_date,
          profileImg: temp.profile_img,
          description: temp.description,
          reportCount: temp.report_count,
          rejectEndDate: temp.reject_end_date,
          foreverReject: temp.forever_reject,
          reputation: temp.reputation,
        };

        userInfo.value.data.push(info);
        userInfo.value.description = temp.description;
        userInfo.value.name = temp.name;
        userInfo.value.code = temp.code;
        userInfo.value.email = temp.email;

        const loadData = setInterval(() => {
          if(getUnitInfo(proxy.store) !== undefined) {
            unit.value.all = getUnitInfo(proxy.store);
            clearInterval(loadData);
          }
        }, 100);
      } else getUserInfo(userName);
    });

    return { userInfo, update, tip, unit, logout, descriptionChange, nameChange, codeChange, profileImgChange, checkValue, clear };
  }
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
}

.user-info {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  margin-top: 0.5%;
}

.summary {
  width: 100%;
  height: 25%;
  border: 2px solid #ffc038;
  border-radius: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.profile-img {
  width: 20%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.profile-img img {
  width: 80%;
  height: 80%;
}

.account {
  width: 70%;
  height: 100%;
  display: grid;
  justify-items: left;
  align-items: center;
}

.account p, .grade, .reject {
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 3rem;
}

.name {
  font-size: 5rem !important;
}

.grade, .reject {
  font-size: 4.5rem !important;
}

.reject {
  color: #f11212;
}

.update {
  width: 10%;
  height: 100%;
}

.update button {
  width: 80%;
  height: 20%;
  background-color: #ffffff;
  border: 2px solid #ffc038;
  border-radius: 15px;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
  margin-top: 5%;
}

.description {
  width: 100%;
  height: 20%;
  border: 2px solid #ffc038;
  border-radius: 15px;
  margin-top: 0.5%;
  padding: 1%;
}

.description-header {
  width: 100%;
  height: 25%;
  display: flex;
  justify-content: left;
  align-items: center;
  margin-bottom: 0.5%;
}

.title {
  width: 10%;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 3rem;
}

.description-header button {
  width: 7%;
  height: 95%;
  background-color: #ffffff;
  border: 2px solid #ffc038;
  border-radius: 15px;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
}

.description textarea {
  width: 100%;
  height: 70%;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 3rem;
  resize: none;
}

.description textarea::-webkit-scrollbar {
  display: none;
}

.description-header .length {
  width: 82%;
  text-align: right;
  font-size: 2rem;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
}

.user-info button {
  transition: all 1s;
  cursor: pointer;
}

.user-info button:hover {
  transform: scale(95%);
}

.user-info-update, .user-profile {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

.update-popup {
  width: 30%;
  height: 50%;
  background-color: #ffffff;
  border: 2px solid #ffc038;
  border-radius: 15px;
}

.update-popup > p {
  height: 15%;
  font-size: 4rem;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  text-align: center;
}

.user-name, .code {
  width: 100%;
  height: 35%;
}

.user-name .popup-title, .code .popup-title {
  height: 20%;
  font-size: 2.5rem;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  text-align: center;
}

.input-field {
  width: 100%;
  height: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.input-field input {
  width: 60%;
  height: 70%;
  border: 2px solid #ffc038;
  border-radius: 15px 0 0 15px;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.5rem;
  text-align: center;
  transition: all 1s;
}

.input-field button {
  width: 20%;
  height: 70%;
  border: 2px solid #ffc038;
  border-left: none;
  border-radius: 0 15px 15px 0;
  background-color: #ffffff;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
  cursor: pointer;
  transition: all 1s;
}

.input-field button:hover {
  background-color: #ffc038;
  color: #ffffff;
}

.update-popup > button {
  width: 20%;
  height: 10%;
  border: 2px solid #ffc038;
  border-radius: 15px;
  background-color: #ffffff;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
  margin-left: 70%;
  cursor: pointer;
  transition: all 1s;
}

.update-popup > button:hover {
  transform: scale(95%);
}

.background {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  background-color: #ffffff;
  z-index: -10;
  opacity: 0.5;
}

input, textarea {
  outline: 0;
}

button:disabled {
  opacity: 0.5;
  pointer-events: none;
}

.tip {
  height: 15%;
  color: #f11212;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

.change-image {
  transition: all 1s;
  cursor: pointer;
}

.change-image:hover {
  transform: scale(95%);
}

.profile-popup {
  width: 50%;
  height: 70%;
  border: 2px solid #ffc038;
  border-radius: 15px;
  background-color: #ffffff;
}

.img-field {
  width: 100%;
  height: 80%;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  justify-items: center;
  align-items: center;
  padding: 0.5%;
  overflow: auto;
}

.img-field::-webkit-scrollbar {
  display: none;
}

.profile-popup img {
  width: 95%;
  height: 95%;
  transition: all 1s;
  cursor: pointer;
}

.profile-popup img:hover {
  transform: scale(95%);
}

.profile-popup .popup-title {
  width: 100%;
  height: 10%;
  font-size: 4rem;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  display: flex;
  justify-content: center;
  align-items: center;
}

.profile-popup button {
  width: 15%;
  height: 7%;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
  background-color: #ffffff;
  border: 2px solid #ffc038;
  border-radius: 15px;
  margin-left: 83%;
  margin-top: 1%;
  cursor: pointer;
  transition: all 1s;
}

.profile-popup button:hover {
  transform: scale(95%);
}

.main-page, .community-page {
  width: 4.5%;
  height: 100%;
  border: 2px solid #ffc038;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  position: absolute;
  top: 0;
  writing-mode: vertical-lr;
  display: flex;
  justify-content: center;
  align-items: center;
  opacity: 0;
  transition: all 1.5s;
  font-size: 3rem;
}

.main-page {
  border-left: none;
  border-radius: 0 15px 15px 0;
  left: 0;
}

.community-page {
  border-right: none;
  border-radius: 15px 0 0 15px;
  right: 0;
}
  
.main-page:hover, .community-page:hover {
  opacity: 1;
}
</style>
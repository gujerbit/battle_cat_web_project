<template>
  <main>
      <div class="user-info" v-for="value in userInfo.data" :key="value">
        <div class="summary">
          <div class="profile-img">
            <img v-if="value.email !== undefined" @click="update.profileImg = true" class="change-image" :src="require(`../../../assets/res/unit/${value.profile_img}`)" alt="">
            <img v-else :src="require(`../../../assets/res/unit/${value.profile_img}`)" alt="">
          </div>
          <div class="account">
            <p class="name">{{value.name}}
              <span class="grade">[{{value.grade === 'user' ? '유저' : '관리자'}}]</span>
              <span class="reject" v-if="value.foreverReject">[영구 차단됨]</span>
              <span class="reject" v-else-if="new Date(value.rejectEndDate).getTime() - new Date().getTime() > 0">[차단됨 {{new Date(new Date(value.rejectEndDate).getTime() - new Date().getTime()).getDate() - 1}}일 남음]</span>
            </p>
            <p v-if="value.email !== undefined">이메일: {{value.email}}</p>
            <p v-if="value.code !== undefined">문의 코드: {{value.code}}</p>
            <p>가입날짜: {{value.reg_date}}</p>
          </div>
          <div class="update">
            <button v-if="value.email !== undefined" @click="logout()">로그아웃</button>
            <button v-if="value.email !== undefined" @click="update.info = true">정보 수정</button>
          </div>
        </div>
        <div class="description">
          <div class="description-header">
            <p class="title">자기 소개</p>
            <button v-if="value.email !== undefined" @click="descriptionChange(userInfo, update, proxy.axios)">{{update.description ? '완료' : '수정'}}</button>
            <p class="length" v-if="update.description" :style="userInfo.description.length > 100 ? { color: '#f11212' } : ''">{{userInfo.description.length}}</p>
          </div>
          <textarea v-model="userInfo.description" :disabled="!update.description" onfocus="this.select()" />
        </div>
      </div>
      <div class="user-info-update" v-if="update.info">
        <div class="update-popup">
          <p>유저 정보 변경</p>
          <div class="user-name">
            <p class="popup-title">닉네임 변경</p>
            <p class="tip">{{tip.name}}</p>
            <div class="input-field">
              <input @input="checkName(userInfo, tip)" type="text" v-model="userInfo.name" :disabled="!update.name" onfocus="this.select()">
              <button @click="nameChange(userInfo, update, proxy.axios)" :disabled="update.name && tip.name.length > 0">{{update.name ? '완료' : '수정'}}</button>
            </div>
          </div>
          <div class="code">
            <p class="popup-title">문의 코드 변경</p>
            <p class="tip">{{tip.code}}</p>
            <div class="input-field">
              <input @input="checkCode(userInfo, tip)" type="text" v-model="userInfo.code" :disabled="!update.code" onfocus="this.select()">
              <button @click="codeChange(userInfo, update, proxy.axios)" :disabled="update.code && tip.code.length > 0">{{update.code ? '완료' : '수정'}}</button>
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
            <img @click="profileImgChange(userInfo, value.image_dir, proxy.axios)" v-for="value in unit.all" :key="value" :src="require(`../../../assets/res/unit/${value.image_dir}`)" alt="">
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
import { logout } from '../../../js/community/user/user.js';
import { descriptionChange, nameChange, codeChange, profileImgChange } from '../../../js/community/user/userInfo.js';

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

    const getUserInfo = async (name) => {
      let { data } = await proxy.axios.get(`/user_info/${name}`);
      const key = Object.keys(data);
      const info = {};

      for(let i = 0; i < key.length; i++) if(data[`${key[i]}`] !== null) info[`${key[i]}`] = data[`${key[i]}`];
      
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
        const key = Object.keys(temp);
        const info = {};

        for(let i = 0; i < key.length; i++) if(temp[`${key[i]}`] !== null) info[`${key[i]}`] = temp[`${key[i]}`];

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

    return { userInfo, update, tip, unit, proxy, logout, descriptionChange, nameChange, codeChange, profileImgChange, clear, checkName, checkCode };
  }
}
</script>

<style scoped src="../../../css/community/user/userInfoComponent.css" />
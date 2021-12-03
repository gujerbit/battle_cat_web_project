<template>
  <main>
      <div class="user-info" v-for="value in userInfo.data" :key="value">
        <div class="summary">
          <div class="profile-img">
            <img v-if="getAccountInfo().email === value.email" @click="update.profileImg = true" class="change-image" :src="require(`../../../assets/res/unit/${value.profile_img}`)" alt="">
            <img v-else :src="require(`../../../assets/res/unit/${value.profile_img}`)" alt="">
          </div>
          <div class="account">
            <p class="name">{{value.name}}
              <span class="grade">[{{value.grade === 'user' ? '유저' : value.grade === 'admin' ? '관리자' : value.grade === 'operator' ? '운영자' : '개발자'}}]</span>
              <span class="reject" v-if="value.forever_reject">[영구 차단됨]</span>
              <span class="reject" v-else-if="new Date(value.reject_end_date).getTime() - new Date().getTime() > 0 && new Date(value.reg_date) !== new Date(value.reject_end_date)">[약 {{Math.round(new Date(new Date(value.reject_end_date).getTime() - new Date().getTime()).getTime() / (1000 * 60 * 60 * 24))}}일 후 차단해제]</span>
            </p>
            <p v-if="getAccountInfo().email === value.email">이메일: {{value.email}}</p>
            <p v-if="getAccountInfo().email === value.email">문의 코드: {{value.code}}</p>
            <p>가입날짜: {{value.reg_date}}</p>
          </div>
          <div class="update">
            <template v-if="getAccountInfo().email === value.email">
              <button @click="logout()">로그아웃</button>
              <button @click="update.info = true">정보 수정</button>
              <button @click="update.remove = true">회원 탈퇴</button>
            </template>
            <template v-else>
              <button>신고하기</button>
            </template>
          </div>
        </div>
        <div class="description">
          <div class="description-header">
            <p class="title">자기 소개</p>
            <button v-if="getAccountInfo().email === value.email" @click="descriptionChange(userInfo.description, value.description, update, proxy.axios)">{{update.description ? '완료' : '수정'}}</button>
            <p class="length" v-if="update.description" :style="userInfo.description.length > 100 ? { color: '#f11212' } : ''">{{userInfo.description.length}}</p>
          </div>
          <textarea v-model="userInfo.description" :disabled="!update.description" onfocus="this.select()" />
        </div>
        <!-- <div class="board">
          <div class="board-header">
            <p>작성한 게시물</p>
          </div>
          <div class="board-body">
            <div class="board-content">
              
            </div>
          </div>
        </div>
        <div class="comment">
          <div class="comment-header">
            <p>작성한 댓글</p>
          </div>
          <div class="comment-body">
            <div class="comment-content">

            </div>
          </div>
        </div> -->
      </div>
      <div class="user-info-update" v-if="update.info">
        <div class="update-popup">
          <p>유저 정보 변경</p>
          <div class="user-name">
            <p class="popup-title">닉네임 변경</p>
            <p class="tip">{{tip.name}}</p>
            <div class="input-field">
              <input @input="checkName(userInfo, tip)" type="text" v-model="userInfo.name" :disabled="!update.name" onfocus="this.select()">
              <button @click="nameChange(userInfo.name, userInfo.beforeName, update, proxy.axios)" :disabled="update.name && tip.name.length > 0">{{update.name ? '완료' : '수정'}}</button>
            </div>
          </div>
          <div class="code">
            <p class="popup-title">문의 코드 변경</p>
            <p class="tip">{{tip.code}}</p>
            <div class="input-field">
              <input @input="checkCode(userInfo, tip)" type="text" v-model="userInfo.code" :disabled="!update.code" onfocus="this.select()">
              <button @click="codeChange(userInfo.code, userInfo.beforeCode, update, proxy.axios)" :disabled="update.code && tip.code.length > 0">{{update.code ? '완료' : '수정'}}</button>
            </div>
          </div>
          <button @click="update.info = false;">취소</button>
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
      <div class="user-remove" v-if="update.remove">
        <div class="remove-popup">
          <p class="popup-title">회원 탈퇴</p>
          <input v-model="userInfo.password" type="password" placeholder="비밀번호를 입력해주세요">
          <div class="btn-field">
            <button @click="userRemove(userInfo, proxy.axios)">완료</button>
            <button @click="update.remove = false; userInfo.password = ''">취소</button>
          </div>
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
import { logout, checkReject } from '../../../js/community/user/user.js';
import { descriptionChange, nameChange, codeChange, profileImgChange, userRemove } from '../../../js/community/user/userInfo.js';
import { rejectAlert } from '../../../js/util/alert.js';
import { getAccountInfo } from '../../../js/community/admin/admin.js';

export default {
  setup() {
    const route = useRoute();
    const { proxy } = getCurrentInstance();

    const userInfo = ref({
      data: [],
      description: '',
      name: '',
      code: '',
      beforeName: '',
      beforeCode: '',
    });

    const update = ref({
      info: false,
      description: false,
      name: false,
      code: false,
      profileImg: false,
      remove: false,
    });

    const tip = ref({
      name: '',
      code: '',
    });

    const unit = ref({
      all: {},
    });

    onBeforeMount(async () => {
      checkReject(proxy.axios);

      try {
        let { data } = await proxy.axios.get(`/user_info/${route.params.userName}`, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });
        
        if(data.length === 0) {
          alert('존재하지 않는 유저입니다!');
          location.href = '/admin';
        } else {
          userInfo.value.data.push(data);
          userInfo.value.description = data.description;
          userInfo.value.name = data.name;
          userInfo.value.code = data.code;
          userInfo.value.beforeName = data.name;
          userInfo.value.beforeCode = data.code;
          unit.value.all = getUnitInfo(proxy.store);
        }
      } catch (error) {
        rejectAlert();
      }
    });

    return { userInfo, update, tip, unit, proxy, logout, descriptionChange, nameChange, codeChange, profileImgChange, checkName, checkCode, userRemove, getAccountInfo };
  }
}
</script>

<style scoped src="../../../css/community/user/userInfoComponent.css" />
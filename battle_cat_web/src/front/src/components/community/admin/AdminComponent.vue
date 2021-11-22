<template>
  <main>
    <div id="admin">
      <nav class="menu">
        <p class="choose">유저 목록</p>
        <p>신고 현황</p>
        <p>감사 로그</p>
      </nav>
      <div class="user-list">
        <div class="header">
          <div class="title">
            <p>{{userInfo.user.name + ': ' + (userInfo.user.grade === 'user' ? '유저' : userInfo.user.grade === 'admin' ? '관리자' : userInfo.user.grade === 'operator' ? '운영자' : '개발자')}}</p>
          </div>
          <div class="search">
            <input @input="search()" v-model="searchInfo.search" type="text" placeholder="유저 닉네임 검색">
            <select v-model="searchInfo.grade" @change="search()">
              <option selected>모두</option>
              <option value="user">유저</option>
              <option value="admin">관리자</option>
              <option value="operator">운영자</option>
              <option value="developer">개발자</option>
            </select>
          </div>
        </div>
        <div class="content">
          <div class="user" v-for="(value, idx) in userInfo.current" :key="value">
            <router-link :to="`/userInfo/${value.name}`">
              <img :src="require(`../../../assets/res/unit/${value.profile_img}`)" alt="">
              <div class="data">
                <section>
                  <p>
                    닉네임: {{value.name}}
                    <span v-if="value.forever_reject">[영구 차단됨]</span>
                    <span v-else-if="new Date(value.reject_end_date).getTime() - new Date().getTime() > 0">[{{new Date(new Date(value.reject_end_date).getTime() - new Date().getTime()).getDate() - 1}}일 후 차단해제]</span>
                  </p>
                  <p>등급: {{value.grade === 'user' ? '유저' : value.grade === 'admin' ? '관리자' : value.grade === 'operator' ? '운영자' : '개발자'}}</p>
                  <p>가입날짜: {{value.reg_date}}</p>
                </section>
                <section>
                  <p>신고당한 횟수: {{value.report_count}}</p>
                  <p>평판 점수: {{value.reputation}}</p>
                </section>
              </div>
            </router-link>
            <div class="setting" v-if="value.name !== userInfo.user.name">
              <div class="reject" v-if="userInfo.user.grade === 'developer' || userInfo.user.grade !== value.grade && (value.grade === 'user' || value.grade === 'admin')">
                <input v-model="reject.length[idx]" type="number" placeholder="차단 기간">
                <button @click="userReject(value.name, reject.length[idx], proxy.axios)">차단</button>
                <button @click="userForeverReject(value.name, proxy.axios)">영구 차단</button>
                <button @click="userRejectRelease(value.name, proxy.axios)">차단 해제</button>
              </div>
              <div class="grade" v-if="userInfo.user.grade !== 'admin' && value.grade !== 'developer' && userInfo.user.grade !== value.grade">
                <select>
                  <option disabled selected>등급 설정</option>
                  <option>유저</option>
                  <option>관리자</option>
                  <option v-if="userInfo.user.grade === 'developer'">운영자</option>
                </select>
              </div>
            </div>
          </div>
        </div>
        <div class="pages">
          <p class="prev" @click="prevPage()">&lt;</p>
          <p :class="'page ' + `${pageInfo.currentPage === value ? 'select' : ''}`" v-for="value in pageInfo.totalPage[pageInfo.viewPage]" :key="value" @click="selectPage(value)">{{value}}</p>
          <p class="next" @click="nextPage()">&gt;</p>
        </div>
      </div>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/community" class="community-page">커뮤니티 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance, watchEffect } from 'vue';
import { pagination, pageDivision } from '../../../js/util/pagination.js';
import { searchUser } from '../../../js/community/user/userInfo.js';
import { userReject, userForeverReject, userRejectRelease } from '../../../js/community/admin/admin.js';
import { checkReject } from '../../../js/community/user/user.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const userInfo = ref({
      data: [],
      current: [],
      user: {},
      searchData: [],
    });

    const searchInfo = ref({
      search: '',
      grade: '모두',
    });

    const reject = ref({
      length: [1, 1, 1, 1, 1],
    });

    const pageInfo = ref({
      totalPage: [], //전체 페이지
      divisionPage: 5, //한 번호에 몇 개씩 데이터를 보여줄 것인지 정하는 변수
      currentPage: 1, //현재 페이지
      viewPage: 0, //현재 번호
    });

    const nextPage = () => { //다음 페이지
      if(pageInfo.value.totalPage.length - 1 > pageInfo.value.viewPage) { //전체 페이지 수보다 현재 페이지 수가 적다면
        pageInfo.value.viewPage++; //현재 페이지 수 증가
        pageInfo.value.currentPage = pageInfo.value.totalPage[pageInfo.value.viewPage][0]; //해당 페이지에 있는 번호 중 가장 앞 번호를 현재 번호로 설정
        contentUpdate(); //현재 번호에 해당하는 내용 업데이트
      }
    };

    const prevPage = () => { //이전 페이지
      if(0 < pageInfo.value.viewPage) { //현재 페이지가 0보다 크다면
        pageInfo.value.viewPage--; //현재 페이지 감소
        pageInfo.value.currentPage = pageInfo.value.totalPage[pageInfo.value.viewPage][0]; //해당 페이지에 있는 번호 중 가장 앞 번호를 현재 번호로 설정
        contentUpdate(); //현재 번호에 해당하는 내용 업데이트
      }
    };

    const selectPage = (page) => { //번호 선택할 때 실행됨
      pageInfo.value.currentPage = page; //사용자가 누른 번호를 현재 번호로 설정
      contentUpdate(); //현재 번호에 해당하는 내용 업데이트
    };

    const contentUpdate = () => { //현재 번호에 해당하는 내용 업데이트
      userInfo.value.current = pagination(userInfo.value.data, pageInfo.value.currentPage, pageInfo.value.divisionPage); //현재 번호에 해당하는 내용 페이지네이션
      pageInfo.value.totalPage = pageDivision(userInfo.value.data, pageInfo.value.divisionPage); //현재 요일 전체 데이터를 기반으로 총 페이지 수 정하기
      reject.value.length = [1, 1, 1, 1, 1];
    };

    const search = () => {
      userInfo.value.searchData = searchUser(userInfo.value.data, searchInfo.value.search, searchInfo.value.grade);
    };

    onBeforeMount(async () => {
      if(window.sessionStorage.getItem('jwt-auth-token') === null) {
        alert('로그인 후 이용가능한 시스템입니다');
        location.href = '/login';
      } else {
        checkReject(proxy.axios);

        const info = JSON.parse(window.sessionStorage.getItem('user-info'));
        userInfo.value.user = info;

        if(info.grade === 'admin' || info.grade === 'operator' || info.grade === 'developer') {
          let { data } = await proxy.axios.get('/user_data');
          userInfo.value.data = data;
        } else {
          alert('관리자만 접근 가능합니다!');
          location.href = '/community'
        }
      }

      contentUpdate();
    });

    watchEffect(() => {
      if(userInfo.value.searchData.length !== undefined && userInfo.value.searchData.length > 0) {
        userInfo.value.current = pagination(userInfo.value.searchData, pageInfo.value.currentPage, pageInfo.value.divisionPage); //현재 번호에 해당하는 내용 페이지네이션
        pageInfo.value.totalPage = pageDivision(userInfo.value.searchData, pageInfo.value.divisionPage); //현재 요일 전체 데이터를 기반으로 총 페이지 수 정하기
      } else if(userInfo.value.data.length > 0 && searchInfo.value.search.length <= 0) contentUpdate();
      else if(!userInfo.value.searchData.length) {
        userInfo.value.current = [];
        pageInfo.value.totalPage = [1];
      }
    });

    return { userInfo, pageInfo, searchInfo, reject, proxy, nextPage, prevPage, selectPage, search, userReject, userForeverReject, userRejectRelease };
  }
}
</script>

<style scoped>
main {
  width: 100%;
  height: 100%;
}

#admin {
  width: 90%;
  height: 100%;
  margin: 0 auto;
}

.menu {
  width: 100%;
  height: 5%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 1% 0;
}

.menu p {
  width: 95%;
  height: 100%;
  margin: 0.5%;
  display: flex;
  justify-content: center;
  align-items: center;
  border: 2px solid #ffc038;
  border-radius: 15px;
  font-size: 3rem;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
}

.choose {
  background-color: #ffc038;
  color: #ffffff !important;
}

.user-list {
  width: 100%;
  height: 90%;
  display: grid;
  grid-template-rows: 1fr 18fr 1fr;
}

.header {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: 4fr 6fr;
}

.title {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: left;
  align-items: center;
  font-size: 2.8rem;
}

.title p {
  width: 100%;
  height: 100%;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  border: 2px solid #ffc038;
  border-radius: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.search {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: right;
  align-items: center;
}

.search input {
  width: 40%;
  height: 95%;
  border: 2px solid #ffc038;
  border-radius: 15px 0 0 15px;
  text-align: center;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.5rem;
  outline: 0;
}

.search input::placeholder {
  color: #ffc038;
}

.search select {
  width: 15%;
  height: 95%;
  border: 2px solid #ffc038;
  border-left: none;
  border-radius: 0 15px 15px 0;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2rem;
  text-align: center;
  outline: 0;
  transition: all 1s;
  cursor: pointer;
}

.search select:hover {
  background-color: #ffc038;
  color: #ffffff;
}

.content {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: repeat(5, 1fr);
  justify-items: center;
  align-items: center;
}

.content a {
  width: 60%;
  height: 100%;
  display: flex;
  justify-content: left;
  align-items: center;
}

.content img {
  width: 20%;
  height: 85%;
}

.user {
  width: 100%;
  height: 95%;
  display: flex;
  padding-left: 1%;
  border: 2px solid #ffc038;
  border-radius: 15px;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
  transition: all 1s;
}

.user:hover {
  transform: scale(97.5%);
}

.data {
  width: 60%;
  height: 100%;
  margin-left: 1%;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  align-items: center;
}

.data p {
  color: #ffc038;
}

span {
  color: #f11212;
}

.setting {
  width: 40%;
  height: 100%;
  display: grid;
  grid-template-rows: repeat(2, 1fr);
  justify-items: center;
  align-items: center;
}

.reject {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: left;
  align-items: center;
}

.reject input {
  width: 25%;
  height: 60%;
  border: 2px solid #ffc038;
  border-radius: 15px;
  text-align: center;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
  outline: 0;
  margin-right: 2.5%;
}

.reject button {
  width: 20%;
  height: 60%;
  border: 2px solid #ffc038;
  background-color: #ffffff;
  border-left: none;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
  outline: 0;
  cursor: pointer;
  transition: all 1s;
}

.reject button:hover {
  background-color: #ffc038;
  color: #ffffff;
}

.reject button:nth-child(2) {
  border-left: 2px solid #ffc038;
  border-radius: 15px 0 0 15px;
}

.reject button:last-child {
  border-radius: 0 15px 15px 0;
}

.reject input::placeholder {
  color: #ffc038;
}

.reject input::-webkit-inner-spin-button {
  display: none;
}

.grade {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: left;
  align-items: center;
}

.grade select {
  width: 30%;
  height: 60%;
  border: 2px solid #ffc038;
  border-radius: 15px;
  text-align: center;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
  font-size: 2.3rem;
  outline: 0;
}

.pages {
  width: 100%;
  height: 100%;
  margin: 0 auto;
  display: flex;
  justify-content: space-around;
  align-items: center;
  border: 2px solid #ffc038;
  border-radius: 15px;
}
  
.pages p {
  width: 2%;
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  transition: all 0.5s;
  color: #ffc038;
  text-shadow: -0.5px 0 #000000, 0 0.5px #000000, 0.5px 0 #000000, 0 -0.5px #000000;
}
  
.pages p:hover {
  color: #ffffff;
  text-shadow: -0.5px 0 #ffc038, 0 0.5px #ffc038, 0.5px 0 #ffc038, 0 -0.5px #ffc038;
}
  
.page {
  width: 100%;
  height: 100%;
  font-size: 3rem;
}
  
.select {
  pointer-events: none;
  color: #ffffff !important;
  text-shadow: -2px 0 3px #ffc038, 0 2px 3px #ffc038, 2px 0 3px #ffc038, 0 -2px 3px #ffc038 !important;
}
  
.prev, .next {
  width: 100%;
  height: 100%;
  color: #ffffff;
  font-size: 3rem;
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
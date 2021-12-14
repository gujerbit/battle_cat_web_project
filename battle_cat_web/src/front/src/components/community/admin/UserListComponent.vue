<template>
  <div id="user-list">
    <div class="header">
      <div class="title">
        <p>{{userInfo.user.name + ': ' + (userInfo.user.grade === 'user' ? '유저' : userInfo.user.grade === 'admin' ? '관리자' : userInfo.user.grade === 'operator' ? '운영자' : '개발자')}}</p>
      </div>
      <div class="search">
        <input @input="search()" v-model="searchInfo.search" type="text" placeholder="유저 닉네임 검색">
        <select v-model="searchInfo.grade" @change="search()">
          <option value="all">모두</option>
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
                <span v-else-if="new Date(value.reject_end_date).getTime() - new Date().getTime() > 0 && new Date(value.reg_date) !== new Date(value.reject_end_date)">[약 {{Math.round(new Date(new Date(value.reject_end_date).getTime() - new Date().getTime()).getTime() / (1000 * 60 * 60 * 24))}}일 후 차단해제]</span>
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
        <div class="setting" v-if="value.name !== userInfo.user.name && userInfo.user.grade !== 'user'">
          <div class="reject" v-if="userInfo.user.grade !== 'user' && userInfo.user.grade !== value.grade && ((userInfo.user.grade === 'admin' && value.grade === 'user') || (userInfo.user.grade === 'operator' && (value.grade === 'user' || value.grade === 'admin')) || userInfo.user.grade === 'developer')">
            <input @input="reject.length[idx] < 1 ? reject.length[idx] = 1 : reject.length[idx] > 365 ? reject.length[idx] = 365 : reject.length[idx] = reject.length[idx]" @mouseleave="scrollPrevent($event)" @mouseover="wheelRejectLengthChange($event, idx)" v-model="reject.length[idx]" type="number" placeholder="차단 기간" onfocus="this.select()">
            <button :disabled="value.forever_reject" @click="userReject(value.name, reject.length[idx], value.grade, proxy.axios)">차단</button>
            <button :disabled="value.forever_reject" @click="userForeverReject(value.name, value.grade, proxy.axios)">영구 차단</button>
            <button :disabled="!value.forever_reject && new Date(value.reject_end_date).getTime() - new Date().getTime() <= 0" @click="userRejectRelease(value.name, value.grade, proxy.axios)">차단 해제</button>
          </div>
          <div class="grade" v-if="userInfo.user.grade !== 'user' && userInfo.user.grade !== 'admin' && value.grade !== 'developer' && userInfo.user.grade !== value.grade">
            <select @change="userGradeSetting(value.name, userInfo.grade, value.grade, proxy.axios)" v-model="userInfo.grade">
              <option disabled selected>등급 설정</option>
              <option v-if="value.grade !== 'user'">유저</option>
              <option v-if="value.grade !== 'admin'">관리자</option>
              <option v-if="value.grade !== 'operator' && userInfo.user.grade === 'developer'">운영자</option>
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
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance, watchEffect } from 'vue';
import { checkReject } from '../../../js/community/user/user.js';
import { searchUser } from '../../../js/community/user/userInfo.js';
import { userReject, userForeverReject, userRejectRelease, userGradeSetting } from '../../../js/community/admin/admin.js';
import { rejectAlert } from '../../../js/util/alert.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const userInfo = ref({
      data: [],
      size: 0,
      current: [],
      user: {},
      searchData: [],
      grade: '등급 설정',
    });

    const searchInfo = ref({
      search: '',
      grade: 'all',
    });

    const reject = ref({
      length: [1, 1, 1, 1, 1],
    });

    const watching = ref({
      scroll: false,
    });

    const pageInfo = ref({
      totalPage: [], //전체 페이지
      divisionPage: 5, //한 번호에 몇 개씩 데이터를 보여줄 것인지 정하는 변수
      currentPage: 1, //현재 페이지
      viewPage: 0, //현재 번호
    });

    const getUserList = async (page) => {
      try {
        let { data:user } = await proxy.axios.get(`/user_data/${page}`, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        let { data:size } = await proxy.axios.get('/user_data_size', {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        userInfo.value.data = user;
        userInfo.value.current = user;
        userInfo.value.size = size;

        userTotalPageSetting();
      } catch (error) {
        rejectAlert();
      }
    };

    const nextPage = () => { //다음 페이지
      
    };

    const prevPage = () => { //이전 페이지
      
    };

    const selectPage = (page) => { //번호 선택할 때 실행됨
      pageInfo.value.currentPage = page;
      page = (page * 5) - 5;
      getUserList(page);
    };

    const userTotalPageSetting = () => {
      pageInfo.value.totalPage = [];
      let pageArr = [];

      for(let i = 1; i <= Math.ceil(userInfo.value.size / pageInfo.value.divisionPage); i++) {
        pageArr.push(i);

        if(i % 5 === 0 || i === Math.ceil(userInfo.value.size / pageInfo.value.divisionPage)) {
          pageInfo.value.totalPage.push(pageArr);
          pageArr = [];
        }
      }
    };

    const search = () => {
      userInfo.value.searchData = searchUser(userInfo.value.data, searchInfo.value.search, searchInfo.value.grade);
      console.log(userInfo.value.searchData);
    };

    const wheelRejectLengthChange = (event, index) => {
      watching.value.scroll = true;
      let input = event.currentTarget;
      let content = event.currentTarget.closest('.content');
      content.style.pointerEvents = 'none';
      input.style.pointerEvents = 'auto';

      window.onmousewheel = e => {
        if(watching.value.scroll) {
          reject.value.length[index] += e.wheelDelta / 180;

          if(reject.value.length[index] > 365) reject.value.length[index] = 365;
          else if(reject.value.length[index] < 1) reject.value.length[index] = 1;
        }
      };
    };

    const scrollPrevent = (event) => {
      watching.value.scroll = false;
      let content = event.currentTarget.closest('.content');
      content.style.pointerEvents = 'auto';
    };

    onBeforeMount(async () => {
      const temp = 1;

      if(temp !== 1) {
        checkReject(proxy.axios);

        getUserList(0);
        userInfo.value.user = JSON.parse(window.sessionStorage.getItem('user-info'));
      }
    });

    watchEffect(() => {
      // if(userInfo.value.searchData.length > 0) {
      //   if(searchInfo.value.search.length > 0) {
      //     userInfo.value.current = userInfo.value.searchData;
      //     userInfo.value.size = userInfo.value.searchData.length;
      //     userTotalPageSetting();
      //   } else if(searchInfo.value.grade === 'all' && searchInfo.value.search.length <= 0) {
      //     getUserList((pageInfo.value.currentPage * 5) - 5);
      //   }
      // } else if(searchInfo.value.search.length > 0 && userInfo.value.searchData.length <= 0) {
      //   userInfo.value.data = [];
      //   userInfo.value.size = 0;
      //   pageInfo.value.totalPage = [1];
      // } else if(searchInfo.value.search.length <= 0) {
      //   getUserList((pageInfo.value.currentPage * 5) - 5);
      // }
    });

    return { userInfo, pageInfo, searchInfo, reject, proxy, nextPage, prevPage, selectPage, search, userReject, userForeverReject, userRejectRelease, userGradeSetting, wheelRejectLengthChange, scrollPrevent };
  }
}
</script>

<style scoped src="../../../css/community/admin/userListComponent.css" />
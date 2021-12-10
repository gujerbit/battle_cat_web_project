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
        <div class="user-writing">
          <div class="board">
            <div class="board-header">
              <p>작성한 게시물 ({{userInfo.boardSize}})</p>
            </div>
            <div class="board-body">
              <router-link :to="`/board_data/${item.idx}`" class="board-content" v-for="item in userInfo.boardData" :key="item">
                <p>{{item.title}}</p>
                <p>{{new Date(item.writing_date).toLocaleString("ko-KR", {timeZone: 'Asia/Seoul'})}}</p>
              </router-link>
            </div>
            <div class="page">
              <p @click="boardPrevPage(value.email)">&lt;</p>
              <p @click="boardSelectPage(value.email, item)" v-for="item in pageInfo.boardTotalPage[pageInfo.boardViewPage]" :key="item">{{item}}</p>
              <p @click="boardNextPage(value.email)">&gt;</p>
            </div>
          </div>
          <div class="comment">
            <div class="comment-header">
              <p>작성한 댓글 ({{userInfo.commentSize}})</p>
            </div>
            <div class="comment-body">
              <router-link :to="`/board_data/${item.board_idx}`" class="comment-content" v-for="item in userInfo.commentData" :key="item">
                <p>{{item.comment}}</p>
                <p>{{new Date(item.comment_date).toLocaleString("ko-KR", {timeZone: 'Asia/Seoul'})}}</p>
              </router-link>
            </div>
            <div class="page">
              <p @click="commentPrevPage(value.email)">&lt;</p>
              <p @click="commentSelectPage(value.email, item)" v-for="item in pageInfo.commentTotalPage[pageInfo.commentViewPage]" :key="item">{{item}}</p>
              <p @click="commentNextPage(value.email)">&gt;</p>
            </div>
          </div>
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
      boardData: [],
      boardSize: 0,
      commentData: [],
      commentSize: 0,
      description: '',
      name: '',
      code: '',
      beforeName: '',
      beforeCode: '',
      email: '',
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

    const pageInfo = ref({
      boardTotalPage: [],
      commentTotalPage: [],
      boardDivisionPage: 5,
      commentDivisionPage: 5,
      boardCurrentPage: 1,
      commentCurrentPage: 1,
      boardViewPage: 0,
      commentViewPage: 0,
    });

    const getUserBoardList = async (email, page) => {
      try {
        let { data:board } = await proxy.axios.post(`/get_user_board_list/${page}`, {
          email: email,
        }, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        let { data:size } = await proxy.axios.post('/get_user_board_list_size', {
          email: email,
        }, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        userInfo.value.boardData = board;
        userInfo.value.boardSize = size;

        boardTotalPageSetting();
      } catch (error) {
        rejectAlert();
      }
    };

    const getUserCommentList = async (email, page) => {
      try {
        let { data:comment } = await proxy.axios.post(`/get_user_comment_list/${page}`, {
          email: email,
        }, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        let { data:size } = await proxy.axios.post('/get_user_comment_list_size', {
          email: email,
        }, {
          headers: {'jwt-auth-token': window.sessionStorage.getItem('jwt-auth-token')}
        });

        userInfo.value.commentData = comment;
        userInfo.value.commentSize = size;

        commentTotalPageSetting();
      } catch (error) {
        rejectAlert();
      }
    };

    const boardNextPage = (email) => { //다음 페이지
      if(pageInfo.value.boardViewPage < pageInfo.value.boardTotalPage.length - 1) {
        pageInfo.value.boardViewPage++;
        getUserBoardList(email, (pageInfo.value.boardTotalPage[pageInfo.value.boardViewPage][0] * 5) - 5);
      }
    };

    const commentNextPage = (email) => {
      if(pageInfo.value.commentViewPage < pageInfo.value.commentTotalPage.length - 1) {
        pageInfo.value.commentViewPage++;
        getUserCommentList(email, (pageInfo.value.commentTotalPage[pageInfo.value.commentViewPage][0] * 5) - 5);
      }
    };

    const boardPrevPage = (email) => { //이전 페이지
      if(pageInfo.value.boardViewPage > 0) {
        pageInfo.value.boardViewPage--;
        getUserBoardList(email, (pageInfo.value.boardTotalPage[pageInfo.value.boardViewPage][0] * 5) - 5);
      }
    };

    const commentPrevPage = (email) => {
      if(pageInfo.value.commentViewPage > 0) {
        pageInfo.value.commentViewPage--;
        getUserCommentList(email, (pageInfo.value.commentTotalPage[pageInfo.value.commentViewPage][0] * 5) - 5);
      }
    };

    const boardSelectPage = (email, page) => { //번호 선택할 때 실행됨
      page = (page * 5) - 5;
      getUserBoardList(email, page);
    };

    const commentSelectPage = (email, page) => {
      page = (page * 5) - 5;
      getUserCommentList(email, page);
    }

    const boardTotalPageSetting = () => {
      pageInfo.value.boardTotalPage = [];
      let pageArr = [];

      for(let i = 1; i <= Math.ceil(userInfo.value.boardSize / pageInfo.value.boardDivisionPage); i++) {
        pageArr.push(i);

        if(i % 5 === 0 || i === Math.ceil(userInfo.value.boardSize / pageInfo.value.boardDivisionPage)) {
          pageInfo.value.boardTotalPage.push(pageArr);
          pageArr = [];
        }
      }
    };

    const commentTotalPageSetting = () => {
      pageInfo.value.commentTotalPage = [];
      let pageArr = [];

      for(let i = 1; i <= Math.ceil(userInfo.value.commentSize / pageInfo.value.commentDivisionPage); i++) {
        pageArr.push(i);

        if(i % 5 === 0 || i == Math.ceil(userInfo.value.commentSize / pageInfo.value.commentDivisionPage)) {
          pageInfo.value.commentTotalPage.push(pageArr);
          pageArr = [];
        }
      }
    }

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
          userInfo.value.email = data.email;
          unit.value.all = getUnitInfo(proxy.store);
        }

        getUserBoardList(data.email, 0);
        getUserCommentList(data.email, 0);
      } catch (error) {
        rejectAlert();
      }
    });

    return { userInfo, update, tip, unit, pageInfo, proxy, logout, descriptionChange, nameChange, codeChange, profileImgChange, checkName, checkCode, userRemove, getAccountInfo, boardNextPage, commentNextPage, boardPrevPage, commentPrevPage, boardSelectPage, commentSelectPage };
  }
}
</script>

<style scoped src="../../../css/community/user/userInfoComponent.css" />
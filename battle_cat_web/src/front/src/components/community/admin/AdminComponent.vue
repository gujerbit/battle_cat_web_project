<template>
  <main>
    <div id="admin">
      <nav class="menu">
        <p :class="view.select === 'userList' ? 'choose' : ''" @click="view.select = 'userList'">유저 목록</p>
        <p :class="view.select === 'reportList' ? 'choose' : ''" @click="view.select = 'reportList'">신고 현황</p>
        <p :class="view.select === 'adminLogList' ? 'choose' : ''" @click="view.select = 'adminLogList'">감사 로그</p>
      </nav>
      <user-list-component v-if="view.select === 'userList'" />
      <admin-log-component v-if="view.select === 'adminLogList'" />
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/community" class="community-page">커뮤니티 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance } from 'vue';
import { checkReject } from '../../../js/community/user/user.js';

import userListComponent from './UserListComponent.vue';
import adminLogComponent from './AdminLogComponent.vue';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const view = ref({
      select: 'userList',
    });

    onBeforeMount(() => {
      checkReject(proxy.axios);
    });

    return { view };
  },
  components: {
    userListComponent,
    adminLogComponent
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
  cursor: pointer;
  transition: all 1s;
}

.menu p:hover {
  transform: scale(95%);
}

.choose {
  background-color: #ffc038;
  color: #ffffff !important;
  pointer-events: none;
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
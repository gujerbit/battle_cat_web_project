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

<style scoped src="../../../css/community/admin/adminComponent.css" />
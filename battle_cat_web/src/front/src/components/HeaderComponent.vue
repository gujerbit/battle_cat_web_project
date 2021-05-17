<template>
  <header>
    <div class="header-container">
      <router-link to="/" class="header-logo">
        <img src="/../../imgs/temp/header_logo.png" alt />
      </router-link>
      <nav class="menu-bar">
        <ul class="main-menu-bar">
          <headerMenuComponent :menuName="'아군 캐릭터 정보'" :linkName="'/unitInfo'" />
          <headerMenuComponent :menuName="'적군 캐릭터 정보'" :linkName="''" />
          <headerMenuComponent :menuName="'스테이지 정보'" :linkName="''" />
          <headerMenuComponent :menuName="'기타 정보'" :linkName="''" />
          <headerMenuComponent :menuName="'커뮤니티'" :linkName="''" />
          <headerMenuComponent :menuName="'정보/유저 검색'" :linkName="''" />
          <headerMenuComponent :menuName="'로그인'" :linkName="''" />
          <headerMenuComponent :menuName="'회원가입'" :linkName="''" />
        </ul>
      </nav>
    </div>
  </header>
</template>

<script>
import headerMenuComponent from "./HeaderMenuComponent.vue";

export default {
  components: {
    headerMenuComponent
  }
};
</script>

<style scoped>
header {
  width: 100%;
  height: 10vh;
  min-height: 50px;
  box-shadow: 0px 5px 3px #0003;
}

.header-container {
  width: 80%;
  height: 100%;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 10% 90%;
  grid-template-rows: 100%;
}

.header-logo,
.menu-bar {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.header-logo > img {
  height: 70%;
}

.main-menu-bar {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(8, 1fr);
}
</style>
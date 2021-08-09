<template>
  <header>
    <div id="header-container">
      <router-link to="/" class="header-logo">
        <img src="./../assets/temp/header_logo.png" alt="logo" />
      </router-link>
      <nav class="menu">
        <router-link to="/unitInfo">아군 캐릭터 정보</router-link>
        <router-link to="/enemyInfo">적군 캐릭터 정보</router-link>
        <router-link to="/stageInfo">스테이지 정보</router-link>
        <router-link to="/etcInfo">기타 정보</router-link>
        <router-link to="/community">커뮤니티</router-link>
        <router-link to="/login">로그인/회원가입</router-link>
      </nav>
    </div>
  </header>
</template>

<script>
export default {
  components: {
  }
}
</script>

<style scoped>
header {
  width: 100%;
  max-height: 8vh;
  display: flex;
  justify-content: center;
  margin-top: 0.5vh;
  /* background-color: #ffc038; */
  box-shadow: 0px 3px 4px 0px #3d3d3d;
}

#header-container {
  width: 80%;
  display: flex;
  justify-content: space-between;
}

.header-logo img {
  height: 80%;
}

.menu {
  width: 75%;
  height: 100%;
  font-size: 2.5rem;
  display: flex;
}

.menu a {
  width: 25%;
  height: 8vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.menu a:hover {
  border-bottom: 2px solid #ffc038;
  padding-top: 2px;
}
</style>
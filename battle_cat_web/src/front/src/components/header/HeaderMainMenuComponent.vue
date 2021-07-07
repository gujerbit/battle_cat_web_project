<template>
  <ul class="main">
      <li>
          <router-link to="">정보</router-link>
      </li>
      <li>
          <router-link to="">커뮤니티</router-link>
      </li>
      <li>
          <router-link to="">로그인/회원가입</router-link>
      </li>
  </ul>
</template>

<script>
export default {

}
</script>

<style>
.main {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
}

.main > li > a {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    font-size: 1.8rem;
}

.main > li:hover {
  border-bottom: 2px solid #ffc343;
}

a {
    width: 100%;
    height: 100%;
}
</style>
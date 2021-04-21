<template>
  <div class="main-update-version">
    <div class="update-version-element">
      <p class="update-element">제목</p>
      <p class="update-element">날짜</p>
    </div>
    <div class="update-version-content">
      <router-link
        :to="'/update_version/' + updateVersionValue['version']"
        class="update-content"
        v-for="updateVersionValue in updateVersionData"
        :key="updateVersionValue"
      >
        <p class="update-title">{{ updateVersionValue["title"] }}</p>
        <p class="update-date">{{ updateVersionValue["update_date"] }}</p>
      </router-link>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    updateVersionData: { type: Array, default: () => [] }
  }
};
</script>

<style scoped>
.main-update-version {
  width: 100%;
  height: 30vh;
  display: grid;
  grid-template-rows: 15% 85%;
}

.update-version-element {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  background-color: #fab92c;
}

.update-element {
  width: 100%;
  height: 100%;
  font-size: 2rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

.update-version-content {
  width: 100%;
  height: 100%;
  font-size: 1.5rem;
}

.update-content {
  width: 100%;
  height: 20%;
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  border-bottom: 1px solid #fab92c;
}

.update-title,
.update-date {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
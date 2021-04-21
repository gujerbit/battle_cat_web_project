<template>
  <div class="main-update-version">
    <div class="update-version-element">
      <div class="update-element-list">
        <p class="update-title-element">제목</p>
        <p class="update-date-element">날짜</p>
      </div>
    </div>
    <div class="update-version-content">
      <div class="update-content-list">
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
  </div>
</template>

<script>
export default {
  props: {
    updateVersionData: { type: Array, default: () => [] },
  },
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
    height: 100%;
    display: flex;
}

.update-element-list {
    width: 100%;
    height: 100%;
    display: grid;
    grid-template-columns: 1fr 1fr;
    align-items: center;
    text-align: center;
    font-size: 1.4em;
    background-color: #f0a535;
}

.update-version-content {
    display: flex;
    justify-content: center;
}

.update-content-list {
    width: 100%;
}

.update-content-list > .update-content {
    width: 100%;
    height: 20%;
    display: grid;
    grid-template-columns: 1fr 1fr;
    font-size: 1.2em;
    box-shadow: 0px 1.5px 3px #f0a535;
    align-items: center;
}

.update-content-list > .update-content > .update-title, .update-date {
    display: flex;
    justify-content: center;
}
</style>
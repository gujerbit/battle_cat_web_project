<template>
  <li>
    <router-link :to="linkName">{{ menuName }}</router-link>
  </li>
</template>

<script>
export default {
  props: {
    menuName: { type: String, default: "" },
    linkName: { type: String, default: "" },
  },
};
</script>

<style scoped>
.main-menu-bar > li {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    font-size: 1.8rem;
}
</style>
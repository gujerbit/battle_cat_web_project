<template>
  <router-link
    @click="activeMenu($event)"
    :class="className"
    :name="className"
    to=""
    >{{ selectName }}</router-link
  >
</template>

<script>
export default {
  methods: {
    activeMenu(event) {
      let className = event.currentTarget.className;
      let name = event.currentTarget.name;
      let isActive = className.includes("select-active");

      if (isActive) {
        event.currentTarget.classList.remove("select-active");
        this.findList = this.findList.filter((value) => value !== name);
      } else {
        event.currentTarget.classList.add("select-active");
        this.findList.push(name);
      }

      console.log(this.findList);
    },
  },
  props: {
    className: { type: String, default: "" },
    selectName: { type: String, default: "" },
  },
};
</script>

<style scoped>
a {
  background-color: #bbbbbb;
  border-radius: 10px;
  height: 50%;
}

.select-active {
  background-color: #fab92c !important;
}
</style>
<template>
  <div class="grade">
    <div class="title">{{title}}</div>
    <div class="element" v-if="type === 0">
        {{type}}
    </div>
    <div class="element" v-if="type === 1">
        {{type}}
    </div>
    <div class="element" v-if="type === 2">
        {{type}}
    </div>
  </div>
</template>

<script>
export default {
    props: {
        title: { type: String, default: "" },
        type: { type: Number, default: 0 }
    }
};
</script>

<style scoped>
</style>
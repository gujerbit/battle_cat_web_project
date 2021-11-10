<template>
  <div id="content">
      <router-link :to="`/enemyInfo/${value.id}`" class="unit-content" v-for="value in unitData" :key="value">
            <img :src="require(`./../../assets/res/enemy/${value.image_dir}`)" alt="">
            <p>{{value.name}}</p>
      </router-link>
  </div>
</template>

<script>
export default {
    data() {
        return {
        }
    },
    mounted() {

    },
    props: ['unitData']
}
</script>

<style scoped>
#content {
    width: 55%;
    margin: 0 auto;
    margin-top: 0.5vh;
    display: grid;
    grid-template-columns: repeat(8, 1fr);
    gap: 1px;
}

.unit-content {
    font-size: 2rem;
    text-align: center;
    border: 1px solid #000000;
}

.unit-content img {
    padding-top: 5%;
}
</style>
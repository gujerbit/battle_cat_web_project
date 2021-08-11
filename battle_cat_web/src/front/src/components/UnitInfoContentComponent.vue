<template>
    <div id="content">
        <router-link :to="`/unitInfo/${value.id}`" class="unit-content" v-for="value in unitData" :key="value">
            <img :src="require(`./../assets/res/unit/${value.image_dir}`)" alt="">
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
    width: 100%;
    margin-top: 0.5vh;
    display: grid;
    grid-template-columns: repeat(6, 1fr);
}

.unit-content {
    display: flex;
    align-items: center;
    font-size: 2em;
    border: 1px solid #000000;
}

.two {
    background-color: #000000;
}
</style>
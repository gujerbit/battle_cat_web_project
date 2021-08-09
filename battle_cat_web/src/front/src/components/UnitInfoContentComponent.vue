<template>
    <div id="content">
        <router-link to="" class="unit-content" v-for="value in unitData" :key="value">
            <img :src="require(`./../assets/res/unit/${value.image_dir}`)" alt="">
            <p>ID : {{value.id}}</p>
            <p>이름 : {{value.name}}</p>
            <p>설명 : {{value.description}}</p>
            <p>레어도 : {{value.rarity}}</p>
        </router-link>
    </div>
</template>

<script>
export default {
    data() {
        return {
        }
    },
    async mounted() {
    },
    props: {
        unitData: { type: Array, data: () => [] }
    }
}
</script>

<style scoped>
#content {
    width: 80%;
}

.unit-content {
    display: flex;
    align-items: center;
    font-size: 2em;
}
</style>
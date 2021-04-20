<template>
    <div class="update-version-container">
        <headerComponent></headerComponent>
        <main>
            <div class="temp" v-for="value in updateVersionData" :key="value">
                {{value}}
            </div>
        </main>
        <footerComponent></footerComponent>
    </div>
</template>

<script>
import headerComponent from "./HeaderComponent.vue";
import footerComponent from "./FooterComponent.vue";
import axios from 'axios';

const DOMAIN = "http://localhost:8090";

export default {
    data() {
        return {
            updateVersionData: []
        }
    },
    components: {
        headerComponent,
        footerComponent
    },
    async mounted() {
        let {data} = await axios.get(`${DOMAIN}/update_version`);
        this.updateVersionData = data;
        console.log(data);
    }
}
</script>

<style scoped>
    @import url('./../../public/css/app.css');
</style>
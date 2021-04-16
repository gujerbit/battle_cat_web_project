<template>
    <div id="container">
        <headerComponent></headerComponent>
        <mainContentComponent></mainContentComponent>
        <footerComponent></footerComponent>
    </div>
</template>

<script>
import headerComponent from './HeaderComponent.vue';
import mainContentComponent from './MainContentComponent.vue';
import footerComponent from './FooterComponent.vue';
import axios from 'axios';

export default {
    components: {
        headerComponent,
        mainContentComponent,
        footerComponent
    },
    mounted() {
        axios.get('/').then(res => {
            console.log(res);
        });
    }
}
</script>

<style scoped>

</style>
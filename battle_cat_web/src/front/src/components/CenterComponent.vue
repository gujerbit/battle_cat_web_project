<template>
    <div id="container">
        <headerComponent></headerComponent>
        <mainComponent></mainComponent>
        <footerComponent></footerComponent>
    </div>
</template>

<script>
import headerComponent from './HeaderComponent.vue';
import mainComponent from './MainComponent.vue';
import footerComponent from './FooterComponent.vue';

export default {
    components: {
        headerComponent,
        mainComponent,
        footerComponent
    }
}
</script>

<style scoped>

</style>
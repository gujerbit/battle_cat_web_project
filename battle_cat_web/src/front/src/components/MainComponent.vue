<template>
    <div id="container">
        <header-component></header-component>
        <div id="main-content">
            <main>
                <article id="main-slide-banner">
                    <img src="/../../imgs/res/event/event_banner_easter.png" alt="">
                </article>
            </main>
            <footerComponent></footerComponent>
        </div>
    </div>
</template>

<script>
import headerComponent from './HeaderComponent.vue';
import footerComponent from './FooterComponent.vue';

export default {
    components: {
        headerComponent,
        footerComponent
    }
}
</script>

<style scoped>
    
</style>
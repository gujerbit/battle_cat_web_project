<template>
    <div>
        <header-component></header-component>
        <main>
            <h1>This is Main Page</h1>
        </main>
        <footerComponent></footerComponent>
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
<template>
    <div>

    </div>
</template>

<script>
import axios from 'axios';

export default {
    mounted() {
        axios.get('/updateVersion').then(res => {
            console.log(res);
            this.$router.push('/');
        });
    }
}
</script>

<style scoped>

</style>
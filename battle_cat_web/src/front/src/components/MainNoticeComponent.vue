<template>
    <div></div>    
</template>

<script>
import axios from 'axios';
console.log(axios);

export default {
    beforeCreate() {
        console.log('a');
        axios.get('/').then(res => {
            console.log(res);
            this.$router.push('/main');
        });
    }
}
</script>

<style scoped>

</style>
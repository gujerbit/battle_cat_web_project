<template>
    <div>

    </div>
</template>

<script>
import axios from 'axios';

export default {
    mounted() {
        axios.get('/msg').then(res => {
            console.log(res);
        });
        axios.post('/msg', {msg:"hello", age:10}).then(res => {
            console.log(res);
            this.$router.push('/');
        });
    }
}
</script>
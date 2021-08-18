<template>
  <main>
      
  </main>
</template>

<script>
import axios from 'axios';

const DOMAIN = 'http://localhost:8090';

export default {
    data() {
        return {

        }
    },
    async mounted() {
        let { data } = await axios.get(`${DOMAIN}/enemy_data`);
        console.log(data);
    }
}
</script>

<style scoped>

</style>
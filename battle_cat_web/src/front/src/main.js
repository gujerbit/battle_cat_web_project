import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import axios from 'axios';

const app = createApp(App);
// axios.defaults.baseURL = 'https://localhost';
axios.defaults.baseURL = 'https://nyanko-db.shop';
app.config.globalProperties.axios = axios;
app.config.globalProperties.store = store;
app.use(router).use(store).mount('#app');
//createApp(App).use(router).mount('#app');
import {createRouter, createWebHistory} from 'vue-router';
import mainComponent from '../components/MainComponent.vue';

const routes = [
    {
        path: '/',
        component: mainComponent
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
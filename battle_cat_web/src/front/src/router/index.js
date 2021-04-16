import {createRouter, createWebHistory} from 'vue-router';
import mainComponent from '../components/MainComponent.vue';
import mainNoticeComponent from '../components/MainNoticeComponent.vue';

const routes = [
    {
        path: '/',
        component: mainNoticeComponent
    },
    {
        path: '/main',
        component: mainComponent
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
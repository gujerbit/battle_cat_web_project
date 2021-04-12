import {createRouter, createWebHistory} from 'vue-router';
import centerComponent from '../components/CenterComponent.vue';

const routes = [
    {
        path: '/',
        component: centerComponent
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
import {createRouter, createWebHistory} from 'vue-router';
import centerComponent from '../components/CenterComponent.vue';
import updateVersionComponent from '../components/UpdateVersionComponent.vue';

const routes = [
    {
        path: '/',
        component: centerComponent
    },
    {
        path: '/updateVersion',
        component: updateVersionComponent
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
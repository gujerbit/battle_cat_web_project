import {createRouter, createWebHistory} from 'vue-router';
import mainComponent from '../components/MainComponent.vue';
import updateVersionComponent from '../components/UpdateVersionComponent.vue';

const routes = [
    {
        path: '/',
        component: mainComponent
    },
    {
        path: '/update_version/:version',
        component: updateVersionComponent,
        props: true
    },
    {
        path: '/:pathMatch(.*)*',
        redirect: '/'
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
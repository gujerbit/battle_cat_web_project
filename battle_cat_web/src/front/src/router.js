import {createWebHistory, createRouter} from 'vue-router';
import MainComponent from './components/MainComponent.vue';
import MsgComponent from './components/MsgComponent.vue';

let routes = [
    {
        path: '/',
        name: 'main',
        component: MainComponent
    },
    {
        path: '/msg',
        name: 'msg',
        component: MsgComponent
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
import {createRouter, createWebHistory} from 'vue-router';
import centerComponent from '../components/CenterComponent.vue';
import msg from '../components/MsgComponent.vue';
import member from '../components/MemberComponent.vue';

const routes = [
    {
        path: '/',
        component: centerComponent
    },
    {
        path: '/msg',
        component: msg
    },
    {
        path: '/db',
        component: member
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
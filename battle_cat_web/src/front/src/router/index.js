import {createRouter, createWebHistory} from 'vue-router';
import mainComponent from '../components/MainComponent.vue';
import mainContentComponent from '../components/MainContentComponent.vue';
import unitInfoComponent from '../components/UnitInfoComponent.vue';
import updateVersionComponent from '../components/UpdateVersionComponent.vue';

const routes = [
    {
        path: '/',
        component: mainComponent,
        children: [
            {
                path: '/',
                component: mainContentComponent
            },
            {
                path: '/unitInfo',
                component: unitInfoComponent
            }
        ]
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
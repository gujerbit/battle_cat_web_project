import {createRouter, createWebHistory} from 'vue-router';
import mainComponent from '../components/MainComponent.vue';
import unitInfoComponent from '../components/UnitInfoComponent.vue';
import unitInfoSpecComponent from '../components/UnitInfoSpecificComponent.vue';
import enemyInfoComponent from '../components/EnemyInfoComponent.vue';
// import updateVersionComponent from '../components/UpdateVersionComponent.vue';
// import mainContentComponent from '../components/MainContentComponent.vue';

const routes = [
    {
        path: '/',
        component: mainComponent,
    },
    {
        path: '/unitInfo',
        component: unitInfoComponent,
    },
    {
        path: '/unitInfo/:unitData',
        component: unitInfoSpecComponent,
        props: true
    },
    {
        path: '/enemyInfo',
        component: enemyInfoComponent,
    },
    {
        path: '/:pathMatch(.*)*',
        redirect: '/'
    }
    // ,{
    //     path: '/update_version/:version',
    //     component: updateVersionComponent,
    //     props: true
    // },
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
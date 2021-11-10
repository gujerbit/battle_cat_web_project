import {createRouter, createWebHistory} from 'vue-router';
import mainComponent from '../components/main/MainComponent.vue';
import unitInfoComponent from '../components/unit/UnitInfoComponent.vue';
import unitInfoSpecComponent from '../components/unit/UnitInfoSpecificComponent.vue';
import enemyInfoComponent from '../components/enemy/EnemyInfoComponent.vue';
import enemyInfoSpecComponent from '../components/enemy/EnemyInfoSpecificComponent.vue';
import stageInfoComponent from '../components/stage/StageInfoComponent.vue';
import stageInfoSpecComponent from '../components/stage/StageInfoSpecificComponent.vue';
import etcInfoComponent from '../components/etc/EtcInfoComponent.vue';
import communityComponent from '../components/community/CommunityComponent.vue';
import loginComponent from '../components/community/user/LoginComponent.vue';
import registerComponent from '../components/community/user/RegisterComponent.vue';
import scheduleComponent from '../components/etc/schedule/ScheduleComponent.vue';
import updateScheduleSpecificComponent from '../components/etc/schedule/UpdateScheduleSpecificComponent.vue';
import patchNoteSpecificComponent from '../components/etc/schedule/PatchNoteSpecificComponent.vue';


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
        path: '/unitInfo/:unitId',
        component: unitInfoSpecComponent,
        props: true
    },
    {
        path: '/enemyInfo',
        component: enemyInfoComponent,
    },
    {
        path: '/enemyInfo/:unitData',
        component: enemyInfoSpecComponent,
        props: true
    },
    {
        path: '/stageInfo',
        component: stageInfoComponent,
    },
    {
        path: '/stageInfo/:stageData',
        component: stageInfoSpecComponent,
        props: true
    },
    {
        path: '/etcInfo',
        component: etcInfoComponent,
    },
    {
        path: '/community',
        component: communityComponent,
    },
    {
        path: '/login',
        component: loginComponent,
    },
    {
        path: '/register',
        component: registerComponent
    },
    {
        path: '/schedule',
        component: scheduleComponent,
    },
    {
        path: '/update_specific/:version',
        component: updateScheduleSpecificComponent,
        props: true
    },
    {
        path: '/patch_note_specific/:version',
        component: patchNoteSpecificComponent,
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
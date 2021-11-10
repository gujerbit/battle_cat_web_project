import { createStore } from 'vuex';
import { header } from './modules/header.js';
import { schedule } from './modules/schedule.js';
import { unitInfo } from './modules/unitInfo.js';
import createPersistedState from 'vuex-persistedstate';

export default createStore({
    modules: {
        header,
        schedule,
        unitInfo,
    },
    plugins: [
        createPersistedState()
    ],
});
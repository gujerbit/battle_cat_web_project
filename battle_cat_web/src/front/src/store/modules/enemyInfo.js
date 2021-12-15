export const enemyInfo = {
    state: () => ({
        info: '',
        searchInfo: '',
    }),
    mutations: {
        setEnemyInfo(state, value) {
            state.info = value;
        },
        setSearchEnemyInfo(state, value) {
            state.searchInfo = value;
        }
    },
    getters: {
        getEnemyInfo(state) {
            return state.info;
        },
        getSearchEnemyInfo(state) {
            return state.searchInfo;
        }
    }
}
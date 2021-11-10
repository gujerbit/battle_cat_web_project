export const unitInfo = {
    state: () => ({
        info: '',
        searchInfo: '',
    }),
    mutations: {
        setUnitInfo(state, value) {
            state.info = value;
        },
        setSearchUnitInfo(state, value) {
            state.searchInfo = value;
        }
    },
    getters: {
        getUnitInfo(state) {
            return state.info;
        },
        getSearchUnitInfo(state) {
            return state.searchInfo;
        }
    }
}
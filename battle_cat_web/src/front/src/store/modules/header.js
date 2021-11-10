export const header = {
    state: () => ({
        path: '',
    }),
    mutations: {
        setPath(state, path) {
            state.path = path;
        }
    },
    getters: {
        getPath(state) {
            return state.path;
        }
    }
}
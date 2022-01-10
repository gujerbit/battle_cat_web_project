export const stageInfo = {
  state: () => ({
    info: '',
    searchInfo: '',
  }),
  mutations: {
    setStageInfo(state, value) {
      state.info = value;
    },
    setSearchStageInfo(state, value) {
      state.searchInfo = value;
    }
  },
  getters: {
    getStageInfo(state) {
      return state.info;
    },
    getSearchStageInfo(state) {
      return state.searchInfo;
    }
  }
};
export const schedule = {
    state: () => ({
        dailySchedule: '',
        eventSchedule: '',
        gachaSchedule: '',
        updateSchedule: '',
        patchNote: '',
    }),
    mutations: {
        setDailySchedule(state, value) {
            state.dailySchedule = value;
        },
        setEventSchedule(state, value) {
            state.eventSchedule = value;
        },
        setGachaSchedule(state, value) {
            state.gachaSchedule = value;
        },
        setUpdateSchedule(state, value) {
            state.updateSchedule = value;
        },
        setPatchNote(state, value) {
            state.patchNote = value;
        }
    },
    getters: {
        getDailySchedule(state) {
            return state.dailySchedule;
        },
        getEventSchedule(state) {
            return state.eventSchedule;
        },
        getGachaSchedule(state) {
            return state.gachaSchedule;
        },
        getUpdateSchedule(state) {
            return state.updateSchedule;
        },
        getPatchNote(state) {
            return state.patchNote;
        }
    }
}
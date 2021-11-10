export { setSchedule, getDailySchedule, getEventSchedule, getGachaSchedule, getUpdateSchedule, searchUpdateSchedule, getPatchNote, searchPatchNote };

async function setSchedule(axios, store) { //모든 일정 가져오기
    if(store.getters.getDailySchedule === '') { //store에 해당 데이터가 없다면
        let { data:daily } = await axios.get('/schedule/daily'); //하루 일정 가져오기
        store.commit('setDailySchedule', daily); //store에 해당 데이터 삽입
    }

    if(store.getters.getEventSchedule === '') {
        let { data:event } = await axios.get('/schedule/event');
        store.commit('setEventSchedule', event);
    }

    if(store.getters.getGachaSchedule === '') {
        let { data:gacha } = await axios.get('/schedule/gacha');
        store.commit('setGachaSchedule', gacha);
    }

    if(store.getters.getUpdateSchedule === '') {
        let { data:update } = await axios.get('/schedule/update');
        store.commit('setUpdateSchedule', update);
    }

    if(store.getters.getPatchNote === '') {
        let { data:patch } = await axios.get('/schedule/patch_note');
        store.commit('setPatchNote', patch);
    }
}

function getDailySchedule(store, week) { //하루 일정 가져오기
    if(store.getters.getDailySchedule !== '') return store.getters.getDailySchedule.filter(res => res.week === week); //해당 요일에 해당하는 하루 일정 넘겨주기
}

function getEventSchedule(store) {
    if(store.getters.getEventSchedule !== '') return store.getters.getEventSchedule;
}

function getGachaSchedule(store) {
    if(store.getters.getGachaSchedule !== '') return store.getters.getGachaSchedule;
}

function getUpdateSchedule(store) {
    if(store.getters.getUpdateSchedule !== '') return store.getters.getUpdateSchedule;
}

function searchUpdateSchedule(store, version) {
    if(store.getters.getUpdateSchedule !== '') return store.getters.getUpdateSchedule.filter(res => res.version === version);
}

function getPatchNote(store) {
    if(store.getters.getPatchNote !== '') return store.getters.getPatchNote.reverse();
}

function searchPatchNote(store, version) {
    if(store.getters.getPatchNote !== '') return store.getters.getPatchNote.filter(res => res.version === version);
}
export { setStageInfo, getStageInfo, setSearchStageInfo, getSearchStageInfo, getSpecificStageInfo };

async function setStageInfo(axios, store) {
  let { data:size } = await axios.get('/stage_data_size');

  if(store.getters.getStageInfo === '' || store.getters.getStageInfo.length < size) {
    const sizeArr = [];
    const data = [];

    for(let i = 0; i < Math.ceil(size / 100); i++) sizeArr.push(i * 100);

    for(let i = 0; i < sizeArr.length; i++) {
      let { data:stages } = await axios.get(`/stage_data/${sizeArr[i]}`);

      for(let j = 0; j < stages.length; j++) data.push(stages[j]);
    }
    
    store.commit('setStageInfo', data);
  }
}

function getStageInfo(store) {
  if(store.getters.getStageInfo !== '') return store.getters.getStageInfo;
}

function setSearchStageInfo(value, search, store) {
  const searchInfo = [];

  value.forEach(res => {
    if(res.sortation === search) searchInfo.push(res);
  });
  
  store.commit('setSearchStageInfo', searchInfo);
}

function getSearchStageInfo(store) {
  if(store.getters.getSearchStageInfo !== '') return store.getters.getSearchStageInfo;
}

function getSpecificStageInfo(store, id) {
  return store.getters.getStageInfo.filter(res => res.id === id);
}
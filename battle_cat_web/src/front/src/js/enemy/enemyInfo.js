export { setEnemyInfo, getEnemyInfo, setSearchEnemyInfo, getSearchEnemyInfo, getSpecificEnemyInfo };

async function setEnemyInfo(axios, store) {
  let { data:size } = await axios.get('/enemy_data_size');

  if(store.getters.getEnemyInfo === '' || store.getters.getEnemyInfo.length < size) {
    const sizeArr = [];
    const data = [];

    for(let i = 0; i < Math.ceil(size / 100); i++) sizeArr.push(i * 100);

    for(let i = 0; i < sizeArr.length; i++) {
      let { data:enemys } = await axios.get(`/enemy_data/${sizeArr[i]}`);

      for(let j = 0; j < enemys.length; j++) data.push(enemys[j]);
    }

    store.commit('setEnemyInfo', data);
  }
}

function getEnemyInfo(store) {
  if(store.getters.getEnemyInfo !== '') return store.getters.getEnemyInfo;
}

function setSearchEnemyInfo(value, store) {
  let enemyInfo = store.getters.getEnemyInfo;
  let result = [];

  if(value.property.length > 0 || value.target.length > 0 || value.attackType.length > 0) {
    if(value.include) {
      enemyInfo.forEach(res => {
        let resData = ['property', 'target', 'attack_type'];
        let valueData = ['property', 'target', 'attackType'];

        for(let i = 0; i < resData.length; i++) includeSetting(res[resData[i]], value[valueData[i]], res, result);
      });

      if(value.search !== '') {
        enemyInfo.forEach(res => {
          if(res.name.includes(value.search)) result.push(res);
        });
      }

      const noneDuplicateResult = new Set(result);
      store.commit('setSearchEnemyInfo', [...noneDuplicateResult]);
    } else {
      for(let i = 0; i < enemyInfo.length; i++) {
        let propertyArr = enemyInfo[i].property.split('/');
        
        if(excludeSetting(propertyArr, value.property).length === 0) result.push(enemyInfo[i]);
      }

      if(value.target.length > 0) {
        enemyInfo = result;
        result = [];

        for(let i = 0; i < enemyInfo.length; i++) {
          let targetArr = enemyInfo[i].target.split('/');

          if(excludeSetting(targetArr, value.target).length === 0) result.push(enemyInfo[i]);
        }
      }

      if(value.attackType.length > 0) {
        enemyInfo = result;
        result = [];
                
        for(let i = 0; i < enemyInfo.length; i++) {
          let attackTypeArr = enemyInfo[i].attack_type.split('/');

          if(excludeSetting(attackTypeArr, value.attackType).length === 0) result.push(enemyInfo[i]);
        }
      }
            
      const noneDuplicateResult = new Set(result);
      store.commit('setSearchEnemyInfo', [...noneDuplicateResult]);

      if(value.search !== '') store.commit('setSearchEnemyInfo', store.getters.getSearchEnemyInfo.filter(res => res.name.includes(value.search)));
    }
  } else if(value.search !== '') {
    store.commit('setSearchEnemyInfo', enemyInfo.filter(res => res.name.includes(value.search)));
  } else {
    store.commit('setSearchEnemyInfo', '');
  }
}

function includeSetting(data, value, res, result) {
  data.split('/').forEach(item => {
    if(value !== undefined && value.length > 0) {
      value.forEach(content => {
        if(item.split(',')[0].length > 1 && item.split(',')[0] === content) result.push(res);
        else if(item.split(',')[0].length <= 1 && item === content) result.push(res);
      });
    }
  });
}

function excludeSetting(arr, value) {
  for(let i = 0; i < arr.length; i++) for(let j = 0; j < value.length; j++) if(arr[i] === value[j]) value = value.filter(res => res !== arr[i]);

  return value;
}

function getSearchEnemyInfo(store) {
  if(store.getters.getSearchEnemyInfo !== '') return store.getters.getSearchEnemyInfo;
}

function getSpecificEnemyInfo(store, id) {
  return store.getters.getEnemyInfo.filter(res => res.id === id);
}
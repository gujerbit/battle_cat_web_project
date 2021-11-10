export { setUnitInfo, getUnitInfo, setSearchUnitInfo, getSearchUnitInfo, getSpecificUnitInfo };

async function setUnitInfo(axios, store) {
  if(store.getters.getUnitInfo === '') {
    let { data } = await axios.get('/unit_data');
    store.commit('setUnitInfo', data);
  }
}

function getUnitInfo(store) {
  if(store.getters.getUnitInfo !== '') return store.getters.getUnitInfo;
}

function setSearchUnitInfo(value, store) {
  let unitInfo = store.getters.getUnitInfo;
  let result = [];

  if(value.property.length > 0 || value.rarity.length > 0 || value.target.length > 0 || value.attackType.length > 0) {
    if(value.include) {
      unitInfo.forEach(res => {
        let resData = ['property', 'instinct', 'target', 'attack_type'];
        let valueData = ['property', 'property', 'target', 'attackType'];

        for(let i = 0; i < resData.length; i++) includeSetting(res[resData[i]], value[valueData[i]], res, result);

        value.rarity.forEach(item => {
          if(item === res.rarity) result.push(res);
        });
      });

      if(value.search !== '') {
        unitInfo.forEach(res => {
          if(res.name.includes(value.search)) result.push(res);
        });
      }

      const noneDuplicateResult = new Set(result);
      store.commit('setSearchUnitInfo', [...noneDuplicateResult]);
    } else {
      for(let i = 0; i < unitInfo.length; i++) {
        let propertyArr = unitInfo[i].property.split('/');
        let instinctArr = unitInfo[i].instinct.split('/');
        let combinePropertyArr = [];

        for(let j = 0; j < propertyArr.length; j++) {
          combinePropertyArr.push(propertyArr[j]);
        }

        for(let j = 0; j < instinctArr.length; j++) {
          combinePropertyArr.push(instinctArr[j]);
        }

        if(excludeSetting(combinePropertyArr, value.property).length === 0) result.push(unitInfo[i]);
      }
            
      if(value.rarity.length > 0) {
        unitInfo = result;
        result = [];

        for(let i = 0; i < unitInfo.length; i++) {
          let rarityTemp = value.rarity;
    
          for(let j = 0; j < rarityTemp.length; j++) if(unitInfo[i].rarity == rarityTemp[j]) rarityTemp = rarityTemp.filter(res => res !== unitInfo[i].rarity);
          if(rarityTemp.length === 0) result.push(unitInfo[i]);
        }
      }

      if(value.target.length > 0) {
        unitInfo = result;
        result = [];

        for(let i = 0; i < unitInfo.length; i++) {
          if(excludeSetting(unitInfo[i].target.split('/'), value.target).length === 0) result.push(unitInfo[i]);
        }
      }

      if(value.attackType.length > 0) {
        unitInfo = result;
        result = [];
                
        for(let i = 0; i < unitInfo.length; i++) {
          if(excludeSetting(unitInfo[i].attack_type.split('/'), value.attackType).length === 0) result.push(unitInfo[i]);
        }
      }
            
      const noneDuplicateResult = new Set(result);
      store.commit('setSearchUnitInfo', [...noneDuplicateResult]);

      if(value.search !== '') store.commit('setSearchUnitInfo', store.getters.getSearchUnitInfo.filter(res => res.name.includes(value.search)));
    }
  } else if(value.search !== '') {
    store.commit('setSearchUnitInfo', unitInfo.filter(res => res.name.includes(value.search)));
  } else {
    store.commit('setSearchUnitInfo', '');
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
  for(let i = 0; i < arr.length; i++) {
    let temp = arr[i].split(',').length > 1 ? arr[i].split(',')[0] : arr[i];

    for(let j = 0; j < value.length; j++) if(temp === value[j]) value = value.filter(res => res !== temp);
  }

  return value;
}

function getSearchUnitInfo(store) {
  if(store.getters.getSearchUnitInfo !== '') return store.getters.getSearchUnitInfo;
}

function getSpecificUnitInfo(store, id) {
  return store.getters.getUnitInfo.filter(res => res.id.split('-')[0] === id);
}
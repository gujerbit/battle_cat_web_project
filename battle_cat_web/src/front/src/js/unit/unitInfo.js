export { setUnitInfo, getUnitInfo, setSearchUnitInfo, getSearchUnitInfo, getSpecificUnitInfo };

async function setUnitInfo(axios, store) {
  let { data:size } = await axios.get('/unit_data_size');

  if(store.getters.getUnitInfo === '' || store.getters.getUnitInfo.length < size) {
    const sizeArr = [];
    const data = [];

    for(let i = 0; i < Math.ceil(size / 100); i++) sizeArr.push(i * 100);

    for(let i = 0; i < sizeArr.length; i++) {
      let { data:units } = await axios.get(`/unit_data/${sizeArr[i]}`);

      for(let j = 0; j < units.length; j++) data.push(units[j]);
    }
    
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
        let resData = ['property', 'target', 'attack_type'];
        let valueData = ['property', 'target', 'attackType'];

        for(let i = 0; i < resData.length; i++) includeSetting(res[resData[i]], value[valueData[i]], res, result);

        res.instinct.split('/').forEach(item => {
          if(item.split(',')[0].split('|').length > 1) {
            item.split(',')[0].split('|').forEach(content => {
              if(value.property !== undefined && value.property.length > 0) {
                value.property.forEach(data => {
                  if(content === data) result.push(res);
                });
              }

              if(value.target !== undefined && value.target.length > 0) {
                value.target.forEach(data => {
                  if(content === data) result.push(res);
                });
              }
            });
          } else {
            if(value.property !== undefined && value.property.length > 0) {
              value.property.forEach(content => {
                if(item.split(',')[0] === content) result.push(res);
              });
            }

            if(value.target !== undefined && value.target.length > 0) {
              value.target.forEach(content => {
                if(item.split(',')[0] === content) result.push(res);
              });
            }
          }
        });

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

        for(let j = 0; j < propertyArr.length; j++) combinePropertyArr.push(propertyArr[j].split(',')[0]);

        for(let j = 0; j < instinctArr.length; j++) {
          if(instinctArr[j].split(',')[0].split('|').length > 1) for(let k = 0; k < instinctArr[j].split(',')[0].split('|').length; k++) combinePropertyArr.push(instinctArr[j].split(',')[0].split('|')[k]);
          else combinePropertyArr.push(instinctArr[j].split(',')[0]);
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
          let instinctArr = unitInfo[i].instinct.split('/');
          let combinePropertyArr = [];
          
          for(let j = 0; j < unitInfo[i].target.split('/').length; j++) combinePropertyArr.push(unitInfo[i].target.split('/')[j]);

          for(let j = 0; j < instinctArr.length; j++) {
            if(instinctArr[j].split(',')[0].split('|').length > 1) for(let k = 0; k < instinctArr[j].split(',')[0].split('|').length; k++) combinePropertyArr.push(instinctArr[j].split(',')[0].split('|')[k]);
            else combinePropertyArr.push(instinctArr[j].split(',')[0]);
          }

          if(excludeSetting(combinePropertyArr, value.target).length === 0) result.push(unitInfo[i]);
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
  for(let i = 0; i < arr.length; i++) for(let j = 0; j < value.length; j++) if(arr[i] === value[j]) value = value.filter(res => res !== arr[i]);

  return value;
}

function getSearchUnitInfo(store) {
  if(store.getters.getSearchUnitInfo !== '') return store.getters.getSearchUnitInfo;
}

function getSpecificUnitInfo(store, id) {
  return store.getters.getUnitInfo.filter(res => res.id.split('-')[0] === id);
}
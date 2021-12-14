export { dataSetting, instinctSetting, levelSetting, dpsSetting };

function dataSetting(value) {
  value.data.forEach(res => {
    value.attackPower.push(res.attack_power.split('/'));
    value.hp.push(res.hp);
    value.hitBack.push(res.hit_back);
    value.attackSpeed.push(res.attack_speed.split('/'));
    value.attackEndSpeed.push(res.attack_end_speed);
    value.attackFreq.push(res.attack_freq);
    value.moveSpeed.push(res.move_speed);
    value.produceSpeed.push(res.produce_speed);
    value.cost.push(res.cost);
    value.attackRange.push(res.attack_range);
    value.description.push(res.description);
    value.property.push(res.property.split('/'));
    value.instinct = res.instinct.split('/');
    value.levelIncreaseRate = res.level_increase_rate.split(',');
    value.maxLevel.push(res.max_level);
  });
}

function instinctSetting(value) {
  let instinct = {
    img: [],
    description: [],
    maxLevel: [],
    increase: [],
    init: [],
  };

  for(let i = 0; i < value.length; i++) {
    let item = value[i].split(',');
    let keys = Object.keys(instinct);

    for(let j = 0; j < keys.length; j++) {
      instinct[`${keys[j]}`].push(item[j]);
    }
  }

  return { instinct };
}

function levelSetting(level, unitData, settingUnitData) {
  for(let i = 0; i < unitData.value.data.length; i++) {
    let attackPowerArr = unitData.value.attackPower[i];
    let attackPowers = [];
    let hpArr = unitData.value.hp[i];

    for(let j = 0; j < attackPowerArr.length; j++) {
      let attackPower = attackPowerArr[j] *= 1;
      let origin = attackPower;
      let increase = 0;

      for(let k = 2; k <= level[i]; k++) {
        increase = origin * (unitData.value.levelIncreaseRate[Math.floor((k - 1) / 10)]);
        attackPower += increase / 100;
      }

      attackPower = Math.floor(Math.round(attackPower) * 2.5);
      attackPowers.push(attackPower);
    }

    let combineAttackPower = 0;
    attackPowers.forEach(res => combineAttackPower += res);
    unitData.value.combineAttackPower[i] = combineAttackPower;

    hpArr *= 1;
    let origin = hpArr;
    let increase = 0;

    for(let k = 2; k <= level[i]; k++) {
      increase = origin * (unitData.value.levelIncreaseRate[Math.floor((k - 1) / 10)]);
      hpArr += increase / 100;
    }

    hpArr = Math.floor(Math.round(hpArr) * 2.5);

    settingUnitData.value.attackPower[i] = attackPowers;
    settingUnitData.value.hp[i] = hpArr;
  }
}

function dpsSetting(value) {
  for(let i = 0; i < value.combineAttackPower.length; i++) {
    value.dps[i] = Math.round(value.combineAttackPower[i] / (value.attackFreq[i] / 30));
  }
}
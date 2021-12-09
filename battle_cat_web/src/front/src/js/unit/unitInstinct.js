export { applyInstinct };

function applyInstinct(level, unitData, settingUnitData) {
  for(let i = 0; i < level.length; i++) {
    if(unitData.value.instinct.instinct.img[i] === 'attack_power_increase') attackPowerIncrease(i, unitData, settingUnitData);
    if(unitData.value.instinct.instinct.img[i] === 'hp_increase') hpIncrease(i, unitData, settingUnitData);
    if(unitData.value.instinct.instinct.img[i] === 'cost_decrease') costDecrease(i, unitData, settingUnitData);
    if(unitData.value.instinct.instinct.img[i] === 'attack_power_up') attackPowerUp(i, unitData, settingUnitData);
    if(unitData.value.instinct.instinct.img[i] === 'move_speed_increase') moveSpeedIncrease(i, unitData, settingUnitData);
    if(unitData.value.instinct.instinct.img[i] === 'produce_speed_increase') produceSpeedIncrease(i, unitData, settingUnitData);
  }
}

function attackPowerIncrease(idx, unitData, settingUnitData) {
  if(unitData.value.instinctApply[idx]) {
    let increaseValue = settingUnitData.value.instinct.instinct.increase[idx];

    if(settingUnitData.value.attackPower[2].length > 1) {
      let attackPowerArr = settingUnitData.value.attackPower[2];

      if(increaseValue !== 0) {
        for(let i = 0; i < attackPowerArr.length; i++) {
          attackPowerArr[i] += Math.round(attackPowerArr[i] * (increaseValue / 100));
        }
      }

      settingUnitData.value.attackPower[2] = attackPowerArr;
    }

    let combineAttackPower = unitData.value.combineAttackPower[2];
      
    if(increaseValue !== 0) combineAttackPower += combineAttackPower * (increaseValue / 100);

    unitData.value.combineAttackPower[2] = Math.round(combineAttackPower);
  }
}

function hpIncrease(idx, unitData, settingUnitData) {
  if(unitData.value.instinctApply[idx]) {
    let increaseValue = settingUnitData.value.instinct.instinct.increase[idx];
    let hp = settingUnitData.value.hp[2];

    if(increaseValue !== 0) hp += hp * (increaseValue / 100);

    settingUnitData.value.hp[2] = Math.round(hp);
  }
}

function costDecrease(idx, unitData, settingUnitData) {
  if(unitData.value.instinctApply[idx]) {
    let decreaseValue = settingUnitData.value.instinct.instinct.increase[idx];
    let cost = unitData.value.cost[2];

    if(decreaseValue !== 0) cost -= decreaseValue;

    settingUnitData.value.cost = cost;
  } else {
    settingUnitData.value.cost = 0;
  }
}

function attackPowerUp(idx, unitData, settingUnitData) {
  if(unitData.value.instinctApply[idx]) {
    let increaseValue = settingUnitData.value.instinct.instinct.increase[idx];
  
    if(settingUnitData.value.attackPower[2].length > 1) {
      let attackPowerArr = settingUnitData.value.attackPower[2];

      if(increaseValue !== 0) {
        for(let i = 0; i < attackPowerArr.length; i++) {
          attackPowerArr[i] += Math.round(attackPowerArr[i] * (increaseValue / 100));
        }
      }

      settingUnitData.value.attackPower[2] = attackPowerArr;
    }

    let combineAttackPower = unitData.value.combineAttackPower[2];
      
    if(increaseValue !== 0) combineAttackPower += combineAttackPower * (increaseValue / 100);

    unitData.value.combineAttackPower[2] = Math.round(combineAttackPower);
  }
}

function moveSpeedIncrease(idx, unitData, settingUnitData) {
  if(unitData.value.instinctApply[idx]) {
    let increaseValue = settingUnitData.value.instinct.instinct.increase[idx];
    let moveSpeed = unitData.value.moveSpeed[2];

    if(increaseValue != 0) moveSpeed += increaseValue;

    settingUnitData.value.moveSpeed = moveSpeed;
  } else {
    settingUnitData.value.moveSpeed = 0;
  }
}

function produceSpeedIncrease(idx, unitData, settingUnitData) {
  if(unitData.value.instinctApply[idx]) {
    let decreaseValue = settingUnitData.value.instinct.instinct.increase[idx];
    let decreaseInit = settingUnitData.value.instinct.instinct.init[idx];
    let produceSpeed = unitData.value.produceSpeed[2];

    if(decreaseValue != 0) produceSpeed -= decreaseValue;
    if(decreaseInit != 0 && unitData.value.instinctLevel[idx] > 0) produceSpeed -= decreaseInit;

    settingUnitData.value.produceSpeed = produceSpeed;
  } else {
    settingUnitData.value.produceSpeed = 0;
  }
}
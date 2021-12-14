export { applyInstinct };

function applyInstinct(level, unitData, settingUnitData) {
  for(let i = 0; i < level.length; i++) {
    if(unitData.value.instinct.instinct.img[i] === 'attack_power_increase') attackPowerIncrease(i, unitData, settingUnitData);
    if(unitData.value.instinct.instinct.img[i] === 'hp_increase') hpIncrease(i, settingUnitData);
    if(unitData.value.instinct.instinct.img[i] === 'cost_decrease') costDecrease(i, unitData, settingUnitData);
    if(unitData.value.instinct.instinct.img[i] === 'attack_power_up') attackPowerUp(i, unitData, settingUnitData);
    if(unitData.value.instinct.instinct.img[i] === 'move_speed_increase') moveSpeedIncrease(i, unitData, settingUnitData);
    if(unitData.value.instinct.instinct.img[i] === 'produce_speed_increase') produceSpeedIncrease(i, unitData, settingUnitData);
    if(unitData.value.instinct.instinct.img[i] === 'strength') strength(level, i, unitData, settingUnitData);
    if(unitData.value.instinct.instinct.img[i] === 'super_damage') superDamage(level, i, unitData, settingUnitData);
  }
}
//기본 공격 업
function attackPowerIncrease(idx, unitData, settingUnitData) {
  let increaseValue = settingUnitData.value.instinct.instinct.increase[idx];

  if(settingUnitData.value.attackPower[2].length > 1) {
    let attackPowerArr = settingUnitData.value.attackPower[2];

    if(increaseValue !== 0) for(let i = 0; i < attackPowerArr.length; i++) attackPowerArr[i] += Math.round(attackPowerArr[i] * (increaseValue / 100));

    settingUnitData.value.attackPower[2] = attackPowerArr;
  }

  if(increaseValue !== 0) unitData.value.combineAttackPower[2] += Math.round(unitData.value.combineAttackPower[2] * (increaseValue / 100));
}
//기본 체력 업
function hpIncrease(idx, settingUnitData) {
  let increaseValue = settingUnitData.value.instinct.instinct.increase[idx];

  if(increaseValue !== 0) settingUnitData.value.hp[2] += Math.round(settingUnitData.value.hp[2] * (increaseValue / 100));
}
//생산 비용 할인
function costDecrease(idx, unitData, settingUnitData) {
  let decreaseValue = settingUnitData.value.instinct.instinct.increase[idx];
  let cost = unitData.value.cost[2];

  if(decreaseValue !== 0) cost -= decreaseValue;

  settingUnitData.value.cost = cost;
}
//공격력 업
function attackPowerUp(idx, unitData, settingUnitData) {
  let increaseValue = settingUnitData.value.instinct.instinct.increase[idx];
  
  if(settingUnitData.value.attackPower[2].length > 1) {
    let attackPowerArr = settingUnitData.value.attackPower[2];

    if(increaseValue !== 0) for(let i = 0; i < attackPowerArr.length; i++) attackPowerArr[i] += Math.round(attackPowerArr[i] * (increaseValue / 100));

    settingUnitData.value.attackPower[2] = attackPowerArr;
  }

  if(increaseValue !== 0) unitData.value.combineAttackPower[2] += Math.round(unitData.value.combineAttackPower[2] * (increaseValue / 100));
}
//이동 속도 증가
function moveSpeedIncrease(idx, unitData, settingUnitData) {
  let increaseValue = settingUnitData.value.instinct.instinct.increase[idx];
  let moveSpeed = unitData.value.moveSpeed[2];

  if(increaseValue != 0) moveSpeed += increaseValue;

  settingUnitData.value.moveSpeed = moveSpeed;
}
//생산 속도 증가
function produceSpeedIncrease(idx, unitData, settingUnitData) {
  let decreaseValue = settingUnitData.value.instinct.instinct.increase[idx];
  let decreaseInit = settingUnitData.value.instinct.instinct.init[idx];
  let produceSpeed = unitData.value.produceSpeed[2];

  if(decreaseValue != 0) produceSpeed -= decreaseValue;
  if(decreaseInit != 0 && unitData.value.instinctLevel[idx] > 0) produceSpeed -= decreaseInit;

  settingUnitData.value.produceSpeed = produceSpeed;
}
//엄청 강하다
function strength(level, idx, unitData, settingUnitData) {
  if(level[idx] > 0) {
    settingUnitData.value.hp[2] = Math.round(settingUnitData.value.hp[2] * 2.5);

    if(settingUnitData.value.attackPower[2].length > 1) {
      let attackPowerArr = settingUnitData.value.attackPower[2];

      for(let i = 0; i < attackPowerArr.length; i++) attackPowerArr[i] += Math.round(attackPowerArr[i] * 1.8);

      settingUnitData.value.attackPower[2] = attackPowerArr;
    }

    unitData.value.combineAttackPower[2] = Math.round(unitData.value.combineAttackPower[2] * 1.8);
  }
}
//초 데미지
function superDamage(level, idx, unitData, settingUnitData) {
  if(level[idx] > 0) {
    if(settingUnitData.value.attackPower[2].length > 1) {
      let attackPowerArr = settingUnitData.value.attackPower[2];
  
      for(let i = 0; i < attackPowerArr.length; i++) attackPowerArr[i] *= 4;
  
      settingUnitData.value.attackPower[2] = attackPowerArr;
    }
  
    unitData.value.combineAttackPower[2] = Math.round(unitData.value.combineAttackPower[2] * 4);
  }
}
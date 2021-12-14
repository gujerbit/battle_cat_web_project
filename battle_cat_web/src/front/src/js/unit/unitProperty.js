export { applyProperty };

function applyProperty(unitData, settingUnitData) {
  for(let i = 0; i < unitData.value.propertyApply.length; i++) {
    if(unitData.value.propertyApply[i]) {
      let property = unitData.value.property[i][0].split(',')[0];

      if(property === 'strength') strength(i, unitData, settingUnitData);
      if(property === 'super_damage') superDamage(i, unitData, settingUnitData);
      if(property === 'ultra_damage') ultraDamage(i, unitData, settingUnitData);
      if(property === 'hard') hard(i, settingUnitData);
      if(property === 'ultra_hard') ultraHard(i, settingUnitData);
    }
  }
}
//엄청 강하다
function strength(idx, unitData, settingUnitData) {
  settingUnitData.value.hp[idx] = Math.round(settingUnitData.value.hp[idx] * 2.5);

  if(settingUnitData.value.attackPower[idx].length > 1) {
    let attackPowerArr = settingUnitData.value.attackPower[idx];

    for(let i = 0; i < attackPowerArr.length; i++) attackPowerArr[i] += Math.round(attackPowerArr[i] * 1.8);

    settingUnitData.value.attackPower[idx] = attackPowerArr;
  }

  unitData.value.combineAttackPower[idx] = Math.round(unitData.value.combineAttackPower[idx] * 1.8);
}
//초 데미지
function superDamage(idx, unitData, settingUnitData) {
  if(settingUnitData.value.attackPower[idx].length > 1) {
    let attackPowerArr = settingUnitData.value.attackPower[idx];

    for(let i = 0; i < attackPowerArr.length; i++) attackPowerArr[i] *= 4;

    settingUnitData.value.attackPower[idx] = attackPowerArr;
  }

  unitData.value.combineAttackPower[idx] = Math.round(unitData.value.combineAttackPower[idx] * 4);
}
//극 데미지
function ultraDamage(idx, unitData, settingUnitData) {
  if(settingUnitData.value.attackPower[idx].length > 1) {
    let attackPowerArr = settingUnitData.value.attackPower[idx];

    for(let i = 0; i < attackPowerArr.length; i++) attackPowerArr[i] *= 6;
  }

  unitData.value.combineAttackPower[idx] = Math.round(unitData.value.combineAttackPower[idx] * 6);
}
//맷집이 좋다
function hard(idx, settingUnitData) {
  settingUnitData.value.hp[idx] = Math.round(settingUnitData.value.hp[idx] * 5);
}
//초 맷집이 좋다
function ultraHard(idx, settingUnitData) {
  settingUnitData.value.hp[idx] = Math.round(settingUnitData.value.hp[idx] * 7);
}
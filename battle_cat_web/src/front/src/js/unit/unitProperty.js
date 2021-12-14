export { applyProperty };

function applyProperty(unitData, settingUnitData) {
  for(let i = 0; i < unitData.value.propertyApply.length; i++) {
    if(unitData.value.propertyApply[i]) {
      let property = unitData.value.property[i][0].split(',')[0];

      if(property === 'strength') strength(i, unitData, settingUnitData);
      if(property === 'super_damage') superDamage(i, unitData, settingUnitData);
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
// function ultraDamage(idx, unitData, settingUnitData) {

// }
<template>
  <main>
    <div id="content">
      <div class="upgrade">
        <p @click="changeUpgrade(index + 1)" v-for="(value, index) in unitData.data" :key="value" :class="unitData.upgrade === index + 1 ? 'select' : ''">{{index + 1}}단 진화</p>
      </div>
      <div v-show="unitData.upgrade === index + 1" class="unit-info" v-for="(value, index) in unitData.data" :key="value">
        <div class="title">
          <div class="title-header">
            <p>ID</p> <p>이름</p> <p>등급</p> <p>레벨</p>
          </div>
          <div class="title-content">
            <p>{{value.id}}</p>
            <p>{{value.name}}</p>
            <div class="rarity">
              <img :src="require(`../../assets/res/elements/rarity/${value.rarity}.png`)" alt="">
            </div>
            <input @mouseover="wheelLevelChange(index, watching, unitData)" @mouseleave="scrollPrevent(watching)" class="level" v-model="unitData.level[index]" type="number" onfocus="this.select()">
          </div>
        </div>
        <div class="image">
          <div class="image-title">
            <p>유닛 이미지</p> <p>대항 속성</p> <p>유닛 능력/효과</p>
          </div>
          <div class="image-content">
            <div class="thumbnail">
              <img :src="require(`./../../assets/res/unit/${value.image_dir}`)" alt="">
            </div>
            <div class="target">
              <template v-for="item in elements.target" :key="item">
                <template v-if="settingUnitData.instinct.instinct.img.join().split('|').join().split(',').length > 5 && settingUnitData.instinct.instinct.img.join().split('|').join().split(',').includes(item)">
                  <img v-if="unitData.instinctLevel[settingUnitData.instinct.instinct.img.join().split('|').join().split(',').indexOf(item) - 1] > 0" :src="require(`../../assets/res/elements/property/target/${item}.png`)" alt="">
                  <img v-else :src="require(`../../assets/res/elements/property/untarget/${item}.png`)" alt="">
                </template>
                <template v-else-if="settingUnitData.instinct.instinct.img.join().split('|').join().split(',').length <= 5 && settingUnitData.instinct.instinct.img.includes(item)">
                  <img v-if="unitData.instinctLevel[settingUnitData.instinct.instinct.img.indexOf(item) - 1] > 0" :src="require(`../../assets/res/elements/property/target/${item}.png`)" alt="">
                  <img v-else :src="require(`../../assets/res/elements/property/untarget/${item}.png`)" alt="">
                </template>
                <img v-else-if="value.target.includes(item)" :src="require(`../../assets/res/elements/property/target/${item}.png`)" alt="">
                <img v-else :src="require(`../../assets/res/elements/property/untarget/${item}.png`)" alt="">
              </template>
            </div>
            <div class="element">
              <template v-if="value.property.length > 0">
                <img v-for="item in value.property.split('/')" :key="item" :src="require(`../../assets/res/elements/property_search/${item.split(',')[0]}.png`)" alt="">
              </template>
              <template v-if="value.instinct.length > 0">
                <template v-for="item in settingUnitData.instinct" :key="item">
                  <template v-for="(content, idx) in item.img" :key="content">
                    <img v-if="!elements.target.includes(content.split('|')[0])" :class="unitData.instinctLevel[idx] > 0 ? '' : 'unselect'" :src="require(`../../assets/res/elements/property_search/${content.split('|')[0]}.png`)" alt="">
                    <img v-else-if="!elements.target.includes(content.split('|')[1])" :class="unitData.instinctLevel[idx] > 0 ? '' : 'unselect'" :src="require(`../../assets/res/elements/property_search/${content.split('|')[1]}.png`)" alt="">
                  </template>
                </template>
              </template>
              <img v-for="item in value.attack_type.split('/')" :key="item" :src="require(`../../assets/res/elements/property_search/attack_type_${item}.png`)" alt="">
            </div>
          </div>
        </div>
        <div class="content">
          <section>
            <div class="content-title">
              <p>체력</p> <p>히트백</p> <p>공격력</p> <p>DPS</p> <p>사거리</p> <p>가격</p>
            </div>
            <div class="content-data">
              <p>{{settingUnitData.hp[index]}}</p>
              <p>{{unitData.hitBack[index]}}</p>
              <div class="attack-power">
                <div class="content-division-forward">
                  <template v-for="(item, idx) in settingUnitData.attackPower[index]" :key="item + idx">
                    <p v-if="idx === 0">{{unitData.combineAttackPower[index]}}</p>
                  </template>
                </div>
                <div class="content-division-backend" v-if="settingUnitData.attackPower[index].length > 1">
                  <template v-for="(item, idx) in settingUnitData.attackPower[index]" :key="item + idx">
                    <p v-if="idx === 0">(</p>
                    <p v-if="idx !== 0">/</p>
                    <p>{{item}}</p>
                    <p v-if="idx === settingUnitData.attackPower[index].length - 1">)</p>
                  </template>
                </div>
              </div>
              <p>{{unitData.dps[index]}}</p>
              <p>{{unitData.attackRange[index]}}</p>
              <div class="cost">
                <p v-if="index === 2 && settingUnitData.cost !== 0">{{settingUnitData.cost}}</p>
                <p v-else>{{unitData.cost[index]}}</p>
              </div>
            </div>
          </section>
          <section>
            <div class="content-title">
              <p>공격주기</p> <p>공격간격</p> <p>선딜</p> <p>후딜</p> <p>이동속도</p> <p>생산속도</p> 
            </div>
            <div class="content-data">
              <div class="attack-freq">
                <p>{{(unitData.attackFreq[index] / 30).toFixed(1)}}초</p>
                <p>{{unitData.attackFreq[index]}}F</p>
              </div>
              <div class="attack-cycle">
                <div class="content-division-forward">
                  <template v-for="(item, idx) in unitData.attackSpeed[index]" :key="item">
                    <p v-if="idx !== 0">/</p>
                    <p>{{Math.abs(((unitData.attackFreq[index] / 30) - (item / 30) - (unitData.attackEndSpeed[index] / 30))).toFixed(1)}}</p>
                    <p v-if="idx === unitData.attackSpeed[index].length - 1">초</p>
                  </template>
                </div>
                <div class="content-division-backend">
                  <template v-for="(item, idx) in unitData.attackSpeed[index]" :key="item">
                    <p v-if="idx !== 0">/</p>
                    <p>{{unitData.attackFreq[index] - item - unitData.attackEndSpeed[index]}}</p>
                    <p v-if="idx === unitData.attackSpeed[index].length - 1">F</p>
                  </template>
                </div>
              </div>
              <div class="attack-speed">
                <div class="content-division-forward">
                  <template v-for="(item, idx) in unitData.attackSpeed[index]" :key="item">
                    <p v-if="idx !== 0">/</p>
                    <p>{{(item / 30).toFixed(1)}}</p>
                    <p v-if="idx === unitData.attackSpeed[index].length - 1">초</p>
                  </template>
                </div>
                <div class="content-division-backend">
                  <template v-for="(item, idx) in unitData.attackSpeed[index]" :key="item">
                    <p v-if="idx !== 0">/</p>
                    <p>{{item}}</p>
                    <p v-if="idx === unitData.attackSpeed[index].length - 1">F</p>
                  </template>
                </div>
              </div>
              <div class="attack-end-speed">
                <p>{{(unitData.attackEndSpeed[index] / 30).toFixed(1)}}초</p>
                <p>{{unitData.attackEndSpeed[index]}}F</p>
              </div>
              <p>{{unitData.moveSpeed[index]}}</p>
              <div class="produce-speed">
                <p>{{(unitData.produceSpeed[index] / 30).toFixed(1)}}초</p>
                <p>{{unitData.produceSpeed[index]}}F</p>
              </div>
            </div>
          </section>
          <div class="property" v-show="unitData.property[index] != ''">
            <p>능력/효과</p>
            <div class="property-content">
              <p v-for="item in unitData.property[index]" :key="item">{{item.split(',')[1]}}</p>
            </div>
          </div>
          <div class="instinct" v-show="index === 2 && settingUnitData.instinct.instinct.img.length > 1">
            <p>본능</p>
            <div class="instinct-content" v-for="item in settingUnitData.instinct" :key="item">
              <div class="instinct-data" v-for="(content, idx) in item.img" :key="content + idx">
                <div class="text">
                  <p>
                    <span>{{(item.description[idx] + '').split('|')[0] + ' '}}</span>
                    <span v-if="unitData.instinctLevel[idx] > 0 && (item.init[idx] !== undefined || item.increase[idx] !== undefined)">{{(item.init[idx] !== undefined ? Number(item.init[idx]) : 0) + (item.increase[idx] !== undefined ? Number(item.increase[idx]) : 0)}}</span>
                    <span v-else-if="item.init[idx] !== undefined || item.increase[idx] !== undefined">0</span>
                    <span>{{(item.description[idx] + '').split('|')[1]}}</span>
                  </p>
                </div>
                <div class="input">
                  <input @mouseover="wheelInstinctLevelChange($event, idx, watching, unitData);" @mouseleave="scrollPrevent(watching); overflowScrollApply($event)" class="instinct-level" v-model="unitData.instinctLevel[idx]" type="number" onfocus="this.select()">
                  <button @click="instinctApply(idx)">{{unitData.instinctApply[idx] === true ? '적용' : '미적용'}}</button>
                </div>
              </div>
            </div>
          </div>
          <div class="description">
            <p>인게임 설명</p>
            <p>{{unitData.description[index]}}</p>
          </div>
        </div>
      </div>
    </div>
    <router-link to="/" class="main-page">메인 화면으로 돌아가기</router-link>
    <router-link to="/unitInfo" class="unit-info-page">아군 캐릭터 정보 화면으로 돌아가기</router-link>
  </main>
</template>

<script>
import { ref, onBeforeMount, getCurrentInstance, watchEffect, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { getSpecificUnitInfo } from '../../js/unit/unitInfo.js';
import { dataSetting, instinctSetting, levelSetting, dpsSetting } from '../../js/unit/unitSpecifiecInfo.js';
import { applyInstinct } from '../../js/unit/unitInstinct.js';
import { wheelLevelChange, wheelInstinctLevelChange, scrollPrevent, overflowScrollApply } from '../../js/util/wheelControl.js';

export default {
  setup() {
    const route = useRoute();
    const { proxy } = getCurrentInstance();

    const elements = {
      property: ['attack_power_down', 'stop', 'slow', 'target_only', 'strength', 'hard', 'ultimate_hard', 'super_damage', 'ultimate_damage', 'knock_back', 'warp', 'ancient_curse', 'attack_invalid', 'attack_power_up', 'survive', 'ultimate_base_crash', 'critical', 'zombie_killer', 'barrier_breaker', 'soul_attack', 'more_money', 'metal', 'mini_wave', 'wave', 'thermal_wave', 'attack_power_down_invalid', 'stop_invalid', 'slow_invalid', 'knock_back_invalid', 'wave_invalid', 'thermal_wave_invalid', 'warp_invalid', 'ancient_curse_invalid', 'poison_invalid', 'wave_stopper', 'attack_type_single', 'attack_type_multi', 'attack_type_long', 'attack_type_wide', 'attack_power_down_defense', 'stop_defense', 'slow_defense', 'knock_back_defense', 'wave_defense', 'thermal_wave_defense', 'warp_defense', 'ancient_curse_defense', 'poison_defense', 'hp_increase', 'attack_power_increase', 'move_speed_increase', 'knock_back_count_increase', 'cost_decrease', 'produce_speed_increase'],
      rarity: ['normal', 'special', 'rare', 'super_rare', 'uber_rare', 'legend_rare'],
      target: ['red', 'fly', 'black', 'metal', 'angel', 'alien', 'zombie', 'ancient', 'devil']
    };

    const unitData = ref({
      data: [],
      upgrade: 1,
      level: [1, 10, 30],
      instinctLevel: [0, 0, 0, 0, 0],
      attackPower: [],
      dps: [],
      hp: [],
      hitBack: [],
      attackSpeed: [],
      attackEndSpeed: [],
      attackFreq: [],
      moveSpeed: [],
      produceSpeed: [],
      cost: [],
      attackRange: [],
      description: [],
      property: [],
      instinct: [],
      levelIncreaseRate: [],
      combineAttackPower: [],
      maxLevel: [],
      instinctApply: [true, true, true, true, true],
    });

    const settingUnitData = ref({
      attackPower: [],
      hp: [],
      instinct: [],
      cost: 0,
    });

    const watching = ref({
      ready: false,
      scroll: true,
    });

    const changeUpgrade = (value) => {
      unitData.value.upgrade = value;
    };

    const instinctLevelSetting = (level) => {
      for(let i = 0; i < level.length; i++) {
        let origin = unitData.value.instinct.instinct.increase[i];
        
        if(origin !== undefined) origin *= level[i];
        
        settingUnitData.value.instinct.instinct.increase[i] = origin;
      }

      applyInstinct(level, unitData, settingUnitData);
    };

    const instinctApply = (idx) => {
      unitData.value.instinctApply[idx] = !unitData.value.instinctApply[idx];
    };

    onBeforeMount(() => {
      unitData.value.data = getSpecificUnitInfo(proxy.store, route.params.unitId);
      dataSetting(unitData.value);
      let instinct = unitData.value.instinct;

      for(let i = 0; i < 2; i++) {
        if(i === 0) unitData.value.instinct = instinctSetting(instinct);
        else settingUnitData.value.instinct = instinctSetting(instinct);
      }

      watching.value.ready = true;
    });

    onMounted(() => {
      let attackPowerContent = document.querySelectorAll('.attack-power');

      for(let i = 0; i < attackPowerContent.length; i++) {
        if(settingUnitData.value.attackPower[i].length <= 1) attackPowerContent[i].style.gridTemplateRows = '1fr';
      }
    });

    watchEffect(() => {
      if(watching.value.ready) {
        for(let i = 0; i < unitData.value.maxLevel.length; i++) {
          if(unitData.value.level[i] > unitData.value.maxLevel[i]) unitData.value.level[i] = unitData.value.maxLevel[i];
          if(unitData.value.level[i] < 1) unitData.value.level[i] = 1;
        }

        for(let i = 0; i < unitData.value.instinct.instinct.maxLevel.length; i++) {
          if(unitData.value.instinctLevel[i] > unitData.value.instinct.instinct.maxLevel[i] * 1) unitData.value.instinctLevel[i] = unitData.value.instinct.instinct.maxLevel[i] * 1;
          if(unitData.value.instinctLevel[i] < 0) unitData.value.instinctLevel[i] = 0;
        }

        levelSetting(unitData.value.level, unitData, settingUnitData);
        instinctLevelSetting(unitData.value.instinctLevel);
        dpsSetting(unitData.value);
      }
    });

    return { unitData, settingUnitData, elements, watching, changeUpgrade, wheelLevelChange, wheelInstinctLevelChange, scrollPrevent, instinctApply, overflowScrollApply };
  }
}
</script>

<style scoped src="../../css/unit/unitInfoSpecificComponent.css" />
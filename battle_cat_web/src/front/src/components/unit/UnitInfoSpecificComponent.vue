<template>
  <main>
    <div id="content">
      <div class="upgrade">
        <p @click="unitData.upgrade = index + 1" v-for="(value, index) in unitData.data" :key="value" :class="unitData.upgrade === index + 1 ? 'select' : ''">{{index + 1}}단 진화</p>
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
                  <img v-if="unitData.instinctLevel[settingUnitData.instinct.instinct.img.indexOf(item)] > 0" :src="require(`../../assets/res/elements/property/target/${item}.png`)" alt="">
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
                    <img v-if="content.split('|')[0] !== undefined && !elements.target.includes(content.split('|')[0])" :class="unitData.instinctLevel[idx] > 0 ? '' : 'unselect'" :src="require(`../../assets/res/elements/property_search/${content.split('|')[0]}.png`)" alt="">
                    <img v-else-if="content.split('|')[1] !== undefined && !elements.target.includes(content.split('|')[1])" :class="unitData.instinctLevel[idx] > 0 ? '' : 'unselect'" :src="require(`../../assets/res/elements/property_search/${content.split('|')[1]}.png`)" alt="">
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
              <p>체력</p> <p>히트백</p> <p>공격력</p> <p>DPS</p> <p>인식 사거리 (타격 범위)</p> <p>가격</p>
            </div>
            <div class="content-data">
              <p>{{settingUnitData.hp[index]}}</p>
              <p>{{unitData.hitBack[index]}}</p>
              <div class="attack-power">
                <div class="content-division-forward">
                  <p>{{unitData.combineAttackPower[index]}}</p>
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
              <div class="move-speed">
                <p v-if="index === 2 && settingUnitData.moveSpeed !== 0">{{settingUnitData.moveSpeed}}</p>
                <p v-else>{{unitData.moveSpeed[index]}}</p>
              </div>
              <div class="produce-speed">
                <template v-if="index === 2 && settingUnitData.produceSpeed !== 0">
                  <p>{{(settingUnitData.produceSpeed / 30).toFixed(1)}}초</p>
                  <p>{{settingUnitData.produceSpeed}}F</p>
                </template>
                <template v-else>
                  <p>{{(unitData.produceSpeed[index] / 30).toFixed(1)}}초</p>
                  <p>{{unitData.produceSpeed[index]}}F</p>
                </template>
              </div>
            </div>
          </section>
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
                </div>
              </div>
            </div>
          </div>
          <div class="property" v-if="unitData.property[index] != ''">
            <div class="property-header">
              <p>능력/효과</p>
              <button @click="unitData.propertyApply[index] = !unitData.propertyApply[index]">{{unitData.propertyApply[index] ? '적용' : '미적용'}}</button>
            </div>
            <div class="property-content">
              <p v-for="item in unitData.property[index]" :key="item">{{item.split(',')[1]}}</p>
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
import { ref, onBeforeMount, getCurrentInstance, watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import { getSpecificUnitInfo } from '../../js/unit/unitInfo.js';
import { dataSetting, instinctSetting, levelSetting, dpsSetting } from '../../js/unit/unitSpecifiecInfo.js';
import { applyInstinct } from '../../js/unit/unitInstinct.js';
import { applyProperty } from '../../js/unit/unitProperty.js';
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
    //미가공 유닛 데이터
    const unitData = ref({
      data: [], //전체 데이터
      upgrade: 1, //현재 진화 단계
      level: [1, 10, 30], //진화 단계별 레벨
      instinctLevel: [0, 0, 0, 0, 0], //본능별 레벨
      attackPower: [], //공격력
      dps: [], //초당 공격력
      hp: [], //체력
      hitBack: [], //히트백
      attackSpeed: [], //선딜
      attackEndSpeed: [], //후딜
      attackFreq: [], //공격 빈도
      moveSpeed: [], //이동 속도
      produceSpeed: [], //생산 속도
      cost: [], //가격
      attackRange: [], //사거리
      description: [], //인게임 설명
      property: [], //능력
      instinct: [], //본능
      levelIncreaseRate: [], //레벨 당 스펙 증가율
      combineAttackPower: [], //합산 공격력
      maxLevel: [], //최대 레벨
      propertyApply: [false, false, false],
    });
    //가공 유닛 데이터
    const settingUnitData = ref({
      attackPower: [], //공격력
      hp: [], //체력
      instinct: [], //본능
      cost: 0, //가격
      moveSpeed: 0, //이동 속도
      produceSpeed: 0 //생산 속도
    });
    //스크롤 방지
    const watching = ref({
      scroll: true, //스크롤을 해도 되는지
    });
    //본능 레벨 설정
    const instinctLevelSetting = (level) => {
      for(let i = 0; i < level.length; i++) if(unitData.value.instinct.instinct.increase[i] !== undefined) settingUnitData.value.instinct.instinct.increase[i] = unitData.value.instinct.instinct.increase[i] * level[i];

      applyInstinct(level, unitData, settingUnitData);
    };

    onBeforeMount(() => {
      unitData.value.data = getSpecificUnitInfo(proxy.store, route.params.unitId); //유닛 데이터 가져오기
      dataSetting(unitData.value); //유닛 데이터 설정
      let instinct = unitData.value.instinct; //설정전 본능
      unitData.value.instinct = instinctSetting(instinct); //미가공 유닛 데이터에 본능 설정
      settingUnitData.value.instinct = instinctSetting(instinct); //가공 유닛 데이터에 본능 설정
    });

    watchEffect(() => {
      if(settingUnitData.value.instinct.instinct !== undefined) { //본능 설정이 끝났다면
        for(let i = 0; i < unitData.value.maxLevel.length; i++) {
          if(unitData.value.level[i] > unitData.value.maxLevel[i]) unitData.value.level[i] = unitData.value.maxLevel[i]; //유닛 레벨이 최대 레벨 보다 높다면 유닛 레벨을 최대 레벨로 설정
          if(unitData.value.level[i] < 1) unitData.value.level[i] = 1; //유닛 레벨이 1보다 작다면 유닛 레벨을 1로 설정
        }

        for(let i = 0; i < unitData.value.instinct.instinct.maxLevel.length; i++) {
          if(unitData.value.instinctLevel[i] > unitData.value.instinct.instinct.maxLevel[i] * 1) unitData.value.instinctLevel[i] = unitData.value.instinct.instinct.maxLevel[i] * 1; //본능 레벨이 최대 레벨 보다 높다면 본능 레벨을 최대 레벨로 설정
          if(unitData.value.instinctLevel[i] < 0) unitData.value.instinctLevel[i] = 0; //본능 레벨이 0보다 작다면 본능 레벨을 0으로 설정
        }

        levelSetting(unitData.value.level, unitData, settingUnitData); //유닛 레벨 설정
        applyProperty(unitData, settingUnitData);
        instinctLevelSetting(unitData.value.instinctLevel); //유닛 본능 레벨 설정
        dpsSetting(unitData.value); //유닛 dps 설정
      }
    });

    return { unitData, settingUnitData, elements, watching, wheelLevelChange, wheelInstinctLevelChange, scrollPrevent, overflowScrollApply };
  }
}
</script>

<style scoped src="../../css/unit/unitInfoSpecificComponent.css" />
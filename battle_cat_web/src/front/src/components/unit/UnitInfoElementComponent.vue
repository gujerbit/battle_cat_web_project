<template>
  <div id="element">
    <div class="content">
      <div class="property">
        <div v-for="value in elements.property" :key="value" class="data">
          <img @click="setProperty($event, value)" :src="require(`../../assets/res/elements/property_search/${value}.png`)" alt="">
        </div>
      </div>
      <section>
        <div class="rarity">
          <div v-for="value in elements.rarity" :key="value" class="data">
            <img @click="setRarity($event, value)" :src="require(`../../assets/res/elements/rarity/${value}.png`)" alt="">
          </div>
        </div>
        <div class="target">
          <div v-for="value in elements.target" :key="value" class="data">
            <img @click="setTarget($event, value)" :src="require(`../../assets/res/elements/target/${value}_none.png`)" alt="">
          </div>
        </div>
      </section>
    </div>
    <div class="search">
      <input @input="setSearchData($event)" type="text" placeholder="유닛 이름 검색">
      <button @click="setInclude()" class="include">{{searchInfo.include ? '포함' : '미포함'}}</button>
    </div>
  </div>
</template>

<script>
import { ref, getCurrentInstance } from 'vue';
import { setSearchUnitInfo } from '../../js/unit/unitInfo.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const elements = {
      property: ['attack_power_down', 'stop', 'slow', 'target_only', 'strength', 'hard', 'ultimate_hard', 'super_damage', 'ultimate_damage', 'knock_back', 'warp', 'ancient_curse', 'attack_invalid', 'attack_power_up', 'survive', 'ultimate_base_crash', 'critical', 'zombie_killer', 'barrier_breaker', 'shield_breaker', 'soul_attack', 'more_money', 'metal', 'mini_wave', 'wave', 'thermal_wave', 'attack_power_down_invalid', 'stop_invalid', 'slow_invalid', 'knock_back_invalid', 'wave_invalid', 'thermal_wave_invalid', 'warp_invalid', 'ancient_curse_invalid', 'poison_invalid', 'wave_stopper', 'attack_type_single', 'attack_type_multi', 'attack_type_long', 'attack_type_wide', 'attack_power_down_defense', 'stop_defense', 'slow_defense', 'knock_back_defense', 'wave_defense', 'thermal_wave_defense', 'warp_defense', 'ancient_curse_defense', 'poison_defense', 'hp_increase', 'attack_power_increase', 'move_speed_increase', 'knock_back_count_increase', 'cost_decrease', 'produce_speed_increase'],
      rarity: ['normal', 'special', 'rare', 'super_rare', 'uber_rare', 'legend_rare'],
      target: ['red', 'fly', 'black', 'metal', 'angel', 'alien', 'zombie', 'devil', 'ancient']
    };
    //검색 값
    const searchInfo = ref({
      property: [],
      rarity: [],
      target: [],
      attackType: [],
      search: '', //유닛 이름
      include: true, //OR 검색 == true & AND 검색 == false
    });
    //검색 값에 능력 or 공격 타입 추가 & 제외하기
    const setProperty = (event, value) => {
      if(value.includes('attack_type')) { //공격 타입이라면
        if(!event.currentTarget.className.includes('select')) searchInfo.value.attackType.push(value.split('attack_type_')[1]); //선택이 안되어 있었다면 검색 값에 해당 공격 타입 추가하기
        else searchInfo.value.attackType = searchInfo.value.attackType.filter(res => res !== value.split('attack_type_')[1]); //선택이 되어 있었다면 기존 검색 값에 해당 공격 타입 제외하기
      } else { //능력이라면
        if(!event.currentTarget.className.includes('select')) searchInfo.value.property.push(value); //선택이 안되어 있었다면 검색 값에 해당 능력 추가하기
        else searchInfo.value.property = searchInfo.value.property.filter(res => res !== value); //선택이 되어 있었다면 검색 값에 해당 능력 제외하기
      }

      setSearchUnitInfo(searchInfo.value, proxy.store); //검색
      setStyle(event); //스타일 세팅
    };
    //검색 값에 레어도 추가 & 제외하기
    const setRarity = (event, value) => {
      if(!event.currentTarget.className.includes('select')) searchInfo.value.rarity.push(value); //선택이 안되어 있었다면 검색 값에 해당 레어도 추가하기 
      else searchInfo.value.rarity = searchInfo.value.rarity.filter(res => res !== value); //선택이 되어 있었다면 검색 값에 해당 레어도 제외하기

      setSearchUnitInfo(searchInfo.value, proxy.store); //검색
      setStyle(event); //스타일 세팅
    };
    //검색 값에 타겟 추가 & 제외하기
    const setTarget = (event, value) => {
      if(!event.currentTarget.className.includes('select')) searchInfo.value.target.push(value.includes('_none') ? value.split('_none')[0] : value); //선택이 안되어 있었다면 검색 값에 해당 타겟 추가하기
      else searchInfo.value.target = searchInfo.value.target.filter(res => res !== (value.includes('_none') ? value.split('_none')[0] : value)); //선택이 되어 있었다면 검색 값에 해당 타겟 제외하기
      //이미지 세팅
      if(event.currentTarget.src.includes('_none')) event.currentTarget.src = require(`../../assets/res/elements/target/${value.split('_none')[0]}.png`);
      else event.currentTarget.src = require(`../../assets/res/elements/target/${value}_none.png`);

      setSearchUnitInfo(searchInfo.value, proxy.store); //검색
      setStyle(event); //스타일 세팅
    };
    //검색 값에 이름 추가 & 제외하기
    const setSearchData = (event) => {
      searchInfo.value.search = event.currentTarget.value; //input 값 동기화
      setSearchUnitInfo(searchInfo.value, proxy.store); //검색
    };
    //OR 검색 & AND 검색 설정
    const setInclude = () => {
      searchInfo.value.include = !searchInfo.value.include;
      setSearchUnitInfo(searchInfo.value, proxy.store);
    };
    //스타일 세팅
    const setStyle = (event) => {
      if(event.currentTarget.className.includes('select')) event.currentTarget.classList.remove('select'); //이미 선택이 되어있다면 select 클래스 제외하기
      else event.currentTarget.classList.add('select'); //선택이 안되어 있었다면 select 클래스 추가하기
    };

    return { elements, searchInfo, setProperty, setRarity, setTarget, setSearchData, setInclude };
  }
}
</script>

<style scoped src="../../css/unit/unitInfoElementComponent.css" />
<template>
  <div id="element">
    <div class="content">
      <div class="property">
        <div v-for="value in elements.property" :key="value" class="data">
          <img @click="setProperty($event, value)" :src="require(`../../assets/res/elements/property_search/${value}.png`)" alt="">
        </div>
      </div>
      <div class="target">
        <div v-for="value in elements.target" :key="value" class="data">
          <img @click="setTarget($event, value)" :src="require(`../../assets/res/elements/target/${value}_none.png`)" alt="">
        </div>
      </div>
    </div>
    <div class="search">
      <input @input="setSearchData($event)" type="text" placeholder="유닛 이름 검색">
      <button @click="setInclude()" class="include">{{searchInfo.include ? '포함' : '미포함'}}</button>
    </div>
  </div>
</template>

<script>
import { ref, getCurrentInstance } from 'vue';
import { setSearchEnemyInfo } from '../../js/enemy/enemyInfo.js';

export default {
  setup() {
    const { proxy } = getCurrentInstance();

    const elements = {
      property: ['attack_power_down', 'stop', 'slow', 'knock_back', 'warp', 'ancient_curse', 'attack_invalid', 'attack_power_up', 'survive', 'ultimate_base_crash', 'critical', 'soul_attack', 'mini_wave', 'wave', 'thermal_wave', 'poison', 'burrow', 'resurrection', 'barrier_breaker', 'shield_breaker', 'attack_power_down_invalid', 'stop_invalid', 'slow_invalid', 'knock_back_invalid', 'wave_invalid', 'thermal_wave_invalid', 'warp_invalid', 'ancient_curse_invalid', 'attack_type_single', 'attack_type_multi', 'attack_type_long', 'attack_type_wide'],
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

      setSearchEnemyInfo(searchInfo.value, proxy.store); //검색
      setStyle(event); //스타일 세팅
    };
    //검색 값에 타겟 추가 & 제외하기
    const setTarget = (event, value) => {
      if(!event.currentTarget.className.includes('select')) searchInfo.value.target.push(value.includes('_none') ? value.split('_none')[0] : value); //선택이 안되어 있었다면 검색 값에 해당 타겟 추가하기
      else searchInfo.value.target = searchInfo.value.target.filter(res => res !== (value.includes('_none') ? value.split('_none')[0] : value)); //선택이 되어 있었다면 검색 값에 해당 타겟 제외하기
      //이미지 세팅
      if(event.currentTarget.src.includes('_none')) event.currentTarget.src = require(`../../assets/res/elements/target/${value.split('_none')[0]}.png`);
      else event.currentTarget.src = require(`../../assets/res/elements/target/${value}_none.png`);

      setSearchEnemyInfo(searchInfo.value, proxy.store); //검색
      setStyle(event); //스타일 세팅
    };
    //검색 값에 이름 추가 & 제외하기
    const setSearchData = (event) => {
      searchInfo.value.search = event.currentTarget.value; //input 값 동기화
      setSearchEnemyInfo(searchInfo.value, proxy.store); //검색
    };
    //OR 검색 & AND 검색 설정
    const setInclude = () => {
      searchInfo.value.include = !searchInfo.value.include; //OR라면 AND로, AND라면 OR로
      setSearchEnemyInfo(searchInfo.value, proxy.store); //검색
    };
    //스타일 세팅
    const setStyle = (event) => {
      if(event.currentTarget.className.includes('select')) event.currentTarget.classList.remove('select'); //이미 선택이 되어있다면 select 클래스 제외하기
      else event.currentTarget.classList.add('select'); //선택이 안되어 있었다면 select 클래스 추가하기
    };

    return { elements, searchInfo, setProperty, setTarget, setSearchData, setInclude };
  }
}
</script>

<style scoped>
#element {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-rows: 87.5% 12.5%;
}

#element * {
  text-decoration: none;
}

.content {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  display: grid;
  grid-template-rows: 3fr 1fr;
  overflow: auto;
  border: 1.5px solid #ffc038;
}

.content::-webkit-scrollbar {
  display: none;
}

.data {
  width: 80%;
  height: 80%;
}

.data img {
  width: 100%;
  height: 100%;
}

.property {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(20, 1fr);
  justify-items: center;
  align-items: center;
}

section {
  width: 100%;
  height: 100%;
}

.target {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  justify-items: center;
  align-items: center;
}

.target img {
  width: 80%;
}

.search {
  width: 90%;
  height: 100%;
  margin: 0 auto;
  display: flex;
  justify-content: center;
  align-items: top;
}

.search input {
  width: 90%;
  height: 100%;
  position: relative;
  outline: 0;
  border: 1.5px solid #ffc038;
  border-top: none;
  background-color: #ffffff;
  text-align: center;
  font-size: 2.8rem;
}

.search .include {
  width: 10%;
  height: 100%;
  border: 1.5px solid #ffc038;
  background-color: #ffffff;
  border-left: none;
  border-top: none;
  outline: 0;
  text-align: center;
  font-size: 2.5rem;
  cursor: pointer;
}

img {
  cursor: pointer;
  transition: all 0.5s;
  filter: grayscale(1);
}

img:hover {
  transform: scale(0.9);
}

.select {
  filter: grayscale(0);
}
</style>